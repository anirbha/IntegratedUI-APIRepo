package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import ui.Base.DriverManager;
import ui.Utils.ExtentManager;

public class Hooks {

    @Before("@ui")
    public void setUp(Scenario scenario) {

        ExtentManager.createTest(scenario.getName());
        DriverManager.getDriver();
        ExtentManager.getExtentTest().info("Driver initialized for scenario: " + scenario.getName());
    }

    @After("@ui")
    public void tearDown(Scenario scenario) {

        DriverManager.quitDriver();
        ExtentManager.getInstance().flush();
    }

    @Before("@api")
    public void setApi(Scenario scenario) {

        ExtentManager.createTest(scenario.getName());

        ExtentManager.getExtentTest().info("Scenario Initialized " + scenario.getName());
    }

    @After("@api")
    public void tearDownAPI(Scenario scenario) {
        ExtentManager.getInstance().flush();
    }

}
