package api.runners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import listeners.ExtentTestListener;
import org.testng.annotations.Listeners;

@CucumberOptions(
        features = {"src/test/java/resources/features/api"},
        glue = {"api/stepdefinitions","hooks"},
        tags = "@api",
        plugin = {"pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}

)
@Listeners(ExtentTestListener.class)
public class APITestRunner extends AbstractTestNGCucumberTests{

}
