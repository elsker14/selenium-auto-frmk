import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.ProductCataloguePage;
import testComponents.BaseTest;

import java.io.IOException;
import java.util.List;


public class ErrorValidationTest extends BaseTest {

    @Test
    public void LoginErrorValidation() throws IOException, InterruptedException {
        landingPage.loginApplication("anshika@gmail.com", "Iamki000");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    }

    @Test
    public void ProductErrorValidation() {
        String productName = "ZARA COAT 3";
        ProductCataloguePage productCataloguePage = landingPage.loginApplication("iancujianu98@gmail.com", "Pernutepufoase14*");
        List<WebElement> products = productCataloguePage.getProductList();
        productCataloguePage.addProductToCart(productName);
        CartPage cartPage = productCataloguePage.goToCartPage();
        Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
        Assert.assertFalse(match);
    }
}
