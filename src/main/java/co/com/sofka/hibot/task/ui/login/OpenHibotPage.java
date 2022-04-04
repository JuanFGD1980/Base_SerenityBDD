package co.com.sofka.hibot.task.ui.login;

import co.com.sofka.hibot.userinterfaces.HibotPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class OpenHibotPage implements Task {

    private HibotPage hibotPage;

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.browserOn(hibotPage)
        );
    }

    public static OpenHibotPage openHibotPage(){
        return new OpenHibotPage();
    }
}
