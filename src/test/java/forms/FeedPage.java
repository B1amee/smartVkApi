package forms;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;

public class FeedPage extends BaseForm {

    private static final By locator = By.xpath("//*[@id='l_pr']");

    private final By myPage = By.xpath("//*[@id='l_pr']"); // "My Page";

    public FeedPage() {
        super(locator, "Feed page");
    }

    public void clickMyPage() {
//        myPage.clickAndWait();
        $(myPage).shouldBe(exist).click();
    }
}
