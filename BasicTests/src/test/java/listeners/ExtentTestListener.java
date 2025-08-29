package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ui.Utils.ExtentManager;


public class ExtentTestListener implements ITestListener {

//    @Override
//    public void onTestStart(ITestResult result) {
//
//
//    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentManager.getExtentTest().pass("Test '" + testName + "' passed.");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentManager.getExtentTest().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentManager.getExtentTest().skip("Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.getInstance().flush();
    }
}
