package bddCucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import tddTestNG.pageObjects.*;
import tddTestNG.testComponents.BaseTest;

import java.io.IOException;
import java.util.List;

public class StepDefinitionImpl extends BaseTest {

    public LandingPage landingPage;
    public ProductCataloguePage productCataloguePage;
    public CartPage cartPage;
    public CheckoutPage checkoutPage;
    public ConfirmationPage confirmationPage;

    @Given("I landed on Ecommerge Page")
    public void landOnEcommPage() throws IOException {
        landingPage = launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void logInWithUserAndPass(String username, String password) {
        productCataloguePage = landingPage.loginApplication(username, password);
    }

    @When("^I add product (.+) to Cart$")
    public void addProductToCart(String productName) {
        List<WebElement> products = productCataloguePage.getProductList();
        productCataloguePage.addProductToCart(productName);
    }

    @And("^Checkout (.+) and submit the order$")
    public void checkoutAndSubmitOrder(String productName) {
        cartPage = productCataloguePage.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);

        checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("india");

        confirmationPage = checkoutPage.submitOrder();
    }

    @Then("{string} message is displayed on ConfirmationPage.")
    public void checkMessageIsDisplayedOnConfirmationPage(String message) {
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(message));
    }
}
