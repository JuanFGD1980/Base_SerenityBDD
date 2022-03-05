package co.com.sofka.hibot.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static co.com.sofka.hibot.task.OpenHibotPage.openHibotPage;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class StepExample extends Setup{

    @Given("the user is on the page of hibot")
    public void theUserIsOnThePageOfHibot() {
        actorSetupTheBrowser(actor.getName());
        theActorInTheSpotlight().wasAbleTo(
                openHibotPage()
        );

    }
    @When("the User enters the username and password")
    public void theUserEntersTheUsernameAndPassword() {

    }
    @Then("the user must observe her information when entering")
    public void theUserMustObserveHerInformationWhenEntering() {

    }




}
