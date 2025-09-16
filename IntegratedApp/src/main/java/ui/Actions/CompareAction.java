package ui.Actions;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ui.Pages.ComparePage;
import ui.Utils.*;

import java.util.ArrayList;
import java.util.List;

import static ui.Utils.TestUtils.allElementsEqual;

public class CompareAction {
    
    private WebDriver driver;
    ComparePage comparePage=new ComparePage();
    String brandname;
    String modelname;
    ExcelUtils excelUtils;
    String product1,product2;
    
    public CompareAction(WebDriver driver)
    {
        this.driver=driver;
    }

    public void clickOnTheCompareChkBoxInProductPg() {
        WaitUtils.waitExplicitlyForWebElementVisible(driver,comparePage.compareChkBoxInProduct);
        driver.findElement(comparePage.compareChkBoxInProduct).click();
        Log.info("Clicked on the compare checkbox");
        ExtentManager.getExtentTest().info("Clicked on the compare checkbox", MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());

    }

    public void clickOnComparePopUp() {
        WaitUtils.waitExplicitlyForElemTobeClickable(driver,comparePage.comparePopUp);
        driver.findElement(comparePage.comparePopUp).click();
        Log.info("Clicked on the compare pop up");
        ExtentManager.getExtentTest().info("licked on the compare pop up");
    }

