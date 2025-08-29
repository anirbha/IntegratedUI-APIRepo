package ui.stepdefinitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import ui.Actions.LoginActions;
import ui.Base.DriverManager;

public class LoginSteps {

    private WebDriver driver;
    private LoginActions loginAction;

    public LoginSteps()
    {
        this.driver= DriverManager.getDriver();
        this.loginAction=new LoginActions(driver);

    }
    @Given("as a user I launch the website")
    public void asAUserILaunchTheWebsite() {
        loginAction.launchurl();

    }

    @Then("I validate the title of the page")
    public void iValidateTheTitleOfThePage() {
        loginAction.validateTitle();
    }
}
