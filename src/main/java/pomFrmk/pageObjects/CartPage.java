package pomFrmk.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pomFrmk.abstractComponents.AbstractHeader;

import java.util.List;

public class CartPage extends AbstractHeader {
    WebDriver driver;
    By buyNowBy = By.cssSelector(".cartSection .btn:first-child");
    @FindBy(css = ".cartSection .btn:first-child")
    private WebElement buyNow;
    @FindBy(css = ".cartSection h3")
    private List<WebElement> cartProducts;
    @FindBy(css = ".totalRow button")
    private WebElement checkout;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public List<WebElement> getCartProducts() {
        waitForElementToAppear(buyNowBy);
        return cartProducts;
    }

    public boolean isProductInCart(String productName) {
        return cartProducts.stream().anyMatch(p -> p.getText().equalsIgnoreCase(productName));
    }

    public void checkout() {
        checkout.click();
    }
}
