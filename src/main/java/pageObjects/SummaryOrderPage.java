package pageObjects;

import abstractComponents.AbstractHeader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SummaryOrderPage extends AbstractHeader {
    WebDriver driver;
    By downloadOrderDetailsBy = By.cssSelector("tr button");
    @FindBy(css = ".hero-primary")
    private WebElement thankYouMessage;

    public SummaryOrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public String getThankYouMessage() {
        waitForElementToAppear(downloadOrderDetailsBy);
        return thankYouMessage.getText();
    }
}
