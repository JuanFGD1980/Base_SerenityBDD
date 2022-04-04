package co.com.sofka.hibot.stepdefinitions.ui.login;

import co.com.sofka.hibot.stepdefinitions.Setup;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;

import java.util.Properties;

import static co.com.sofka.hibot.task.ui.login.Login.login;
import static co.com.sofka.hibot.task.ui.login.OpenHibotPage.openHibotPage;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class StepExample extends Setup {


    public static Logger LOGGER = Logger.getLogger(StepExample.class);
    Properties properties = new Properties();



    @Given("the user is on the page of hibot")
    public void theUserIsOnThePageOfHibot() {
        try {
            actorSetupTheBrowser(actor.getName());
            theActorInTheSpotlight().wasAbleTo(
                    openHibotPage()
            );
            LOGGER.info("Carga la pagina de inicio");
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
            Assertions.fail("");
        }

    }
    @When("the User enters the username and password")
    public void theUserEntersTheUsernameAndPassword() {

        try {
            theActorInTheSpotlight().wasAbleTo(
                    login()
                            .WithUser("1")
                            .andPassword("2")
            );
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
