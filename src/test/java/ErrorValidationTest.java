import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.ProductCataloguePage;
import testComponents.BaseTest;
import testComponents.Retry;

import java.io.IOException;
import java.util.List;


public class ErrorValidationTest extends BaseTest {

    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void LoginErrorValidation() throws IOException, InterruptedException {
        landingPage.loginApplication("anshika@gmail.com", "Iamki000");
        Assert.assertEquals("Incorrect email  password.", landingPage.getErrorMessage());
    }

    @Test(groups = {"ErrorHandling"})
    public void ProductErrorValidation() throws InterruptedException {
        String productName = "ZARA COAT 3";

        // Log in to app and go to product catalogue page
        ProductCataloguePage productCataloguePage = landingPage.loginApplication("iancujianu98@gmail.com", "Pernutepufoase14*");

        // Get all products on page
        List<WebElement> products = productCataloguePage.getProductList();

        // Add wished product to cart
        productCataloguePage.addProductToCart(productName);

        // Go to Cart Page
        Thread.sleep(2000);
        CartPage cartPage = productCataloguePage.goToCartPage();

        // Check if product is in cart list
        Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
        Assert.assertFalse(match);
    }
}
