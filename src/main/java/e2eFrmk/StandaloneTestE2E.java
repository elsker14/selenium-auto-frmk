package e2eFrmk;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

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

public class StandaloneTestE2E {
    static {
        WebDriverManager.chromedriver().setup();
    }

    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    @Test
    public void test() throws InterruptedException {
        String productName = "ZARA COAT 3";

        // Configure browsert and get to page
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");

        // Log in on page
        driver.findElement(By.id("userEmail")).sendKeys("iancujianu98@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Pernutepufoase14*");
        driver.findElement(By.id("login")).click();

        // Get all elements on the page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement product = products.stream()
                .filter(p -> p.findElement(By.cssSelector("b")).getText().contains(productName))
                .findFirst().orElse(null);

        // Click Add To Cart button
        product.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        // Check Add To Card notif appears
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

        // Click on Cart
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".cartSection .btn:first-child"))));

        // Get list of items in the cart
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        boolean match = cartProducts.stream().anyMatch(p -> p.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match, "Product is not in the cart list");

        // Click on Checkout button
        driver.findElement(By.cssSelector(".totalRow button")).click();

        // Assignament
//        mySolution();
        courseSolution();

        // Close browser
        driver.quit();
    }

    public void mySolution() throws InterruptedException {
        // Select Country dropdown
        driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("Ro");
        List<WebElement> resultsButtons = driver.findElements(By.cssSelector(".ta-results button"));
        WebElement country = resultsButtons.stream().filter(rb -> rb.getText().equals("Romania")).findFirst().orElse(null);
        country.click();

        // Click Place Order button
        driver.findElement(By.cssSelector(".actions a")).click();

        // Wait to see summary order page
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("tr button"))));
        Thread.sleep(2000);
    }

    public void courseSolution() {
        // Select Country dropdown
        Actions actions = new Actions(driver);
        actions
                .sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india")
                .build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2]")).click();

        // Click Place Order button
        driver.findElement(By.cssSelector(".action__submit")).click();

        // Wait to see summary order page
        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."), "Confirm message does not appear");
    }
}
