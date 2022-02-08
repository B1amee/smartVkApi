package tests;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import utils.DataManager;

import static com.codeborne.selenide.Selenide.open;


public class BaseTest {

    protected final Logger log = Logger.getLogger(this.getClass());

    @BeforeTest
    public void beforeTest() {
        String url = DataManager.getValue("url");
        open(url);
    }
}
