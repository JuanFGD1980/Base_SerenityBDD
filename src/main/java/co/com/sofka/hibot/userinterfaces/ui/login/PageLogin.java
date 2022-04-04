package co.com.sofka.hibot.userinterfaces.ui.login;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class PageLogin extends PageObject {

    public static final Target USER_FIELD =
            Target.the("campo para ingresar usuario")
                    .located(By.id("email"));

    public static final Target PASSWORD_FIELD =
            Target.the("campo para ingresar contrasenna")
                    .located(By.id("password"));

    public static final Target LOGIN_BUTTON =
            Target.the(" boton para iniciar sesión")
                    .located(By.id("btn-login"));

}
