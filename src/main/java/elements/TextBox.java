package elements;

import org.openqa.selenium.By;

public class TextBox  extends BaseElement{
    public TextBox(By locator, String name) {
        super(locator, name);
    }

    public String getText() {
        log.info("Get the visible text of the element \"" + name + "\"");
        return findElement().getText();
    }

    public void type(String str) {
        findElement().setValue(str);
    }
}
