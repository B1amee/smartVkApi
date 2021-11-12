package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class FeedPage extends Form {

    private final IButton myPage = getElementFactory().getButton(By.xpath("//*[@id='l_pr']"), "My Page");

    protected FeedPage() {
        super(By.xpath("//*[@id='l_pr']"), "Feed page");
    }

    public void clickMyPage() {
        myPage.clickAndWait();
    }
}
