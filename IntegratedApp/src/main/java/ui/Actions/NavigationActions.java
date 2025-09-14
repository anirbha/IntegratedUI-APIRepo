package ui.Actions;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import ui.Pages.NavigationPage;
import ui.Utils.*;

import java.util.List;

public class NavigationActions {

    private WebDriver driver;
    NavigationPage navPage=new NavigationPage();


    List<WebElement> subCatagories;
    String subOption;

    public NavigationActions(WebDriver driver) {
        this.driver = driver;
    }

    public void hoverOnDropdown(String dropdown) {
        MouseHover.mouseHover(driver, navPage.navigationDrpDwn(dropdown));
        Log.info("Mouse hovered on the dropdown: "+dropdown);
        ExtentManager.getExtentTest().info("Mouse hovered on the dropdown: "+dropdown, MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
    }

    public void valNavOptionsInDropdown() {
        List<WebElement> catagories=driver.findElements(navPage.dropdownOptions);
        for(WebElement option:catagories)
        {
            Log.info(option.getText());
            ExtentManager.getExtentTest().info(option.getText());
        }
    }

    public void hoverOnCatagory(String option) {
        List<WebElement> catagories=driver.findElements(navPage.dropdownOptions);
        for(WebElement catagory :catagories)
        {
            if (catagory.getText().equalsIgnoreCase(option))
            {

                Actions actions = new Actions(driver);

                actions.moveToElement(catagory).perform();

                WaitUtils.waitExplicitlyForWebElementVisible(driver,navPage.moreinHeading);

                subCatagories=driver.findElements(navPage.subCatagoryOptions);

                break;
            }
        }

        Log.info("Mouse hovered on the option: "+option);
        ExtentManager.getExtentTest().info("Mouse hovered on the option: "+option, MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
    }

    public void valSubOptionsInCatagory() {

        Log.info("The sub categories are: ");
        ExtentManager.getExtentTest().info("The sub categories are: ");
        for(WebElement option:subCatagories)
        {
            Log.info(option.getText());
            ExtentManager.getExtentTest().info(option.getText());
        }
    }

    public void clickOnDropdownOption(String subOption) {
        this.subOption=subOption;
        WaitUtils.waitExplicitlyForElemTobeClickable(driver,navPage.catagoryOption(subOption));
        driver.findElement(navPage.catagoryOption(subOption)).click();
    }



    public void valPageNavigation() {
        String title=driver.getTitle();
        Log.info("The page title is: "+title);
        ExtentManager.getExtentTest().info("The page title is: "+title);
        try{
            Assert.assertTrue(title.contains(subOption));
            Log.info("The user is navigated to the respective page");
            ExtentManager.getExtentTest().pass("The user is navigated to the respective page", MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        } catch (Exception e) {
            Log.error("The user is not navigated to the respective page");
            ExtentManager.getExtentTest().fail("The user is not navigated to the respective page", MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }
    }

    public void clickOnNavBarOption(String navOption) {
        driver.findElement(navPage.navigationWOutDrpDwn(navOption)).click();
        WaitUtils.waitImplicitly(driver,10);
        Log.info("Clicked on the navigation option: "+navOption);
        ExtentManager.getExtentTest().info("Clicked on the navigation option: "+navOption, MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
    }

    public void valParticularBrandHeaders(String brand) {
        TestUtils.scrollUsingJS(driver,navPage.brandHeader(brand));
        try{
            Assert.assertTrue(driver.findElement(navPage.brandHeader(brand)).isDisplayed());
            Log.info("The products of the brand "+brand+" are displayed and header is "+ driver.findElement(navPage.brandHeader(brand)).getText());
            ExtentManager.getExtentTest().pass("The products of the brand "+brand+" are displayed and header is "+ driver.findElement(navPage.brandHeader(brand)).getText(), MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        } catch (Exception e) {
            Log.error("The products of the brand "+brand+" are not displayed");
            ExtentManager.getExtentTest().fail("The products of the brand "+brand+" are not displayed", MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }
    }

    public void validateNavToRespectivePage(String page) {

        try{
            if(page.equalsIgnoreCase("Mobiles & Tablets"))
            {
                TestUtils.assertEquals(driver.getTitle(),TestUtils.getProperty("Mobile&TabletsPageTitle"),"User navigation to the respective page");
                Log.info("The user is navigated to the respective page");
                ExtentManager.getExtentTest().pass("The user is navigated to the respective page", MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
            }
        } catch (Exception e) {
                Log.error("The user is navigated to the respective page");
                ExtentManager.getExtentTest().fail("The user is not navigated to the respective page", MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }

    }

    public void valParticularBrandProducts(String brand) {
        TestUtils.scrollUsingJS(driver,navPage.brandHeader(brand));
        driver.findElement(navPage.viewAllBtn(brand)).click();

        try{
            WaitUtils.waitImplicitly(driver,10);
            WaitUtils.waitExplicitlyForWebElementVisible(driver,navPage.header);
            List<WebElement> products=driver.findElements(navPage.brandProduct(brand));
            for(int i=2;i<products.size();i++)
            {
                Log.info(products.get(i).getText());
                ExtentManager.getExtentTest().info(products.get(i).getText());
            }

        } catch (Exception e) {
                Log.error("Products not displayed");
                ExtentManager.getExtentTest().info("Products not displayed");
        }
    }

    public void valParticularBrandHeadersNotPresent(String brand) {

        try {
            boolean isDisplayed = driver.findElement(navPage.brandHeader(brand)).isDisplayed();
            if (isDisplayed) {
                Log.error("The brand header is present");
                ExtentManager.getExtentTest().fail("The brand header " + brand + " is present", MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
                Assert.fail("Brand header is present");
            }
        } catch (org.openqa.selenium.NoSuchElementException e) {
            Log.info("The brand header is not present");
            ExtentManager.getExtentTest().pass("The brand header is not present", MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }
    }


}
