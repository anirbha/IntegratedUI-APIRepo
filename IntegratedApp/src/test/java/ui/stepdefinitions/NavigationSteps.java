package ui.stepdefinitions;


import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import ui.Actions.NavigationActions;
import ui.Base.DriverManager;

public class NavigationSteps {
    private WebDriver driver;
    private NavigationActions navActions;

    public NavigationSteps()
    {
        this.driver= DriverManager.getDriver();
        this.navActions=new NavigationActions(driver);

    }
    @When("I mousehover on the Navigation dropdown{string}")
    public void iMousehoverOnTheNavigationDropdown(String dropdown) {
        navActions.hoverOnDropdown(dropdown);
    }

    @Then("I should see all the options in the dropdown")
    public void iShouldSeeAllTheOptionsInTheDropdown() {
        navActions.valNavOptionsInDropdown();
    }

    @When("I mousehover on any option {string}")
    public void iMousehoverOnAnyOption(String option) {
        navActions.hoverOnCatagory(option);
    }

    @Then("I should see all the sub options for that option")
    public void iShouldSeeAllTheSubOptionsForThatOption() {
        navActions.valSubOptionsInCatagory();
    }

    @When("I click on any option from the dropdown {string}")
    public void iClickOnAnyOptionFromTheDropdown(String subOption) {
        navActions.clickOnDropdownOption(subOption);
    }

    @Then("I should be navigated to the respective page from navigation {string}")
    public void iShouldBeNavigatedToTheRespectivePage(String page) {
       navActions.validateNavToRespectivePage(page);
    }


    @When("I click on any option from the Navigation bar {string}")
    public void iClickOnAnyOptionFromTheNavigationBar(String navOption) {
        navActions.clickOnNavBarOption(navOption);

    }

    @Then("I validate any particular brand's header is present or not {string}")
    public void iValidateAnyParticularBrandHeaderIsPresentOrNot(String brand) {
        navActions.valParticularBrandHeaders(brand);
    }

    @Then("I validate any particular brand's products are present or not {string}")
    public void iValidateAnyParticularBrandSProductsArePresentOrNot(String brand) {
        navActions.valParticularBrandProducts(brand);
    }

    @Then("I should be navigated to the respective page")
    public void iShouldBeNavigatedToTheRespectivePage() {
       navActions.valPageNavigation();
    }

    @Then("I validate any particular brand's header is not present {string}")
    public void iValidateAnyParticularBrandSHeaderIsNotPresent(String brand) {
        navActions.valParticularBrandHeadersNotPresent(brand);
    }
}
