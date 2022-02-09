package forms;

import elements.Button;
import org.openqa.selenium.By;

public class FeedPage extends BaseForm {

    private static final By locator = By.xpath("//*[@id='l_pr']");

    private final Button myPage = new Button(locator, "My Page");

    public FeedPage() {
        super(locator, "Feed page");
    }

    public void clickMyPage() {
        myPage.click();
    }
}
