package ui.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import ui.Actions.DynamicSelectionAction;
import ui.Base.DriverManager;

public class DynamicSelectionSteps {
    
    private WebDriver driver;
    private DynamicSelectionAction dynamicSelectionAction;
    
    public DynamicSelectionSteps()
    {
        this.driver= DriverManager.getDriver();
        this.dynamicSelectionAction =new DynamicSelectionAction(driver);

    }

    @Then("the product should be opened in a new tab")
    public void theProductShouldBeOpenedInANewTab() throws InterruptedException {
        dynamicSelectionAction.valProductOpensInANewTab();
    }

    @When("I click on the Add to Cart button if the product is available")
    public void iClickOnTheAddToCartButton() {
        dynamicSelectionAction.clickOnAddToCartBtn();
    }

    @Then("I should be navigated to the Shopping Cart page")
    public void iShouldBeNavigatedToTheShoppingCartPage() {
        dynamicSelectionAction.valNavToShoppingCartPage();
    }

    @Then("I validate the correct product is added to the cart")
    public void iValidateTheCorrectProductIsAddedToTheCart() {
       dynamicSelectionAction.valCorrectPrdctAdded();
    }

    @And("I calculate the total discount on the item")
    public void iCalculateTheTotalDiscountOnTheItem() {
        dynamicSelectionAction.calTotalDiscount();
    }

    @Then("it should match with the displayed discount")
    public void itShouldMatchWithTheDisplayedDiscount() {
        dynamicSelectionAction.valDiscount();
    }


    @And("I enter a product name {string} in the searchbox and hit enter")
    public void iEnterAProductNameInTheSearchboxAndHitEnter(String product) {

        dynamicSelectionAction.enterProductNameInSearchBox(product);
    }

    @Then("it should show the all the available products related to that product")
    public void itShouldShowTheAllTheAvailableProductsRelatedToThatProduct() {
        dynamicSelectionAction.getTheListOfAvlPrdcts();
    }

    @And("I click on the first product from the list")
    public void iClickOnTheFirstProductFromTheList() {
        dynamicSelectionAction.clickOnTheFirstProduct();
    }

    @Then("I validate the storage {string} of the product with my requirement")
    public void iValidateTheStorageOfTheProductWithMyRequirement(String storage) throws InterruptedException {
        dynamicSelectionAction.valStorageOfTheProduct(storage);
    }

    @Then("I validate the colour {string}of the product with my requirement")
    public void iValidateTheColourOfTheProductWithMyRequirement(String colour) {
        dynamicSelectionAction.valColourOfTheProduct(colour);

    }

    @When("I click on the Place Order button")
    public void iClickOnThePlaceOrderButton() {
        dynamicSelectionAction.clickOnThePlaceOrderBtn();
    }

    @Then("I should be navigated to the Checkout page")
    public void iShouldBeNavigatedToTheCheckOutPage() {
        dynamicSelectionAction.valNavToPaymentPage();

    }


}
