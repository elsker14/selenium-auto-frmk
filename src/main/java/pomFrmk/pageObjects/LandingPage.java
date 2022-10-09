package pomFrmk.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pomFrmk.abstractComponents.AbstractHeader;

public class LandingPage extends AbstractHeader {
    private WebDriver driver;
    // PageFactory
    @FindBy(id = "userEmail")
    private WebElement userEmail;
    @FindBy(id = "userPassword")
    private WebElement userPassword;
    @FindBy(id = "login")
    private WebElement submit;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void goTo(String url) {
        driver.get(url);
    }

    public void loginApplication(String email, String password) {
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        submit.click();
    }
}
