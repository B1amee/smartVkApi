package forms;

import org.openqa.selenium.By;
import org.apache.log4j.Logger;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public abstract class BaseForm {

    private By locator;
    private String name;
    protected final Logger log = Logger.getLogger(this.getClass());

    public BaseForm(By locator, String name) {
        this.locator = locator;
        this.name = name;
    }

    public boolean isDisplayed() {
        log.info("Page \"" + name + "\" is Displayed");
        $(locator).shouldBe(visible);
        return $(locator).isDisplayed();
    }
}
