package co.com.sofka.hibot.runner.ui;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/"},
        glue = {"co.com.sofka.hibot.stepdefinitions"},
        tags = "@contacto1"
)

public class TestRunner {
}
