package co.com.sofka.hibot.controllers.ui;

import co.com.sofka.hibot.task.Setup;

import static co.com.sofka.hibot.task.ui.login.OpenHibotPage.openHibotPage;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class StartBrowserWebController extends Setup {

    public void OpenHitbotPage(){
        actorSetupTheBrowser(actor.getName());
        theActorInTheSpotlight().wasAbleTo(
                openHibotPage()
        );
    }
}