    public void valComparePgNav() {
        try
        {
            WaitUtils.waitExplicitlyForWebElementVisible(driver,comparePage.comparePgHeader);
            Assert.assertTrue(driver.findElement(comparePage.comparePgHeader).isDisplayed());
            Log.info("Navigated to the compare page");
            ExtentManager.getExtentTest().info("Navigated to the compare page",MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        } catch (Exception e) {
            Log.error("Not Navigated to the compare page");
            ExtentManager.getExtentTest().fail(" Not Navigated to the compare page",MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }
    }

    public void valComparedProds(String product1, String product2) {
        try{
            Assert.assertTrue(driver.findElement(comparePage.comparedProducts(product1)).isDisplayed());
            Log.info(product1 + " is present in the compare page");
            ExtentManager.getExtentTest().info(product1 + " is present in the compare page");
            this.product1=driver.findElement(comparePage.comparedProducts(product1)).getText();
            Assert.assertTrue(driver.findElement(comparePage.comparedProducts(product2)).isDisplayed());
            Log.info(product2 + " is present in the compare page");
            ExtentManager.getExtentTest().info(product2 + " is present in the compare page");
            ExtentManager.getExtentTest().pass("Both the products are present in the compare page",MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
            this.product2=driver.findElement(comparePage.comparedProducts(product1)).getText();
        }catch (Exception e)
        {
            Log.info("Both or any of the products is not present in the compare page");
            ExtentManager.getExtentTest().fail("Both or any of the products is not present in the compare page",MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }
    }

    public void addToCompareCheckLists(int num) {
        List<WebElement> addToCompareLists= driver.findElements(comparePage.addToCompareChkboxList);
        for(int i=0; i<num; i++)
        {
            addToCompareLists.get(i).click();
            Log.info(i+" no add to compare checkboxes checked");
            ExtentManager.getExtentTest().info(i+" no add to compare checkboxes checked");
        }
    }

    public void valSameModelWithPrice() {
        List<WebElement> pricesOfTheProds=driver.findElements(comparePage.pricesOfSameModel);

        List<String> priceValues = pricesOfTheProds.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .toList();
        boolean allSame = allElementsEqual(priceValues);
        try{
            Assert.assertTrue(allSame, "Validation of all prices " + priceValues);
            Log.info("All prices are same for the same model: " + allSame);
            ExtentManager.getExtentTest().pass("All prices are same for the same model: " + allSame);
        } catch (Exception e) {
            Log.error("All prices are not same for the same model: " + allSame);
            ExtentManager.getExtentTest().fail("All prices are not same for the same model: " + allSame);
        }

    }

    public void addNewProductInComparePg(String brandname, String modelname) {
        this.brandname=brandname;
        this.modelname=modelname;

        driver.findElement(comparePage.chooseBrandDrpDwn).click();
        WaitUtils.waitExplicitlyForWebElementVisible(driver,comparePage.chooseBrandList);

        List<WebElement> brands= driver.findElements(comparePage.chooseBrandList);

        for (WebElement brand: brands)
        {
            if(brand.getText().equalsIgnoreCase(brandname))
            {
                brand.click();
                break;
            }
        }
        Log.info("selected the brand " + brandname);
        ExtentManager.getExtentTest().info("selected the brand " + brandname, MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());

        driver.findElement(comparePage.chooseProductDrpDwn).click();
        List<WebElement> models= driver.findElements(comparePage.chooseModelList(modelname));

        for (WebElement model: models)
        {
            if(model.getText().equalsIgnoreCase(brandname+" "+modelname))
            {
                model.click();
                break;
            }
        }

        Log.info("selected the model " + modelname);
        ExtentManager.getExtentTest().info("selected the model " + modelname, MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
    }

    public void productAdded() {
        try
        {
            WaitUtils.waitExplicitlyForWebElementVisible(driver,comparePage.newlyAddedProduct(brandname,modelname));
            Assert.assertTrue(driver.findElement(comparePage.newlyAddedProduct(brandname,modelname)).isDisplayed());
            Log.info("The new product is added" + driver.findElement(comparePage.newlyAddedProduct(brandname,modelname)).getText());
            ExtentManager.getExtentTest().info("The new product is added" + driver.findElement(comparePage.newlyAddedProduct(brandname,modelname)).getText(), MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());

        } catch (Exception e) {
            Log.error("The new product is not added" + driver.findElement(comparePage.newlyAddedProduct(brandname,modelname)).getText());
            ExtentManager.getExtentTest().fail("The new product is not added" + driver.findElement(comparePage.newlyAddedProduct(brandname,modelname)).getText(), MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }
    }

    public void clickOnCrossLink() {
        WaitUtils.waitExplicitlyForWebElementVisible(driver,comparePage.crosslinkOnProduct);
        driver.findElement(comparePage.crosslinkOnProduct).click();
        Log.info("Clicked on the X link on the second product");
        ExtentManager.getExtentTest().info("Clicked on the X link on the second product");
    }

    public void valOnlyOneProduct(String product) {

        List<WebElement> products=driver.findElements(comparePage.comparedProducts(product));
        try{
            Assert.assertEquals(products.getFirst().getText(), products.getLast().getText(), "One product present check");
            Log.info("One product present only");
            ExtentManager.getExtentTest().pass("One product present only "+products.getFirst().getText(),MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        } catch (Exception e) {
            Log.error("More than one product present");
            ExtentManager.getExtentTest().fail("More than one product present "+products.getFirst().getText(),MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }
    }

    public void valOfErrorMsg() {
        WaitUtils.waitExplicitlyForWebElementVisible(driver,comparePage.diffProdCompareErrMsg);
        try{
            Assert.assertTrue(driver.findElement(comparePage.diffProdCompareErrMsg).isDisplayed());
            Log.info("Warning msg displayed "+driver.findElement(comparePage.diffProdCompareErrMsg).getText());
            ExtentManager.getExtentTest().pass("Warning msg displayed "+driver.findElement(comparePage.diffProdCompareErrMsg).getText(),MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        } catch (Exception e) {
            Log.info("Warning msg displayed "+driver.findElement(comparePage.diffProdCompareErrMsg).getText());
            ExtentManager.getExtentTest().pass("Warning msg displayed "+driver.findElement(comparePage.diffProdCompareErrMsg).getText(),MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }
    }

    public void writeProductSpecificationInExcel() {
        //This method will write the comparison properties into the excel sheet.

            excelUtils = new ExcelUtils(TestUtils.getProperty("ExcelSheetName"), System.getProperty("user.dir")+TestUtils.getProperty("ExcelOutputPath"));

            excelUtils.writeHeader(new String[]{TestUtils.getProperty("ExcelSheetHeaderCol1"), product1, product2});

        //for the 1st row to record price

        List<WebElement> priceslist = driver.findElements(comparePage.comparedPriceLists);

        List<String> prices = new ArrayList<>();

        for (WebElement element : priceslist) {
            prices.add(element.getText());
        }

        excelUtils.writeRow(1, new String[] { "Price",prices.getFirst(), prices.getLast()});

        try {
            excelUtils.saveAndClose();
            ExtentManager.getExtentTest().pass("Details successfully captured in the excel");
            Log.info("Details successfully captured in the excel");

        } catch (Exception e) {
            ExtentManager.getExtentTest().fail("Exception occurred while save and closing of the excel" + e);
            Log.error("Exception occurred while save and closing of the excel");
        }
    }
}
