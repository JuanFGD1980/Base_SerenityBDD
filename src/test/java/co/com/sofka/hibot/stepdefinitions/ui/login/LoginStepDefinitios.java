package co.com.sofka.hibot.stepdefinitions.ui.login;

import co.com.sofka.hibot.controllers.ui.LoginController;
import co.com.sofka.hibot.controllers.ui.StartBrowserWebController;
import co.com.sofka.hibot.task.Setup;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;

import java.util.Properties;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class LoginStepDefinitios extends Setup {


    public static Logger LOGGER = Logger.getLogger(LoginStepDefinitios.class);
    Properties properties = new Properties();



    @Given("the user is on the page of hibot")
    public void theUserIsOnThePageOfHibot() {
        try {
            StartBrowserWebController startBrowserWebController = new StartBrowserWebController();
            startBrowserWebController.OpenHitbotPage();
            LOGGER.info("Carga la pagina de inicio");
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
            Assertions.fail("");
        }

    }
    @When("the User enters the username and password")
    public void theUserEntersTheUsernameAndPassword() {

        try {
            LoginController loginController = new LoginController();
            loginController.LoginPage();
            LOGGER.info("Registro en la pagina");
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
            Assertions.fail("");

        }
    }
    @Then("the user must observe her information when entering")
    public void theUserMustObserveHerInformationWhenEntering() {
        try {

            theActorInTheSpotlight().wasAbleTo(

            );
            LOGGER.info("Verificacion");
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
            Assertions.fail("");
        }
    }




}
