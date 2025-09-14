package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import ui.Base.DriverManager;
import ui.Utils.ExtentManager;

public class Hooks {

    @Before
    public void setUp(Scenario scenario) {

        ExtentManager.createTest(scenario.getName());
        DriverManager.getDriver();
        ExtentManager.getExtentTest().info("Driver initialized for scenario: " + scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {

        DriverManager.quitDriver();
        ExtentManager.getInstance().flush();
    }

}
