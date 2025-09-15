package ui.Actions;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ui.Pages.PaginationPage;
import ui.Utils.ExtentManager;
import ui.Utils.Log;
import ui.Utils.TestUtils;
import ui.Utils.WaitUtils;


public class PaginationAction {
    private WebDriver driver;
    PaginationPage paginationpage=new PaginationPage();
    String pageno;
    int currentPage;

    public PaginationAction(WebDriver driver)
    {
       this.driver=driver;
    }

    public void printCurrentPageNumber() {

        TestUtils.scrollUsingJS(driver,paginationpage.pagenoStatment);
        WaitUtils.waitExplicitlyForWebElementVisible(driver,paginationpage.pagenoStatment);
        String pageTxt=driver.findElement(paginationpage.pagenoStatment).getText();
        Log.info(pageTxt);
        ExtentManager.getExtentTest().info(pageTxt, MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        currentPage=TestUtils.CurrentPageNo(pageTxt);
        Log.info("Current page no: "+TestUtils.CurrentPageNo(pageTxt));
        ExtentManager.getExtentTest().info("Current page no: "+TestUtils.CurrentPageNo(pageTxt));
    }

    public void clickedOnPageNumber(String pageno) {
        this.pageno=pageno;
        WaitUtils.waitExplicitlyForWebElementVisible(driver,paginationpage.pageno(pageno));
        driver.findElement(paginationpage.pageno(pageno)).click();
        Log.info("Clicked on the " + pageno + " number page");
        ExtentManager.getExtentTest().info("Clicked on the " + pageno + " number page");
    }

    public void valCurrentPageNumber() throws InterruptedException {
        Thread.sleep(1000);

        printCurrentPageNumber();
        try{
            Assert.assertEquals(currentPage,Integer.parseInt(pageno),"Page number validation");
            Log.info("Page number is matching "+currentPage);
            ExtentManager.getExtentTest().pass("Page number is matching "+currentPage,MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        } catch (Exception e) {
            Log.error("Page number is not matching "+currentPage);
            ExtentManager.getExtentTest().fail("Page number is not matching "+currentPage,MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }
    }

    public void navigationBybutton(String button) {
        WaitUtils.waitExplicitlyForWebElementVisible(driver,paginationpage.lookingForText);
        WaitUtils.waitExplicitlyForWebElementVisible(driver,paginationpage.button(button));
        driver.findElement(paginationpage.button(button)).click();
        Log.info(button + " is clicked");
        ExtentManager.getExtentTest().info(button + " is clicked");
    }

    public void valNavBybutton(String button) throws InterruptedException {
        Thread.sleep(1000);
        printCurrentPageNumber();
        try
        {
           if(button.equalsIgnoreCase("Previous"))
           {
               Assert.assertTrue(currentPage<Integer.parseInt(pageno));
               Log.info("Moved to previous page "+currentPage);
               ExtentManager.getExtentTest().pass("Moved to previous page "+currentPage,MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
           }
           else {
               Assert.assertTrue(currentPage>Integer.parseInt(pageno));
               Log.info("Moved to next page "+currentPage);
               ExtentManager.getExtentTest().pass("Moved to next page "+currentPage,MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
           }
        } catch (Exception e) {
            Log.error("Navigation failed");
            ExtentManager.getExtentTest().pass("Navigation failed",MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }
    }

    public void valBtnDisabled(String button) {
        try{
            Assert.assertTrue(driver.findElement(paginationpage.button(button)).isDisplayed());

        } catch (Exception e) {
            Log.info(button + " is not present");
            ExtentManager.getExtentTest().pass(button + " is not present",MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }

    }
}
