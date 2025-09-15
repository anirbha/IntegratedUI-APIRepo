package ui.stepdefinitions;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import ui.Actions.CompareAction;
import ui.Base.DriverManager;

public class CompareSteps {

    private WebDriver driver;
    CompareAction compareAction;

    public CompareSteps()
    {
        driver= DriverManager.getDriver();
        compareAction=new CompareAction(driver);
    }
    @And("I check the compare checkbox for the product")
    public void iCheckTheCompareCheckboxForTheProduct() {
        compareAction.clickOnTheCompareChkBoxInProductPg();
    }

    @When("I click on the compare pop up")
    public void iClickOnTheComparePopUp() {
        compareAction.clickOnComparePopUp();
    }

    @Then("I should be able to navigate to the compare page")
    public void iShouldBeAbleToNavigateToTheComparePage() {
        compareAction.valComparePgNav();
    }

    @Then("the products should be present{string}{string}")
    public void theProductsShouldBePresent(String product1, String product2) {
        compareAction.valComparedProds(product1,product2);
    }

    @And("I check the compare checkboxes of the first {int} products")
    public void iCheckTheCompareCheckboxesOfTheFirstProducts(int num) {
        compareAction.addToCompareCheckLists(num);
    }

    @Then("I validate both model has same price")
    public void iValidateWhichModelHasHigherPrice() {
        compareAction.valSameModelWithPrice();
    }

    @When("I add a new product to compare from the compare page {string}{string}")
    public void iAddANewProductToCompareFromTheComparePage(String brandname, String modelname) {
        compareAction.addNewProductInComparePg(brandname,modelname);
    }

    @Then("I should be able to add the product")
    public void iShouldBeAbleToAddTheProduct() {
       compareAction.productAdded();
    }

    @When("I click on the X link on one product")
    public void iClickOnTheXLinkOnOneProduct() {
        compareAction.clickOnCrossLink();
    }
    @Then("there should be only on product{string}")
    public void thereShouldBeOnlyOnProduct(String product) {
        compareAction.valOnlyOneProduct(product);
    }

    @When("I try to check the compare checkbox of the {int}st product")
    public void iTryToCheckTheCompareCheckboxOfTheStProduct(int num) {
        compareAction.addToCompareCheckLists(num);
    }

    @Then("it should show error message that it can't be added")
    public void itShouldShowErrorMessageThatItCanTBeAdded() {
        compareAction.valOfErrorMsg();
    }

    @When("I try to check the compare checkbox of product")
    public void iTryToCheckTheCompareCheckboxOfProduct() {
        compareAction.clickOnTheCompareChkBoxInProductPg();
    }
}
