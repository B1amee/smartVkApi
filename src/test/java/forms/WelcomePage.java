package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class WelcomePage extends Form {

    private static final By locator = By.xpath("//*[@id='index_email']");

    private final ITextBox loginField = getElementFactory().getTextBox(By.xpath("//*[@id='index_email']"), "Login field");
    private final ITextBox passwordField = getElementFactory().getTextBox(By.xpath("//*[@id='index_pass']"), "Password field");
    private final IButton signInBtm = getElementFactory().getButton(By.xpath("//*[@id='index_login_button']"), "Sign in button");

    public WelcomePage() {
        super(locator, "Authentication Page");
    }

    public void setLoginAndPassword(String login, String password) {
        loginField.typeSecret(login);
        passwordField.typeSecret(password);
    }

    public void clickSignInBtm() {
        signInBtm.clickAndWait();
    }

}
