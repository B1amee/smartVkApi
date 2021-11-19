package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class FeedPage extends Form {

    private static final By locator = By.xpath("//*[@id='l_pr']");

    private final IButton myPage = getElementFactory().getButton(By.xpath("//*[@id='l_pr']"), "My Page");

    public FeedPage() {
        super(locator, "Feed page");
    }

    public void clickMyPage() {
        myPage.clickAndWait();
    }
}
