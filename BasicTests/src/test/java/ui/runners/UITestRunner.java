package ui.runners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import listeners.ExtentTestListener;
import org.testng.annotations.Listeners;

@CucumberOptions(
        features = {"src/test/java/resources/features/ui/Login.feature"},
        glue = {"ui/stepdefinitions","hooks"},
        tags = "@RegistrationWithInvalidNumbers",
        plugin = {"pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}

)
@Listeners(ExtentTestListener.class)
public class UITestRunner extends AbstractTestNGCucumberTests{

}
