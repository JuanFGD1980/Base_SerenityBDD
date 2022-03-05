package co.com.sofka.hibot.stepdefinitions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

public class Setup {
    @Managed()
    protected WebDriver webDriver;
    protected Actor actor = new Actor("The User");

    private void setupUser(String name, WebDriver webDriver){
        OnStage.setTheStage(new OnlineCast());
        theActorCalled(name).can(BrowseTheWeb.with(webDriver));
    }
    protected  void  actorSetupTheBrowser(String name){
        setupUser(name, webDriver);
    }
}
