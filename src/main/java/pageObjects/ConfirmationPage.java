package pageObjects;

import abstractComponents.AbstractHeader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractHeader {
    WebDriver driver;

    @FindBy(css = ".hero-primary")
    WebElement confirmationMessage;

    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getConfirmationMessage() {
        CheckoutPage cp = new CheckoutPage(driver);
        return confirmationMessage.getText();
    }
}
