import framework.utils.ConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeTest;
import framework.utils.DataManager;

import static com.codeborne.selenide.Selenide.open;


public class BaseTest {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @BeforeTest
    public void beforeTest() {
        String url = ConfigManager.getValue("url");
        open(url);
    }
}
