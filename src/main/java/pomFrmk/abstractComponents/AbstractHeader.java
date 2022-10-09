package pomFrmk.abstractComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class AbstractHeader extends AbstractBase {
    WebDriver driver;
    @FindBy(css = "[routerlink*='cart']")
    private WebElement cart;

    public AbstractHeader(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void goToCartPage() {
        cart.click();
    }
}
