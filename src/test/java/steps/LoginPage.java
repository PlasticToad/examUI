package steps;

import elements.LoginPageElements;
import io.qameta.allure.Step;


public class LoginPage extends LoginPageElements {
    @Step("Авторизация в системе")
    public static void authorization(String login, String pass) {
        if (userName.exists()) {
            userName.sendKeys(login);
            password.sendKeys(pass);
            enterBtn.click();
        }
    }
}
