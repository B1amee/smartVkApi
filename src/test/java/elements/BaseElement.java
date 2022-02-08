package elements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.apache.log4j.Logger;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public abstract class BaseElement {

    protected final By locator;
    protected final String name;
    protected final Logger log = Logger.getLogger(this.getClass());

    public BaseElement(By locator, String name) {
        this.locator = locator;
        this.name = name;
    }

    public boolean isDisplayed() {
        log.info("Check is element \"" + name + "\" Displayed");
        findElement().shouldBe(exist);
        return findElement().isDisplayed();
    }

    public void click() {
        log.info("Click the element \"" + name + "\"");
        findElement().shouldBe(exist).click();
    }

    public void scrollTo(boolean bool) {
        log.info("Scroll to the element \"" + name + "\"");
        findElement().scrollIntoView(bool);
    }

    protected SelenideElement findElement() {
        log.info("Find the element \"" + name + "\"");
        return $(locator);
    }

    public String getAttribute(String name) {
        log.info("Get attribute \"" + name  + "\" from the element \"" + this.name + "\"");
        return findElement().getAttribute(name);
    }

    public void waitForExist() {
        findElement().shouldBe(exist);
    }

    public void waitForVisible() {
        findElement().shouldBe(visible);
    }

    public void waitForHidden() {
        findElement().shouldBe(hidden);
    }

    @Override
    public String toString() {
        return "BaseElement{" +
                "locator=" + locator +
                ", name='" + name + '\'' +
                '}';
    }
}
