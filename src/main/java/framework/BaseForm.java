package framework;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public abstract class BaseForm {

    private final By locator;
    private final String name;
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

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
