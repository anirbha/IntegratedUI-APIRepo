package ui.Actions;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import ui.Pages.LoginPage;
import ui.Utils.ExtentManager;
import ui.Utils.Log;
import ui.Utils.TestUtils;

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
        ExtentManager.getExtentTest().pass("Title "+ headerTitle);
    }
}
