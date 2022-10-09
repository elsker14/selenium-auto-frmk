import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import testComponents.BaseTest;

import java.io.IOException;

/*
CSS Selector:
    .class
    tagName
    #id
    attribute
    [attribute='word'] (equals word)
    [attribute*='word'] (contains word)
 */

public class StandalonePOMTestE2EMySolution extends BaseTest {
    // POM initialization
    LandingPage landingPage = launchApplication();
    ProductCatalogPage productCatalogPage = new ProductCatalogPage(driver);
    CartPage cartPage = new CartPage(driver);
    CheckoutPage checkoutPage = new CheckoutPage(driver);
    SummaryOrderPage summaryOrderPage = new SummaryOrderPage(driver);

    public StandalonePOMTestE2EMySolution() throws IOException {
    }

    @Test
    public void mySolutionTest() throws InterruptedException {
        commonTestPart();
        mySolution();
    }

    public void commonTestPart() throws InterruptedException {
        String productName = "ZARA COAT 3";

        // Log in on page
        landingPage.loginApplication("iancujianu98@gmail.com", "Pernutepufoase14*");

        // Click Add To Cart button
        productCatalogPage.addProductToCart(productName);

        // Click on Cart and wait to see Buy now button
        Thread.sleep(2000);
        productCatalogPage.goToCart();

        // Get list of items in the cart
        boolean match = cartPage.isProductInCart(productName);
        Assert.assertTrue(match, "Product is not in the cart list");

        // Click on Checkout button
        cartPage.checkout();
    }

    public void mySolution() {
        // Select Country dropdown
        checkoutPage.searchCountry("Ro");
        checkoutPage.selectCountryByName("Romania");

        // Click Place Order button
        checkoutPage.placeOrderMe();

        // Wait to see summary order page
        String confirmMessage = summaryOrderPage.getThankYouMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."), "Confirm message does not appear");
    }
}
