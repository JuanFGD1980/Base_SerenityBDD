package co.com.sofka.hibot.stepdefinitions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.Managed;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;

import static com.google.common.base.StandardSystemProperty.USER_DIR;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

public class Setup {
    @Managed()
    protected WebDriver webDriver;
    protected Actor actor = new Actor("The User");

    private void setupUser(String name, WebDriver webDriver){
        PropertyConfigurator.configure(USER_DIR.value() + "\\src\\test\\resources\\log4j.properties");
        OnStage.setTheStage(new OnlineCast());
        theActorCalled(name).can(BrowseTheWeb.with(webDriver));
    }
    protected  void  actorSetupTheBrowser(String name){
        setupUser(name, webDriver);
    }
}
