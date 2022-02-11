package framework.elements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public abstract class BaseElement {

    protected final By locator;
    protected final String name;
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    public BaseElement(By locator, String name) {
        this.locator = locator;
        this.name = name;
    }

    public boolean isDisplayed() {
        log.info("Check is element \"" + name + "\" Displayed");
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
        log.info("Find the element \"" + name + "\" by xpath " + locator.toString());
        return $(locator);
    }

    public String getAttribute(String name) {
        log.info("Get attribute \"" + name  + "\" from the element \"" + this.name + "\"");
        return findElement().getAttribute(name);
    }

    public void waitForExist() {
        log.info("Wait for exist \"" + name  + "\" from the element \"" + this.name + "\"");
        findElement().shouldBe(exist);
    }

    public void waitForVisible() {
        log.info("Wait for visible \"" + name  + "\" from the element \"" + this.name + "\"");
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
