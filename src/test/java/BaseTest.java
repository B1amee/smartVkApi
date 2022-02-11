import framework.utils.ConfigManager;
import framework.utils.CredentialsManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeTest;

import static com.codeborne.selenide.Selenide.open;


public class BaseTest {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    protected String url = ConfigManager.getValue("url");
    protected String login = CredentialsManager.getValue("login");
    protected String password = CredentialsManager.getValue("password");
    protected int userId = Integer.parseInt(CredentialsManager.getValue("owner_id"));
    protected int letterCount = Integer.parseInt(ConfigManager.getValue("letter_count"));

    @BeforeTest
    public void beforeTest() {
        open(url);
    }
}
