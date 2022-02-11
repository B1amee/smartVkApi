package project.forms;

import framework.BaseForm;
import framework.elements.Button;
import framework.elements.TextBox;
import org.openqa.selenium.By;

public class WelcomePage extends BaseForm {

    private static final By locator = By.xpath("//*[@id='index_email']");

    private final TextBox loginField = new TextBox(locator, "Login field");
    private final TextBox passwordField = new TextBox(By.xpath("//*[@id='index_pass']"), "Password field");
    private final Button signInBtm = new Button(By.xpath("//*[@id='index_login_button']"), "Sign in button");

    public WelcomePage() {
        super(locator, "Authentication Page");
    }

    public void setLoginAndPassword(String login, String password) {
        loginField.type(login);
        passwordField.type(password);
    }

    public void clickSignInBtm() {
        signInBtm.click();
    }

}
