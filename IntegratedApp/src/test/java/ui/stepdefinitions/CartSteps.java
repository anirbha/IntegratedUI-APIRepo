package ui.stepdefinitions;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import ui.Actions.CartAction;
import ui.Actions.LoginActions;
import ui.Base.DriverManager;

public class CartSteps {

    private WebDriver driver;
    private CartAction cartAction;
    LoginActions loginActions;

    public CartSteps()
    {
        driver= DriverManager.getDriver();
        cartAction = new CartAction(driver);
        loginActions=new LoginActions(driver);
    }


    @And("I increase the number of the product added {string}")
    public void iIncreaseTheNumberOfTheProductAdded(String number) {
        cartAction.increaseTheNumber(number);

    }

    @Then("the number of products will be updated accordingly")
    public void theNumberOfProductsWillBeUpdatedAccordingly() {
        cartAction.valTheProductNumberUpdated();
    }

    @When("I try to add {string} of quantity")
    public void iTryToAddOfQuantity(String number) {
        cartAction.addingQuantity(number);
    }

    @Then("it should show alert message")
    public void itShouldShowAlertMessage() {
        cartAction.valUpperLimitAlertMessage();
    }

    @When("I click on the Remove link")
    public void iClickOnTheRemoveLink() {
       cartAction.clickOnTheRemoveLink();
    }

    @Then("the remove pop up should be appeared")
    public void theRemovePopUpShouldBeAppeared() {
       cartAction.validateRemovePopUp();
    }

    @When("I click on the {string} on the remove pop up")
    public void iClickOnTheOnTheRemovePopUp(String btnname) {
        cartAction.clickOnTheButton(btnname);
    }

    @Then("I validate the action based on the pressed button {string} {string}")
    public void iValidateTheActionBasedOnThePressedButton(String btn, String product) {
        cartAction.valBtnFunc(btn,product);

    }

    @When("I click on the {string} link")
    public void iClickOnTheLink(String link) {
        cartAction.clickOnTheLink(link);
    }

    @Then("the product should be added to Save for later table along with the confirmation message")
    public void theProductShouldBeAddedToSaveForLaterTableAlongWithTheConfirmationMessage() throws InterruptedException {
        cartAction.valAddedSaveFrLater();
    }

    @Then("the product should be added to that cart along with the confirmation message")
    public void theProductShouldBeAddedToThatCartAlongWithTheConfirmationMessage() {
        cartAction.valAddedMovedToCart();
    }

    @Then("it should not allow to reduce the quantity")
    public void itShouldNotAllowToReduceTheQuantity() {
        cartAction.valReduceTheQuantity();
    }

    @Then("the plus and minus icon should be disabled")
    public void thePlusAndMinusIconShouldBeDisabled() {
        cartAction.valIconsInSavedForLater();
    }

    @When("I click on the Home link")
    public void iClickOnTheHomeLink() {
       cartAction.clickOnTheHomeLink();
    }

    @Then("I should be navigated to the home page")
    public void iShouldBeNavigatedToTheHomePage() {
       loginActions.validateTitle();
    }

    @When("I click on the cart icon")
    public void iClickOnTheCartIcon() {
        cartAction.clickOnCartIcon();
    }

    @When("I validate the product is Out of stock")
    public void iValidateTheProductIsOutOfStock() {
        cartAction.valOutOfStockItem();
    }

    @Then("add to cart button should not be present")
    public void addToCartButtonShouldNotBePresent() {
        cartAction.valAddToCartbtnnopresent();
    }
}
