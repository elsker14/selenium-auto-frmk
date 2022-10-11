import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.*;
import testComponents.BaseTest;

import java.util.HashMap;
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

public class SubmitOrderModel1Test extends BaseTest {
    String productName = "ZARA COAT 3";

    @Test(dataProvider = "getDataUsingHashMap", groups = {"Purchase"})
    public void submitOrder(HashMap<String, String> inputDP) throws InterruptedException {
        // Log in to app and go to product catalogue page
        ProductCataloguePage productCataloguePage = landingPage.loginApplication(inputDP.get("emailDP"), inputDP.get("passwordDP"));

        // Get all products on page
        List<WebElement> products = productCataloguePage.getProductList();

        // Add wished product to cart
        productCataloguePage.addProductToCart(inputDP.get("productNameDP"));
        Thread.sleep(3000);

        // Go to Cart Page
        CartPage cartPage = productCataloguePage.goToCartPage();

        // Check if product is in cart list
        Boolean match = cartPage.verifyProductDisplay(inputDP.get("productNameDP"));
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

    /* Data Provider using HashMap */
    @DataProvider
    public Object[][] getDataUsingHashMap() {
        // In test you will have 1 argument that will get each value from map
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("emailDP", "iancujianu98@gmail.com");
        map1.put("passwordDP", "Pernutepufoase14*");
        map1.put("productNameDP", "ZARA COAT 3");

        HashMap<String, String> map2 = new HashMap<>();
        map2.put("emailDP", "iancujianu.works@gmail.com");
        map2.put("passwordDP", "Marcelcuceritorul14*");
        map2.put("productNameDP", "ADIDAS ORIGINAL");

        return new Object[][]{
                {map1},
                {map2}
        };
    }
}
