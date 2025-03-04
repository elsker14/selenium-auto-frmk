package tddTestNG.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tddTestNG.abstractComponents.AbstractHeader;

import java.util.List;

public class ProductCataloguePage extends AbstractHeader {
    private WebDriver driver;

    public ProductCataloguePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    By productsBy = By.cssSelector(".mb-3");
    By productNameBy = By.cssSelector("b");
    By addToCartBy = By.cssSelector(".card-body button:last-of-type");
    By addToCartNotifBy = By.cssSelector("#toast-container");

    @FindBy(css = ".mb-3")
    private List<WebElement> products;
    @FindBy(css = ".ng-animating")
    private WebElement spinner;

    public List<WebElement> getProductList() {
        waitForElementToAppear(productsBy);
        return products;
    }

    public WebElement getProductByName(String productName) {
        return getProductList().stream()
                .filter(p -> p.findElement(productNameBy).getText().contains(productName))
                .findFirst().orElse(null);
    }

    public void addProductToCart(String productName) {
        WebElement product = getProductByName(productName);
        product.findElement(addToCartBy).click();
        waitForElementToAppear(addToCartNotifBy);
        waitForWebElementToDisappear(spinner);
    }
}
