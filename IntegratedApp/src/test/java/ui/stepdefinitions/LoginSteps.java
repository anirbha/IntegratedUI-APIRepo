package ui.stepdefinitions;



import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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

    @Then("validate the header of the website")
    public void validateTheCorrectWebsiteLaunchedProperly() {
        loginAction.validateTitle();
    }

    @Then("I validate the presence of Login CTA popup")
    public void iValidateThePresenceOfLoginCTAPopup() {
        loginAction.loginCTAValidation();

    }

    @When("I click on the Login button")
    public void iClickOnTheLoginCTA() {
        loginAction.loginBtnValidation();
        loginAction.clickOnLoginBtn();
    }

    @Then("it should show the Login text along with the enter mobile number textbox")
    public void itShouldShowTheLoginTextAlongWithTheEnterMobileNumberTextbox() {
        loginAction.valPresenceOfLoginTxtBox();
        loginAction.valLoginBoxTxt();
    }

    @When("I click on the Create an account link")
    public void iClickOnTheCreateAnAccountLink() {
       loginAction.clickOnCreateAnAccountLink();
    }

    @Then("it should show the Signup texts in the page")
    public void itShouldShowTheSignupTextsInThePage() {
        loginAction.valSignUpText();
    }

    @And("I enter the registered phone number in the phone number text box")
    public void iEnterTheRegisteredPhoneNumberInThePhoneNumberTextBox() {
       loginAction.enterPhoneNumber();
    }

    @When("I click on the Continue button")
    public void iClickOnTheContinueButton() {
       loginAction.clickOnTheContinueBtn();
    }

    @Then("it should show Login text again")
    public void itShouldShowLoginTextAgain() {
       loginAction.valLoginHeading();
       loginAction.valLoginTexts();
    }

    @And("I enter a phoneNo {string} in the phone number text box")
    public void iEnterAPhoneNoInThePhoneNumberTextBox(String phoneNo) {
        loginAction.enterPhoneNumber(phoneNo);

    }

    @Then("it should shown an error message")
    public void itShouldShownAnErrorMessage() {
        loginAction.valErrorMsg();
    }


}
