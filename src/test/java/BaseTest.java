import org.testng.annotations.BeforeTest;
import utils.ConfigManager;
import utils.RestAssuredUtil;

public abstract class BaseTest {

    @BeforeTest
    public void setUp() {
        RestAssuredUtil.setUrl(ConfigManager.getProperties("url"));
    }
}
