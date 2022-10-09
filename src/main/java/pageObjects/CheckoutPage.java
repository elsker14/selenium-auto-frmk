package pageObjects;

import abstractComponents.AbstractHeader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutPage extends AbstractHeader {
    WebDriver driver;
    Actions actions;
    By resultsOptions = By.cssSelector(".ta-results");
    @FindBy(css = "[placeholder='Select Country']")
    private WebElement selectCountry;
    @FindBy(css = ".ta-results button")
    private List<WebElement> resultsOptionsList;
    @FindBy(css = ".actions a")
    private WebElement placeOrderBtnMe;
    @FindBy(css = ".action__submit")
    private WebElement placeOrderBtnCourse;
    @FindBy(xpath = "(//button[contains(@class, 'ta-item')])[2]")
    private WebElement countryCourse;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.actions = new Actions(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void searchAndSelectCountry(String findByCountryName) {
        actions.sendKeys(selectCountry, findByCountryName).build().perform();
        waitForElementToAppear(resultsOptions);
        countryCourse.click();
    }

    public void searchCountry(String findByCountryName) {
        selectCountry.sendKeys(findByCountryName);
        waitForElementToAppear(resultsOptions);
    }

    public List<WebElement> getResultsOptionsList() {
        return resultsOptionsList;
    }

    public void selectCountryByName(String findByCountryName) {
        WebElement country = getResultsOptionsList().stream()
                .filter(rb -> rb.getText().equalsIgnoreCase(findByCountryName))
                .findFirst().orElse(null);
        country.click();
    }

    public void placeOrderMe() {
        placeOrderBtnMe.click();
    }

    public void placeOrderCourse() {
        placeOrderBtnCourse.click();
    }
}
