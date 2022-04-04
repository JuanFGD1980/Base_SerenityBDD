package co.com.sofka.hibot.utils;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.PropertiesConfigurationLayout;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.convert.ListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class PropertiesFile extends FileObject{
    public static Logger LOGGER = Logger.getLogger(PropertiesFile.class);

    protected static final String DEFAULT_FILE_NAME = "configuration";
    protected static final String DEFAULT_PROJECT_FOLDER = "/src/test/resources/properties/";
    protected static final String PROPERTIES_EXTENSION = ".properties";
    protected static final String DEFAULT_ENVIRONMENT = "default";
    protected static Charset encoding;

    protected Path defaultDirectory = Paths.get(System.getProperty("user.dir"))
            .resolve(DEFAULT_PROJECT_FOLDER);
    protected String valueSeparator = ",";

    {
        filePath = Paths.get(System.getProperty("user.dir"))
                .resolve(DEFAULT_PROJECT_FOLDER)
                .resolve(DEFAULT_FILE_NAME.concat(PROPERTIES_EXTENSION));
        currentFile = filePath.toFile();
    }

    public PropertiesFile(final String propsName, Path propertiesFolder){
        setPropertiesFile(propsName, propertiesFolder);
        try(FileReader fileReader = new FileReader(currentFile)){
            encoding = Charset.forName(fileReader.getEncoding());
        }catch (IOException e){
            LOGGER.info("Invalid Charset. Setting up the default charset: UTF-8");
            encoding = StandardCharsets.UTF_8;
        }
    }

    public PropertiesFile(File propsFile) throws IOException {
        validateFile(propsFile);
        try (FileReader fileReader = new FileReader(currentFile)) {
            encoding = Charset.forName(fileReader.getEncoding());
        }
    }

    public PropertiesFile(Path propsFilePath) throws IOException {
        validatePath(propsFilePath);
        try (FileReader fileReader = new FileReader(currentFile)) {
            encoding = Charset.forName(fileReader.getEncoding());
        }
    }

    private PropertiesFile(File propsFile, Charset encoding) throws IOException {
        validateFile(propsFile);
        PropertiesFile.encoding = encoding;
    }

    public PropertiesFile() {
    }

    public Charset getEncoding() {
        return encoding;
    }

    public void setEncoding(Charset encoding) {
        System.setProperty("file.encoding", encoding.name());
        PropertiesFile.encoding = encoding;
    }

    public String getValueSeparator() {
        return valueSeparator;
    }

    public void setValueSeparator(final String valueSeparator) {
        this.valueSeparator = valueSeparator;
    }

    public Path getDefaultDirectory() {
        return defaultDirectory;
    }

    public void setDefaultDirectory(Path defaultDirectory) {
        this.defaultDirectory = defaultDirectory;
    }

    public void setPropertiesFile(final String fileName) {
        String validfileName = Optional.ofNullable(fileName)
                .filter(StringUtils::isNotBlank).orElse(DEFAULT_FILE_NAME);
        filePath = defaultDirectory.resolve(validfileName);
        currentFile = filePath.toFile();
    }

    private void setPropertiesFile(final String propsName, Path propertiesFolder) {
        Optional.ofNullable(propertiesFolder)
                .filter(path -> StringUtils.isNotBlank(path.toString()))
                .ifPresent(path -> {
                    String validFileName = Optional.ofNullable(propsName)
                            .filter(fileName ->
                                    !fileName.equals(DEFAULT_ENVIRONMENT) && StringUtils.isNotBlank(fileName))
                            .orElse(DEFAULT_FILE_NAME);
                    filePath = path.resolve(validFileName.concat(PROPERTIES_EXTENSION));
                    currentFile = filePath.toFile();
                });
    }

    public static PropertiesFile create(Path fileDirectory, String fileName, Charset encoding) throws IOException {
        Path filePath = getFullFilePath(fileDirectory, fileName);
        return new PropertiesFile(Files.createFile(filePath).toFile(), encoding);
    }

    public static PropertiesFile forceCreate(Path fileDirectory, String fileName, Charset encoding) throws IOException {
        Path filePath = getFullFilePath(fileDirectory, fileName);
        Files.delete(filePath);
        return new PropertiesFile(Files.createFile(filePath).toFile(), encoding);
    }

    public static PropertiesFile create(Path fileDirectory, String fileName) throws IOException {
        return create(fileDirectory, fileName, encoding);
    }

    public static PropertiesFile forceCreate(Path fileDirectory, String fileName) throws IOException {
        return create(fileDirectory, fileName, encoding);
    }

    private PropertiesConfiguration initProperties(){
        PropertiesConfiguration configuration = new PropertiesConfiguration();
        ListDelimiterHandler delimiter = new DefaultListDelimiterHandler(valueSeparator.charAt(0));
        configuration.setListDelimiterHandler(delimiter);
        return configuration;
    }

    public String getFieldValue(final String key) {
        return getInputConfiguration().getString(key);
    }

    public String getFieldValue(final String key, String defaultValue) {
        return getInputConfiguration().getString(key, defaultValue);
    }

    private PropertiesConfiguration getInputConfiguration() {
        PropertiesConfiguration configuration = initProperties();
        PropertiesConfigurationLayout layout = new PropertiesConfigurationLayout();
        try (FileInputStream fileInputStream = new FileInputStream(currentFile)) {
            Reader fileReader = new InputStreamReader(fileInputStream, encoding);
            layout.load(configuration, fileReader);
        } catch (ConfigurationException | IOException e) {
            LOGGER.error("An error ocurred while reading the properties file. Error: \n".concat(e.getMessage()));
        }
        return configuration;
    }

    public String[] getFieldValuesAsArray(final String key) {
        return getInputConfiguration().getStringArray(key);
    }

    public List<String> getFieldValuesAsList(final String key) {
        return getInputConfiguration().getList(String.class, key);
    }

    public String getRandomFieldValue(final String key) {
        List<String> properties = getInputConfiguration().getList(String.class, key);
        int index = new Random().nextInt(properties.size());
        return properties.get(index);
    }

    public void updateFieldValue(final String key, final String newValue) {
        try {
            PropertiesConfiguration configuration = initProperties();
            PropertiesConfigurationLayout layout = getOutputConfiguration(configuration);
            configuration.setProperty(key, newValue);
            FileWriter fileWriter = new FileWriter(currentFile, false);
            layout.save(configuration, fileWriter);
        } catch (ConfigurationException | IOException e) {
            LOGGER.error("An error ocurred while writting the properties file. Error: \n".concat(e.getMessage()));
        }
    }

    public boolean isFieldPresent(final String key) {
        return StringUtils.isNotBlank(getInputConfiguration().getString(key));
    }

    public void addField(final String newKey, final String newValue) {
        try {
            PropertiesConfiguration configuration = initProperties();
            PropertiesConfigurationLayout layout = getOutputConfiguration(configuration);
            configuration.addProperty(newKey, newValue);
            FileWriter fileWriter = new FileWriter(currentFile, false);
            layout.save(configuration, fileWriter);
        } catch (ConfigurationException | IOException e) {
            LOGGER.error("An error ocurred while writting the properties file. Error: \n".concat(e.getMessage()));
        }
    }

    public void addFieldValues(final String key, String... newValues) {
        try {
            String[] actualValues = getFieldValuesAsArray(key);
            PropertiesConfiguration configuration = initProperties();
            PropertiesConfigurationLayout layout = getOutputConfiguration(configuration);
            configuration.setProperty(key, ArrayUtils.addAll(actualValues, newValues));
            FileWriter fileWriter = new FileWriter(currentFile, false);
            layout.save(configuration, fileWriter);
        } catch (ConfigurationException | IOException e) {
            LOGGER.error("An error ocurred while writting the properties file. Error: \n".concat(e.getMessage()));
        }
    }

    private PropertiesConfigurationLayout getOutputConfiguration(PropertiesConfiguration configuration) throws IOException, ConfigurationException {
        PropertiesConfigurationLayout layout = new PropertiesConfigurationLayout();
        try (FileReader fileReader = new FileReader(currentFile)) {
            layout.load(configuration, fileReader);
        }
        return layout;
    }

}
