import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LandingPage;
import testComponents.BaseTest;

import java.io.IOException;


public class ErrorValidationPOMTestE2E extends BaseTest {

    LandingPage landingPage = launchApplication();

    public ErrorValidationPOMTestE2E() throws IOException {
    }

    @Test
    public void errorValidationTest() throws InterruptedException {
        landingPage.loginApplication("iancujianu98@gmail.coma", "Pernutepufoase14*");
        String errorMessage = landingPage.getErrorMessage();
        Assert.assertEquals(errorMessage, "Incorrect email or password.", "Message is incorrect");
        System.out.println(errorMessage);
    }
}
