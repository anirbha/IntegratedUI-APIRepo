package ui.Actions;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import ui.Pages.LoginPage;
import ui.Utils.ExtentManager;
import ui.Utils.Log;
import ui.Utils.TestUtils;
import ui.Utils.WaitUtils;

import static org.testng.Assert.assertTrue;

public class LoginActions {
    String url;
    private WebDriver driver;
    LoginPage loginPage = new LoginPage();

    public LoginActions(WebDriver driver) {
        this.driver = driver;

    }


    public void launchurl() {
        url = TestUtils.getProperty("URL");
        TestUtils.launchUrl(driver, url);
        String path = TestUtils.takeScreenshot(driver);
        try {
            ExtentManager.getExtentTest().info(
                    "Website is launched",
                    MediaEntityBuilder.createScreenCaptureFromPath(path).build()
            );
        } catch (Exception e) {
            ExtentManager.getExtentTest().fail("Website not launched");
        }
        Log.info("Launch the url");

    }

    public void validateTitle() {
        String headerTitle= driver.getTitle();
        Log.info("Header " + headerTitle );
        try{
            TestUtils.assertEquals(headerTitle,TestUtils.getProperty("header"),"Header validation");
            ExtentManager.getExtentTest().pass("Header matches as expected "+ headerTitle);
        } catch (Exception e) {
            ExtentManager.getExtentTest().fail("Header is not matching as expected "+ headerTitle);
        }

    }

