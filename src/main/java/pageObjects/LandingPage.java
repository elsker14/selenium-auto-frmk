package pageObjects;

import abstractComponents.AbstractHeader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractHeader {
    private WebDriver driver;
    // PageFactory
    @FindBy(id = "userEmail")
    private WebElement userEmail;
    @FindBy(id = "userPassword")
    private WebElement userPassword;
    @FindBy(id = "login")
    private WebElement submit;

    @FindBy(css = "[class*='flyInOut']")
    private WebElement errorMessage;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client");
    }

    public void loginApplication(String email, String password) {
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        submit.click();
    }

    public String getErrorMessage() {
        waitForWebElementToAppear(errorMessage);
        return errorMessage.getText();
    }
}
