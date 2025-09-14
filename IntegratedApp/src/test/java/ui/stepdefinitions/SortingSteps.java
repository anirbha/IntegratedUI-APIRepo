package ui.stepdefinitions;


import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import ui.Actions.SortingAction;
import ui.Base.DriverManager;

public class SortingSteps {

    private WebDriver driver;
    private SortingAction sortingAction;


    public SortingSteps()
    {
        this.driver= DriverManager.getDriver();
        sortingAction =new SortingAction(driver);

    }

    @Then("I validate different available sorting options")
    public void iValidateDifferentAvailableSortingOptions() {
        sortingAction.printALLSortingOptions();
    }

    @When("I click on the {string} sorting to validate sorting")
    public void iClickOnTheSortingToValidateSorting(String sorting) {
       sortingAction.clickOnTheSortingOption(sorting);
    }

    @Then("the product should be sorted according to {string}")
    public void theProductShouldBeSortedAccordingTo(String sorting) throws InterruptedException {
        sortingAction.valSortingFunctionality(sorting);
    }

    @When("I reload the page")
    public void iReloadThePage() {
        sortingAction.reloadPage();
    }
}
