package ui.runners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import listeners.ExtentTestListener;
import org.testng.annotations.Listeners;

@CucumberOptions(
        features = {"src/test/java/resources/features/ui/LaunchUrl.feature"},
        glue = {"ui/stepdefinitions","hooks"},
        tags = "@LaunchUrl",
        plugin = {"pretty", "html:target/cucumber-report.html"}
)
@Listeners(ExtentTestListener.class)
public class UITestRunner extends AbstractTestNGCucumberTests{

}
