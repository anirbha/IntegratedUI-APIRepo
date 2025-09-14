package ui.Actions;

import com.aventstack.extentreports.MediaEntityBuilder;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ui.Pages.FilterPage;
import ui.Utils.ExtentManager;
import ui.Utils.Log;
import ui.Utils.TestUtils;
import ui.Utils.WaitUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FilterAction {

    private WebDriver driver;
    List<WebElement> filteredProducts;
    FilterPage filterPage = new FilterPage();
    String filterName;
    String filterType;
    int min;
    int max;
    List<Map<String, String>> rows;
    String removeFlt;

    public FilterAction(WebDriver driver) {
        this.driver = driver;
    }

    public void expandTheFilter(String filterType) {
        this.filterType=filterType;
        TestUtils.scrollUsingJS(driver,filterPage.customerRatings);
        WaitUtils.waitExplicitlyForWebElementVisible(driver, filterPage.filterSection(filterType));
        driver.findElement(filterPage.filterSection(filterType)).click();

        Log.info("Clicked on the " + filterType + " to expand");
        ExtentManager.getExtentTest().info("Clicked on the " + filterType + " to expand", MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
    }

    public void checkTheFilterCheckbox(String filterOption) {
        filterName = filterOption;
        TestUtils.scrollUsingJS(driver, filterPage.questionMarkIcon);
        WaitUtils.waitExplicitlyForWebElementVisible(driver, filterPage.filterCheckbox(filterOption));
        driver.findElement(filterPage.filterCheckbox(filterOption)).click();
        Log.info("Checked on the " + filterOption + "filter checkbox");
        ExtentManager.getExtentTest().info("Checked on the " + filterOption + " filter checkbox", MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
    }

    public void validateProductsFiltered(String filterType) throws InterruptedException {

        if (!filterType.equals("Brand")) {
            WaitUtils.waitExplicitlyForWebElementVisible(driver, filterPage.productShowingHeader);
            WaitUtils.waitExplicitlyForWebElementVisible(driver, filterPage.productClass);

            Log.info("Filtered Products based on the applied filter: " + filterName);
            ExtentManager.getExtentTest().info("Filtered Products based on the applied filter: " + filterName + filterType, MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
            filteredProducts = driver.findElements(filterPage.filteredProducts(filterName));
            for (int i = 0; i < 10; i++) {
                Log.info(filteredProducts.get(i).getText());
                ExtentManager.getExtentTest().pass(filteredProducts.get(i).getText());
            }
        } else {
            WaitUtils.waitExplicitlyForWebElementVisible(driver, filterPage.productShowingHeader);
            WaitUtils.waitExplicitlyForWebElementVisible(driver, filterPage.productClass);
            Log.info("Filtered Products based on the applied brand: " + filterName);
            ExtentManager.getExtentTest().info("Filtered Products based on the applied brand: " + filterName, MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
            filteredProducts = driver.findElements(filterPage.brandFilteredProducts(filterName));
            for (int i = 0; i < 10; i++) {
                Log.info(filteredProducts.get(i).getText());
                ExtentManager.getExtentTest().pass(filteredProducts.get(i).getText());
            }
        }
    }

    public void applyMinRangeOfPriceFilter(String minimum) {
        min = Integer.parseInt(minimum);

        List<WebElement> minRange = driver.findElements(filterPage.priceMinInput);


        for (WebElement element : minRange) {
            Log.info("Available minimum range values: " + element.getText());
            ExtentManager.getExtentTest().info("Available minimum range values: " + element.getText(), MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }

        for (WebElement element : minRange) {
            if (element.getText().replace("₹","").equals(minimum)) {
                element.click();
                Log.info("Applied the minimum range of the price filter: " + minimum);
                ExtentManager.getExtentTest().info("Applied the minimum range of the price filter: " + minimum, MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
                break;
            }
        }

    }

    public void applyMaxRangeOfPriceFilter(String maximum) {
        max = Integer.parseInt(maximum);

        List<WebElement> maxRange = driver.findElements(filterPage.priceMaxInput);

        for (WebElement element : maxRange) {
            Log.info("Available maximum range values: " + element.getText());
            ExtentManager.getExtentTest().info("Available maximum range values: " + element.getText(), MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }

        List<WebElement> maxmRange = driver.findElements(filterPage.priceMaxInput);
        for (WebElement element : maxmRange) {
            if (element.getText().replace("₹","").equals(maximum)) {
                element.click();
                Log.info("Applied the maximum range of the price filter: " + maximum);
                ExtentManager.getExtentTest().info("Applied the maximum range of the price filter: " + maximum, MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
                break;
            }
        }
    }

    public void validateProductsFilteredOnPrice() {
        WaitUtils.waitExplicitlyForWebElementVisible(driver, filterPage.productShowingHeader);

        WaitUtils.waitExplicitlyForWebElementVisible(driver, filterPage.productClass);


        Log.info("Filtered Products based on the applied price filter: ");
        ExtentManager.getExtentTest().info("Filtered Products based on the applied price filter: ", MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        List<WebElement> products = driver.findElements(filterPage.priceFilteredProducts);
        for (int i = 0; i < 10; i++) {
            String price = products.get(i).getText();
            Log.info("Product Price: " + price);
            ExtentManager.getExtentTest().info("Product Price: " + price);
            String priceValue = price.replace("₹", "").trim();
            if (Integer.parseInt(priceValue) >= min && Integer.parseInt(priceValue) <= max) {
                Log.info("The product price is within the applied range of the price filter");
                ExtentManager.getExtentTest().pass("The product price is within the applied range of the price filter");
            } else {
                Log.error("The product price is not within the applied range of the price filter");
                ExtentManager.getExtentTest().fail("The product price is not within the applied range of the price filter");
            }

        }
    }

    public void multipleFilters(DataTable dataTable) throws InterruptedException {
        rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            String filterType = row.get("filterType");
            String filtername = row.get("filterName");

            expandTheFilter(filterType);
            checkTheFilterCheckbox(filtername);
            Thread.sleep(2000);

        }

    }

    public void validateProductsFilteredOnMultipleFilters() {
        List<String> filterNames = new ArrayList<>();
        for (Map<String, String> row : rows) {
            String filtername = row.get("filterName");
            filterNames.add(filtername);
        }
        List<WebElement> filteredProds = driver.findElements(filterPage.multipleFilteredProducts(filterNames));
        Log.info("Filtered Products based on the applied multiple filters: ");
        ExtentManager.getExtentTest().info("Filtered Products based on the applied multiple filters: ", MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        for (int i = 0; i < 10; i++)
        {
            Log.info(filteredProds.get(i).getText());
            ExtentManager.getExtentTest().pass(filteredProds.get(i).getText());
        }

    }

    public void clickOnFirstProduct() {
        List<String> filterNames = new ArrayList<>();
        for (Map<String, String> row : rows) {
            String filtername = row.get("filterName");
            filterNames.add(filtername);
        }

        List<WebElement> filteredProds = driver.findElements(filterPage.multipleFilteredProducts(filterNames));
        filteredProds.getFirst().click();
        WaitUtils.waitImplicitly(driver,20);
        Log.info("Click on the first product");
        ExtentManager.getExtentTest().info("Click on the first product");
    }

    public void valTheProduct() {
        WaitUtils.waitExplicitlyForWebElementVisible(driver,filterPage.header);
        String productname=driver.findElement(filterPage.header).getText();

        for (Map<String, String> row : rows) {
            String filtername = row.get("filterName");
            Assert.assertTrue(productname.contains(filtername));
            {
                Log.info("Product contains the filtername");
                ExtentManager.getExtentTest().pass("Product contains the filtername");
            }
        }
    }

    public void filterCheckBoxes(List<String> values) {
        for(String str:values)
        {

            driver.findElement(filterPage.filterCheckbox(str)).click();
            WaitUtils.waitExplicitlyForWebElementVisible(driver,filterPage.filterSection(filterType));
            expandTheFilter(filterType);
            Log.info("Checked on the "+str +" checkboxes");
            ExtentManager.getExtentTest().info("Checked on the "+str +" checkboxes",MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }
    }
    public void removeOneFilterVal(String removeFlt) {
            this. removeFlt = removeFlt;
            TestUtils.scrollUsingJS(driver,filterPage.FilterHeading);
            driver.findElement(filterPage.removeFilter(removeFlt));
            Log.info("Clicked on X link on the"+removeFlt+"filter");
            ExtentManager.getExtentTest().info("Clicked on X link on the"+removeFlt+"filter",MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
    }
    public void valFilterValues() {
            List<WebElement> filterValues=driver.findElements(filterPage.filterValues);
            List<String> appliedFilters=new ArrayList<>();
            if(filterValues.isEmpty())
            {
                Log.info("All the filters are cleared");
                ExtentManager.getExtentTest().info("All the filters are cleared",MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
            }
            else {
                for(int i=0; i<filterValues.size(); i++)
                {
                    appliedFilters.add(filterValues.get(i).getText());
                }
            }
            try
            {
                Assert.assertFalse(appliedFilters.contains(removeFlt));
                Log.info(removeFlt+" filter is removed");
                ExtentManager.getExtentTest().pass(removeFlt+" filter is removed",MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());

            } catch (Exception e) {
                Log.error(removeFlt+" filter is not removed");
                ExtentManager.getExtentTest().fail(removeFlt+" filter is not removed",MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
            }
    }

    public void clickOnClearAllFilter() {
        driver.findElement(filterPage.clearAllFilter).click();
        Log.info("Clicked on the Clear all filter");
        ExtentManager.getExtentTest().info("Clicked on the Clear all filter");
    }

    public void valNoProdsFound() {
        WaitUtils.waitExplicitlyForWebElementVisible(driver,filterPage.noProdsFound);
        String noProdstext=driver.findElement(filterPage.noProdsFound).getText();
        try{
            TestUtils.assertEquals(noProdstext,TestUtils.getProperty("NoProductsFoundText"),"No products found validation");
            Log.info("No products found validated successfully");
            ExtentManager.getExtentTest().pass("No products found validated successfully",MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        } catch (Exception e) {
            Log.error("No products found not validated successfully");
            ExtentManager.getExtentTest().fail("No products found not validated successfully",MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }
    }
}


