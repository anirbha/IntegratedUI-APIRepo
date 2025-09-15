package ui.stepdefinitions;


import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import ui.Actions.PaginationAction;
import ui.Base.DriverManager;

public class PaginationSteps {
    private WebDriver driver;
    PaginationAction paginationAction;


    public PaginationSteps()
    {
        driver= DriverManager.getDriver();
        paginationAction=new PaginationAction(driver);
    }
    @Then("I validate the current page number")
    public void iValidateTheCurrentPageNumber() {
        paginationAction.printCurrentPageNumber();
    }

    @When("I click on a particular {string}")
    public void iClickOnAParticular(String pageno) {
        paginationAction.clickedOnPageNumber(pageno);
    }

    @Then("I should be navigated to that page")
    public void iShouldBeNavigatedToThatPage() throws InterruptedException {
       paginationAction.valCurrentPageNumber();
    }

    @When("I click on the {string} button")
    public void iClickOnTheButton(String button) {
        paginationAction.navigationBybutton(button);
    }

    @Then("I should be navigated to {string} page")
    public void iShouldBeNavigatedToPage(String button) throws InterruptedException {
        paginationAction.valNavBybutton(button);
    }

    @Then("I validate the {string} button is disabled")
    public void iValidateTheButtonIsDisabled(String button) {
        paginationAction.valBtnDisabled(button);
    }
}
