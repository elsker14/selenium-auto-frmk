package pomFrmk;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pomFrmk.pageObjects.*;

import java.time.Duration;
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

public class StandalonePOMTestE2E {
    // Environment configuration
    static {
        WebDriverManager.chromedriver().setup();
    }

    WebDriver driver = new ChromeDriver();

    // POM initialization
    LandingPage landingPage = new LandingPage(driver);
    ProductCatalogPage productCatalogPage = new ProductCatalogPage(driver);
    CartPage cartPage = new CartPage(driver);
    CheckoutPage checkoutPage = new CheckoutPage(driver);
    SummaryOrderPage summaryOrderPage = new SummaryOrderPage(driver);

    // Test data initialization
    String url = "https://rahulshettyacademy.com/client";
    String productName = "ZARA COAT 3";

    @BeforeSuite(alwaysRun = true)
    public void setupTestSuite() {
        // Configure browser and get to page
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        landingPage.goTo(url);
    }

    @AfterSuite(alwaysRun = true)
    public void teardownTestSuite() throws InterruptedException {
        // Close browser
        driver.quit();
    }

    @Test(priority = 1)
    public void mySolutionTest() throws InterruptedException {
        commonTestPart();
        mySolution();
    }

    @Test(priority = 2)
    public void courseSolutionTest() throws InterruptedException {
        commonTestPart();
        courseSolution();
    }

    public void commonTestPart() throws InterruptedException {
        // Log in on page
        landingPage.loginApplication("iancujianu98@gmail.com", "Pernutepufoase14*");

        // Get all elements on the page
        List<WebElement> products = productCatalogPage.getProductList();
        WebElement product = productCatalogPage.getProductByName(productName);

        // Click Add To Cart button
        // Check Add To Card notification appears
        productCatalogPage.addProductToCart(productName);

        // Click on Cart and wait to see Buy now button
        Thread.sleep(2000);
        productCatalogPage.seeProductsInCart();

        // Get list of items in the cart
        List<WebElement> cartProducts = cartPage.getCartProducts();
        boolean match = cartPage.isProductInCart(productName);
        Assert.assertTrue(match, "Product is not in the cart list");

        // Click on Checkout button
        cartPage.checkout();
    }

    public void mySolution() throws InterruptedException {
        // Select Country dropdown
        checkoutPage.searchCountry("Ro");
        checkoutPage.selectCountryByName("Romania");

        // Click Place Order button
        checkoutPage.placeOrderMe();

        // Wait to see summary order page
        String confirmMessage = summaryOrderPage.getThankYouMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."), "Confirm message does not appear");
    }

    public void courseSolution() {
        // Select Country dropdown
        checkoutPage.searchAndSelectCountry("india");

        // Click Place Order button
        checkoutPage.placeOrderCourse();

        // Wait to see summary order page
        String confirmMessage = summaryOrderPage.getThankYouMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."), "Confirm message does not appear");
    }
}
