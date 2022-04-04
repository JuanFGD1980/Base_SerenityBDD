package co.com.sofka.hibot.task.ui.login;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static co.com.sofka.hibot.userinterfaces.ui.login.PageLogin.*;

public class LoginHitbotPage implements Task {

    private String user;
    private String password;

    public LoginHitbotPage WithUser(String user) {
        this.user = user;
        return this;
    }

    public LoginHitbotPage andPassword(String password) {
        this.password = password;
        return this;
    }



    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Click.on(USER_FIELD),
                Enter.theValue(user).into(USER_FIELD),
                Click.on(PASSWORD_FIELD),
                Enter.theValue(password).into(PASSWORD_FIELD),
                Click.on(LOGIN_BUTTON)
        );
    }

    public static LoginHitbotPage loginHitbotPage(){
        return new LoginHitbotPage();
    }

}
