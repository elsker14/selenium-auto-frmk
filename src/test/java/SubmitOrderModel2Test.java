import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.*;
import testComponents.BaseTest;

import java.util.List;

/*
CSS Selector:
    .class
    tagName
    #id
    attribute
    [attribute='word'] (equals word)
    [attribute*='word'] (contains word)
 */

public class SubmitOrderModel2Test extends BaseTest {
    String productName = "ZARA COAT 3";

    @Test(dataProvider = "getDataUsingObjectMatrix", groups = {"Purchase"})
    public void submitOrder(String emailDP, String passwordDP, String productNameDP) throws InterruptedException {
        // Log in to app and go to product catalogue page
        ProductCataloguePage productCataloguePage = landingPage.loginApplication(emailDP, passwordDP);

        // Get all products on page
        List<WebElement> products = productCataloguePage.getProductList();

        // Add wished product to cart
        productCataloguePage.addProductToCart(productNameDP);
        Thread.sleep(3000);

        // Go to Cart Page
        CartPage cartPage = productCataloguePage.goToCartPage();

        // Check if product is in cart list
        Boolean match = cartPage.verifyProductDisplay(productNameDP);
        Assert.assertTrue(match);

        // Go to Checkout Page
        CheckoutPage checkoutPage = cartPage.goToCheckout();

        // Fill in order details
        checkoutPage.selectCountry("india");

        // Submit order
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();

        // Check confirmation message appears
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void OrderHistoryTest() {
        // Log in to app and go to product catalogue page
        ProductCataloguePage productCataloguePage = landingPage.loginApplication("anshika@gmail.com", "Iamking@000");

        // Go to Orders Page
        OrderPage ordersPage = productCataloguePage.goToOrdersPage();

        // Check order made previously appears in the list
        Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
    }

    @DataProvider
    public Object[][] getDataUsingObjectMatrix() {
        // In test you will have 3 arguments that will take each value
        return new Object[][]{
                {"iancujianu98@gmail.com", "Pernutepufoase14*", "ZARA COAT 3"},
                {"iancujianu.works@gmail.com", "Marcelcuceritorul14*", "ADIDAS ORIGINAL"}
        };
    }
}
