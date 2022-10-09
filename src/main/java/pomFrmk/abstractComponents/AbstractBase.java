package pomFrmk.abstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractBase {
    WebDriver driver;
    WebDriverWait wait;

    public AbstractBase(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
    }

    public void waitForElementToAppear(By findByLocator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(findByLocator));
    }

    public void waitForWebElementToAppear(WebElement findByWebElement) {
        wait.until(ExpectedConditions.visibilityOf(findByWebElement));
    }

    public void waitForElementToDisappear(By findByLocator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(findByLocator));
    }

    public void waitForWebElementToDisappear(WebElement findByWebElement) {
        wait.until(ExpectedConditions.invisibilityOf(findByWebElement));
    }

}
