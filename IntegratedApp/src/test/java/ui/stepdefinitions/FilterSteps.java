package ui.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import ui.Actions.FilterAction;
import ui.Base.DriverManager;

import java.util.List;

public class FilterSteps {
    private WebDriver driver;
    private FilterAction filterAction;
    String filterType;

    public FilterSteps()
    {
        this.driver= DriverManager.getDriver();
        filterAction =new FilterAction(driver);

    }

    @And("I apply the {string} filter")
    public void iApplyTheFilter(String filterType) {
        this.filterType=filterType;
        filterAction.expandTheFilter(filterType);
    }

    @When("I check the {string} checkbox under the filter")
    public void iCheckTheCheckboxUnderTheFilter(String filtername) {
        filterAction.checkTheFilterCheckbox(filtername);
    }

    @Then("the products should be filtered based on the applied filter")
    public void theProductsShouldBeFilteredBasedOnTheAppliedFilter() throws InterruptedException {
        filterAction.validateProductsFiltered(filterType);
    }

    @And("I apply the minimum range of the price filter{string}")
    public void iApplyTheMinimumRangeOfThePriceFilter(String minimum)  {
        filterAction.applyMinRangeOfPriceFilter(minimum);
    }

    @And("I apply the maximum range of the price filter{string}")
    public void iApplyTheMaximumRangeOfThePriceFilter(String maximum) {
        filterAction.applyMaxRangeOfPriceFilter(maximum);
    }

    @Then("the products should be filtered based on the price filter")
    public void theProductsShouldBeFilteredBasedOnThePriceFilter() {
        filterAction.validateProductsFilteredOnPrice();
    }

    @Then("the products should be filtered based on the applied multiple filters")
    public void theProductsShouldBeFilteredBasedOnTheAppliedMultipleFilters() {
        filterAction.validateProductsFilteredOnMultipleFilters();
    }


    @When("I apply multiple filters:")
    public void iApplyMultipleFilters(DataTable dataTable) throws InterruptedException {
        filterAction.multipleFilters(dataTable);
    }

    @When("I click one of the products from the list based on our choice")
    public void iClickOneOfTheProductsFromTheListBasedOnOurChoice() {
        filterAction.clickOnFirstProduct();
    }

    @Then("I validate the brand of the product with my requirement")
    public void iValidateTheBrandOfTheProductWithMyRequirement() {
        filterAction.valTheProduct();
    }

    @When("I check the checkboxes under the filter")
    public void iCheckTheCheckboxesUnderTheFilter(DataTable dataTable) {
        List<String> values =dataTable.asList(String.class);
        filterAction.filterCheckBoxes(values);
    }

    @Then("the checkboxes values should be displayed")
    public void theCheckboxesShouldBeDisplayed() {
        filterAction.valFilterValues();
    }

    @When("I click on X any one filter {string}")
    public void iClickOnXAnyOneFilter(String removeFltr) {
        filterAction.removeOneFilterVal(removeFltr);
    }

    @Then("that filter should be removed")
    public void thatFilterShouldBeRemoved() {
        filterAction.valFilterValues();
    }

    @When("I click on Clear All link")
    public void iClickOnClearAllLink() {
        filterAction.clickOnClearAllFilter();
    }

    @Then("all the filters should be removed")
    public void allTheFiltersShouldBeRemoved() {
        filterAction.valFilterValues();
    }

    @Then("no products should be found")
    public void noProductsShouldBeFound() {
        filterAction.valNoProdsFound();

    }
}
