package co.com.sofka.hibot.controllers.ui;

import co.com.sofka.hibot.task.Setup;

import static co.com.sofka.hibot.task.ui.login.LoginHitbotPage.loginHitbotPage;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class LoginController extends Setup {

    public void LoginPage(){
        theActorInTheSpotlight().wasAbleTo(
                loginHitbotPage()
                        .WithUser("1")
                        .andPassword("2")
        );
    }


}
