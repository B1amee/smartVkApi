package forms;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MyPage extends Form {

    public MyPage() {
        super(By.xpath("//*[@id='page_wall_posts']//*[contains(@id,'post')]"), "My page");
    }

}
