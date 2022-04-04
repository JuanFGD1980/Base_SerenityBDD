package co.com.sofka.hibot.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class FileObject {
    protected static final String NO_NAME_FILE = "NoNameFile";
    public static final String FILE_NOT_FOUND_TEMPLATE = "Invalid %s file. The file doesnt exist. Use %s.create(path,fileName) in order to create a new one.";
    static String fileNotFoundMsg = "Invalid object file. The file doesnt exist. Use FileObject.create(path,fileName) in order to create a new one.";
    public static final String INVALID_PATH_MSG = "Not a valid path.";
    File currentFile;
    Path filePath;

    public static String getFileNotFoundMsg() {
        return fileNotFoundMsg;
    }

    public static void setFileNotFoundMsg(String fileNotFoundMsg) {
        FileObject.fileNotFoundMsg = fileNotFoundMsg;
    }

    public void validateFile(File fileDirPath) throws IOException {
        currentFile = Optional.ofNullable(fileDirPath)
                .filter(File::exists)
                .orElseThrow(() -> new FileNotFoundException(fileNotFoundMsg));
        filePath = currentFile.toPath();
    }
    public void validateStringPath(String fileDirPath) throws IOException {
        String fileDirectory = Optional.ofNullable(fileDirPath)
                .orElse(StringUtils.EMPTY);
        this.filePath = Optional.of(Paths.get(fileDirectory))
                .orElseThrow(() -> new InvalidPathException(fileDirectory, INVALID_PATH_MSG));
        currentFile = Optional.of(this.filePath.toFile())
                .filter(File::exists)
                .orElseThrow(() -> new FileNotFoundException(fileNotFoundMsg));
    }
    public void validatePath(Path filePath) throws IOException {
        Path fileDirectory = Optional.ofNullable(filePath)
                .orElse(Paths.get(System.getProperty("user.dir")));
        this.filePath = Optional.of(fileDirectory)
                .orElseThrow(() -> new InvalidPathException(fileDirectory.toString(), INVALID_PATH_MSG));
        currentFile = Optional.of(this.filePath.toFile())
                .filter(File::exists)
                .orElseThrow(() -> new FileNotFoundException(fileNotFoundMsg));
    }
    protected static Path getFullFilePath(Path fileDirectory, String fileName) {
        String finalName = Optional.ofNullable(fileName)
                .orElse(NO_NAME_FILE);
        return Optional.ofNullable(fileDirectory)
                .orElse(Paths.get(System.getProperty("user.dir"))).resolve(finalName);
    }

    public void delete() throws IOException {
        Files.delete(currentFile.toPath());
    }
}
