package tests;

import forms.WelcomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VkApiTest{

    @Test(priority = 1)
    public void goToSite() {
        WelcomePage welcomePage = new WelcomePage();
        Assert.assertTrue(welcomePage.isDisplayed(), "Welcome Page is not open");
    }
}