    public void loginCTAValidation() {
        try{
            assertTrue(driver.findElement(loginPage.loginCTAPopup).isDisplayed());
            Log.info("Login CTA popup is present");
            ExtentManager.getExtentTest().pass("Login CTA popup is present" , MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        } catch (Exception e) {
            Log.error("Login CTA popup is not present");
            ExtentManager.getExtentTest().fail("Login CTA popup is not present" , MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());

        }

    }

    public void loginBtnValidation() {
        try
        {
            assertTrue(driver.findElement(loginPage.loginbtn).isDisplayed());
            Log.info("Login button is displayed");
            ExtentManager.getExtentTest().pass("Login button is present");
        } catch (Exception e) {
            Log.error("Login button is not displayed");
            ExtentManager.getExtentTest().fail("Login button is not present");
        }
    }

    public void clickOnLoginBtn() {
        WaitUtils.waitExplicitlyForElemTobeInvisible(driver,loginPage.loginCTAPopup);
        driver.findElement(loginPage.loginbtn).click();
        Log.info("Clicked on login button");
        ExtentManager.getExtentTest().info("Clicked on the login button");
    }

    public void valPresenceOfLoginTxtBox() {
        WaitUtils.waitExplicitlyForWebElementVisible(driver,loginPage.mobilenumberTxtBox);
        try
        {
            assertTrue(driver.findElement(loginPage.mobilenumberTxtBox).isDisplayed());
            Log.info("Login textbox is displayed");
            ExtentManager.getExtentTest().pass("Login textbox is displayed",MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        } catch (Exception e) {
            Log.error("Login textbox is not displayed");
            ExtentManager.getExtentTest().fail("Login textbox is not displayed",MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }
    }

    public void valLoginBoxTxt() {
        WaitUtils.waitExplicitlyForWebElementVisible(driver,loginPage.mobilenumberTxt);
        String loginTxt= TestUtils.getTextFromElement(driver,loginPage.mobilenumberTxt);
        try
        {
            assertTrue(driver.findElement(loginPage.mobilenumberTxt).isDisplayed());
            Log.info("Inline text of the login textbox "+ loginTxt);
            ExtentManager.getExtentTest().pass("Inline text of the login textbox is as expected "+ loginTxt);
        } catch (Exception e) {
            Log.error("Login textbox is not displayed");
            ExtentManager.getExtentTest().fail("Inline text of the login textbox is not as expected "+ loginTxt);
        }
    }

    public void clickOnCreateAnAccountLink() {
        driver.findElement(loginPage.registerLink).click();
        Log.info("Clicked on the Create an account link");
        ExtentManager.getExtentTest().info("Clicked on the Create an account link");
    }

    public void valSignUpText() {
        String signupText=driver.findElement(loginPage.signupText).getText();
        try
        {
            assertTrue(driver.findElement(loginPage.signupText).isDisplayed());
            Log.info("Sign Up page text is present and is "+ signupText);
            ExtentManager.getExtentTest().pass("Sign Up page text is present and is "+ signupText,MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        } catch (Exception e) {
            Log.error("Sign Up page text is not displayed");
            ExtentManager.getExtentTest().fail("Sign Up page text is not present "+ signupText,MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }
    }

    public void enterPhoneNumber() {
        String phoneNumber=TestUtils.getProperty("RegisteredPhoneNumber");
        driver.findElement(loginPage.mobilenumberTxtBox).sendKeys(phoneNumber);
        Log.info("Entered the phone number in the phone number text box " + phoneNumber);
        ExtentManager.getExtentTest().info("Successfully entered the phone number in the phone number text box " + phoneNumber,MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
    }

    public void enterPhoneNumber(String phoneNo) {
        driver.findElement(loginPage.mobilenumberTxtBox).sendKeys(phoneNo);
        Log.info("Entered the phone number in the phone number text box " + phoneNo);
        ExtentManager.getExtentTest().info("Trying to enter invalid phone numbers " + phoneNo,MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
    }



    public void clickOnTheContinueBtn() {
        WaitUtils.waitExplicitlyForElemTobeClickable(driver,loginPage.ContinueBtn);
        driver.findElement(loginPage.ContinueBtn).click();
        Log.info("Clicked on the Continue button");
        ExtentManager.getExtentTest().pass("Clicked on the Continue button");
    }

    public void valLoginHeading() {
        WaitUtils.waitExplicitlyForWebElementVisible(driver,loginPage.loginHeading);
        String loginHeading=driver.findElement(loginPage.loginHeading).getText();
        try{
            assertTrue(driver.findElement(loginPage.loginHeading).isDisplayed());
            Log.info("Login heading is displayed "+ loginHeading);
            ExtentManager.getExtentTest().pass("Login heading is displayed "+ loginHeading,MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        } catch (Exception e) {
            Log.error("Login heading is not displayed "+ loginHeading);
            ExtentManager.getExtentTest().fail("Login heading is not displayed "+ loginHeading,MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }

    }

    public void valLoginTexts() {
        WaitUtils.waitExplicitlyForWebElementVisible(driver,loginPage.loginTexts);
        String loginTexts=driver.findElement(loginPage.loginTexts).getText();
        try{
            assertTrue(driver.findElement(loginPage.loginTexts).isDisplayed());
            Log.info("Login texts are displayed "+ loginTexts);
            ExtentManager.getExtentTest().pass("Login texts are displayed "+ loginTexts);
        } catch (Exception e) {
            Log.error("Login texts are not displayed "+ loginTexts);
            ExtentManager.getExtentTest().fail("Login texts are not displayed "+ loginTexts);
        }
    }

    public void valErrorMsg() {
        WaitUtils.waitExplicitlyForWebElementVisible(driver,loginPage.invalidNumErrorMsg);
        String errorMsg=driver.findElement(loginPage.invalidNumErrorMsg).getText();
        try{
            assertTrue(driver.findElement(loginPage.invalidNumErrorMsg).isDisplayed());
            Log.info("Error message is displayed "+ errorMsg);
            ExtentManager.getExtentTest().pass("Error message is displayed "+ errorMsg,MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        } catch (Exception e) {
            Log.error("Error message is not displayed "+ errorMsg);
            ExtentManager.getExtentTest().fail("Error message is not displayed "+ errorMsg,MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }

    }
}
