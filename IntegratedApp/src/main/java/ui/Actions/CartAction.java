package ui.Actions;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ui.Pages.CartPage;
import ui.Utils.ExtentManager;
import ui.Utils.Log;
import ui.Utils.TestUtils;
import ui.Utils.WaitUtils;

import java.util.Objects;

public class CartAction {
    private WebDriver driver;
    CartPage cartpage=new CartPage();

    int number;

    public CartAction(WebDriver driver)
    {
        this.driver=driver;
    }

    public void increaseTheNumber(String number) {
        this.number=Integer.parseInt(number);
        for(int i=1;i<this.number;i++)
        {
            driver.findElement(cartpage.plusIcon).click();
            Log.info("+ btn clicked");
            ExtentManager.getExtentTest().info("+ btn clicked",MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
            ExtentManager.getExtentTest().info(driver.findElement(cartpage.quantityNotification).getText());
            WaitUtils.waitExplicitlyForElemTobeInvisible(driver,cartpage.quantityNotification);
        }
    }

    public void valTheProductNumberUpdated() {

        int qunatity=Integer.parseInt(Objects.requireNonNull(driver.findElement(cartpage.noOfItemsAdded).getAttribute("value")));
        try
        {
            Assert.assertEquals(qunatity,number,"validate the quantity");
            Log.info("Desired number of items added "+ qunatity);
            ExtentManager.getExtentTest().pass("Desired number of items added "+ qunatity, MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        } catch (Exception e) {
            Log.error("Desired number of items not added "+ qunatity);
            ExtentManager.getExtentTest().pass("Desired number of items not added "+ qunatity, MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }
    }

    public void addingQuantity(String number) {
        driver.findElement(cartpage.noOfItemsAdded).clear();
        driver.findElement(cartpage.noOfItemsAdded).sendKeys(number);
        Log.info("Trying to add"+ number+" of products");
        ExtentManager.getExtentTest().info("Trying to add"+ number+" of products");
    }

    public void valUpperLimitAlertMessage() {
        try
        {
            WaitUtils.waitExplicitlyForWebElementVisible(driver,cartpage.upperLimitWarning);
            Assert.assertTrue(driver.findElement(cartpage.upperLimitWarning).isDisplayed());
            String warningmsg=driver.findElement(cartpage.upperLimitWarning).getText();
            Log.info(warningmsg);
            ExtentManager.getExtentTest().pass(warningmsg, MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        } catch (Exception e) {
            Log.error("Warning msg not displayed");
            ExtentManager.getExtentTest().fail("Warning msg not displayed", MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }
    }

    public void clickOnTheRemoveLink() {
        WaitUtils.waitExplicitlyForWebElementVisible(driver,cartpage.removeLink);
        driver.findElement(cartpage.removeLink).click();
        Log.info("Clicked on the Remove link");
        ExtentManager.getExtentTest().info("Clicked on the Remove link");
    }

    public void validateRemovePopUp() {
        WaitUtils.waitExplicitlyForWebElementVisible(driver,cartpage.removeItemPopUpHdng);
        try{
            Assert.assertTrue(driver.findElement(cartpage.removeItemPopUpHdng).isDisplayed());
            Log.info("Remove Pop Up appeared");
            ExtentManager.getExtentTest().pass("Remove Pop Up appeared",MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        } catch (Exception e) {
            Log.error("Remove Pop Up didn't appear");
            ExtentManager.getExtentTest().fail("Remove Pop Up appeared",MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }

    }

    public void clickOnTheButton(String btnname) {
        if(btnname.equals("Remove"))
        {
            driver.findElement(cartpage.removeBtn).click();
            Log.info("Clicked on the Remove button");
            ExtentManager.getExtentTest().info("Clicked on the Remove button");
        }
        else {
            driver.findElement(cartpage.cancelBtn).click();
            Log.info("Clicked on the Cancel button");
            ExtentManager.getExtentTest().info("Clicked on the Cancel button");
        }
    }

    public void valBtnFunc(String btn, String product) {
        if(btn.equals("Remove"))
        {
            try{
                WaitUtils.waitExplicitlyForElemTobeInvisible(driver,cartpage.removeItemPopUpHdng);
                WaitUtils.waitExplicitlyForWebElementVisible(driver, cartpage.removeNotification);
                Assert.assertTrue(driver.findElement(cartpage.removeNotification).isDisplayed());
                Log.info("Product removed successfully "+ driver.findElement(cartpage.removeNotification).getText() );
                ExtentManager.getExtentTest().pass("Product removed successfully "+driver.findElement(cartpage.removeNotification).getText(),MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
            } catch (Exception e) {
                Log.error("Product not removed successfully");
                ExtentManager.getExtentTest().fail("Product not removed successfully",MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
            }

        }
        else {
            try{
                WaitUtils.waitExplicitlyForElemTobeInvisible(driver,cartpage.removeItemPopUpHdng);
                Assert.assertTrue(driver.findElement(cartpage.cartProd(product)).isDisplayed());
                Log.info("Product is present on cart");
                ExtentManager.getExtentTest().pass("Product is present on cart",MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
            } catch (Exception e) {
                Log.error("Product is not present on cart");
                ExtentManager.getExtentTest().fail("Product is not present on cart",MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
            }
        }
    }

    public void clickOnTheLink(String link) {

        if(link.equalsIgnoreCase("Save for later"))
        {
            driver.findElement(cartpage.saveForLaterLink).click();
            Log.info("Clicked on the SaveForLater Link");
            ExtentManager.getExtentTest().info("Clicked on the SaveForLater Link");
        }
        else {
            driver.findElement(cartpage.moveToCartLink).click();
            Log.info("Clicked on the MoveToCart Link");
            ExtentManager.getExtentTest().info("Clicked on the MoveToCart Link");
        }

    }
    public void valAddedSaveFrLater() throws InterruptedException {

        Thread.sleep(1000);
       String successmsg=driver.findElement(cartpage.savedForLaterNotification).getText();
       try{
           Assert.assertTrue(driver.findElement(cartpage.savedForLaterNotification).isDisplayed());
           Log.info("Product saved for later "+ successmsg);
           ExtentManager.getExtentTest().pass("Product saved for later "+ successmsg,MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
       } catch (Exception e) {
           Log.error("Product not saved for later "+ successmsg);
           ExtentManager.getExtentTest().fail("Product not saved for later "+ successmsg,MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
       }

    }

    public void valAddedMovedToCart() {
        WaitUtils.waitExplicitlyForWebElementVisible(driver,cartpage.moveToCartNotification);
        String successmsg=driver.findElement(cartpage.moveToCartNotification).getText();
        try{
            Assert.assertTrue(driver.findElement(cartpage.moveToCartNotification).isDisplayed());
            Log.info("Product moved to cart "+ successmsg);
            ExtentManager.getExtentTest().pass("Product moved to cart "+ successmsg,MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        } catch (Exception e) {
            Log.error("Product moved to cart "+ successmsg);
            ExtentManager.getExtentTest().fail("Product moved to cart "+ successmsg,MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }
    }

    public void valReduceTheQuantity() {
        Assert.assertFalse(driver.findElement(cartpage.minusIcon).isEnabled());
        Log.info("- btn is disabled");
        ExtentManager.getExtentTest().pass("- btn is disabled",MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());


    }

    public void valIconsInSavedForLater() {
        Assert.assertFalse(driver.findElement(cartpage.plusIcon).isEnabled());
        Log.info("+ btn is disabled");
        ExtentManager.getExtentTest().pass("+ btn is disabled",MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());

        Assert.assertFalse(driver.findElement(cartpage.minusIcon).isEnabled());
        Log.info("- btn is disabled");
        ExtentManager.getExtentTest().pass("- btn is disabled",MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
    }

    public void clickOnTheHomeLink() {
        driver.findElement(cartpage.homeLink).click();
        Log.info("Home link is clicked");
        ExtentManager.getExtentTest().info("Home link is clicked");
    }


    public void clickOnCartIcon() {
        if(driver.findElement(cartpage.loginPopUpCloseLink).isDisplayed())
        {
            driver.findElement(cartpage.loginPopUpCloseLink).click();

        }
        WaitUtils.waitExplicitlyForElemTobeInvisible(driver,cartpage.loginCTAPopup);

        WaitUtils.waitExplicitlyForElemTobeClickable(driver,cartpage.cartIcon);
        driver.findElement(cartpage.cartIcon).click();
        Log.info("Cart icon is clicked");
        ExtentManager.getExtentTest().info("Cart icon is clicked");
    }

    public void valOutOfStockItem() {
        try
        {
            WaitUtils.waitExplicitlyForWebElementVisible(driver,cartpage.OutOfStockTxt);
            Assert.assertTrue(driver.findElement(cartpage.OutOfStockTxt).isDisplayed());
            Log.info("The item is Out of stock "+ driver.findElement(cartpage.OutOfStockTxt).getText());
            ExtentManager.getExtentTest().pass("The item is Out of stock" + driver.findElement(cartpage.OutOfStockTxt).getText(), MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        } catch (Exception e) {
            Log.error("The item is not Out of stock "+ driver.findElement(cartpage.OutOfStockTxt).getText());
            ExtentManager.getExtentTest().fail("The item is not Out of stock" + driver.findElement(cartpage.OutOfStockTxt).getText(), MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }
    }

    public void valAddToCartbtnnopresent() {
        try
        {   WaitUtils.waitExplicitlyForWebElementVisible(driver,cartpage.OutOfStockTxt);
            Assert.assertTrue(driver.findElement(cartpage.notifyMeBtn).isDisplayed());
            Log.info("Notify Me button is present instead of Add to Cart");
            ExtentManager.getExtentTest().pass("Notify Me button is present instead of Add to Cart" , MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        } catch (Exception e) {
            Log.error("Add to Cart button is present");
            ExtentManager.getExtentTest().fail("Add to Cart button is present", MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }
    }
}
