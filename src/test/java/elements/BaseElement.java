package elements;

import org.openqa.selenium.By;

public abstract class BaseElement {
    protected final By locator;
    protected final String name;

    public BaseElement(By locator, String name) {
        this.locator = locator;
        this.name = name;
    }
}
