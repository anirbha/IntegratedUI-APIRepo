package ui.Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentManager {
    private static ExtentReports extentReports;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static ExtentReports getInstance() {
        if (extentReports == null) {
            ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("extent.html");
            extentReports = new ExtentReports();
            extentReports.attachReporter(extentSparkReporter);
        }
        return extentReports;
    }

    // Update this method to return ExtentTest
    public static ExtentTest createTest(String name) {
        ExtentTest extentTest = getInstance().createTest(name);
        ExtentManager.extentTest.set(extentTest);
        return extentTest;
    }

    public static ExtentTest getExtentTest() {
        return extentTest.get();
    }
}
