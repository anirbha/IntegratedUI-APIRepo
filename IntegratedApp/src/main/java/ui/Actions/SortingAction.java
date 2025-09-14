package ui.Actions;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ui.Pages.FilterPage;
import ui.Pages.SortingPage;
import ui.Utils.ExtentManager;
import ui.Utils.Log;
import ui.Utils.TestUtils;
import ui.Utils.WaitUtils;

import java.util.ArrayList;
import java.util.List;

public class SortingAction {
    private WebDriver driver;
    SortingPage sortingPage =new SortingPage();
    FilterPage filterPage = new FilterPage();

    public SortingAction(WebDriver driver)
    {
        this.driver=driver;
    }


    public void printALLSortingOptions() {
        List<WebElement> sortingOptions= driver.findElements(sortingPage.sortingOptins);
        Log.info("Available sorting options are: ");
        ExtentManager.getExtentTest().info("Available sorting options are: ");

        for (WebElement sort: sortingOptions)
        {
            Log.info(sort.getText());
            ExtentManager.getExtentTest().pass(sort.getText(), MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }
    }

    public void clickOnTheSortingOption(String sorting) {
        driver.findElement(sortingPage.sortingOption(sorting)).click();
        WaitUtils.waitExplicitlyForWebElementVisible(driver,filterPage.productClass);
        Log.info("Clicked sorting option "+ sorting);
        ExtentManager.getExtentTest().info("Clicked sorting option "+ sorting,MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());

    }

    public void valSortingFunctionality(String sorting) throws InterruptedException {
        WaitUtils.waitExplicitlyForWebElementVisible(driver,filterPage.productClass);
        switch (sorting)
        {
            case "Popularity":
                List<WebElement> bestSellerProds=driver.findElements(sortingPage.bestseller);
                try{
                    Assert.assertFalse(bestSellerProds.isEmpty(),"Validate presence of bestseller items");
                    for(WebElement bestseller: bestSellerProds)
                    {
                        String bestsellerProductName= bestseller.getText();
                        Log.info("Best seller items present "+bestsellerProductName);
                        ExtentManager.getExtentTest().pass("Best seller items present " + bestsellerProductName,MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
                    }
                } catch (Exception e) {
                    Log.error("Best seller items are not present ");
                    ExtentManager.getExtentTest().fail("Best seller items are not present " ,MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
                }
                break;
            case "Price -- Low to High":

                List<Integer> increasingPrice=new ArrayList<>();
                boolean increaseflag=true;
                Log.info("Prices of the items");
                ExtentManager.getExtentTest().info("Prices of the items");
                for(int i=0; i<5 ; i++)
                {
                    List<WebElement> increasingProds=driver.findElements(filterPage.priceFilteredProducts);
                    String price=increasingProds.get(i).getText();
                    Log.info(price);
                    ExtentManager.getExtentTest().info(price);
                    increasingPrice.add(Integer.parseInt(price.replace("₹","").replace(",","").trim()));
                }
                for(int i=0; i<increasingPrice.size()-1;i++)
                {
                    if(increasingPrice.get(i) > increasingPrice.get(i+1))
                    {
                        increaseflag=false;
                    }
                }

                try{
                    Assert.assertTrue(increaseflag,"Validate Price in low to high");
                    Log.info("Items are sorted in low to high Price");
                    ExtentManager.getExtentTest().pass("Items are sorted in low to high Price" ,MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());

                } catch (Exception e) {
                    Log.error("Items are not sorted in low to high Price");
                    ExtentManager.getExtentTest().fail("Items are not sorted in low to high Price" ,MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
                }
                break;
            case "Price -- High to Low":

                List<Integer> decreasingprices=new ArrayList<>();
                boolean decreaseflag=true;
                Log.info("Prices of the items");
                ExtentManager.getExtentTest().info("Prices of the items");
                for(int i=0; i<5 ; i++)
                {
                    List<WebElement> decreasingProds=driver.findElements(filterPage.priceFilteredProducts);
                    String price=decreasingProds.get(i).getText();
                    Log.info(price);
                    ExtentManager.getExtentTest().info(price);
                    decreasingprices.add(Integer.parseInt(price.replace("₹","").replace(",","").trim()));
                }
                for(int i=0; i<decreasingprices.size()-1;i++)
                {
                    if(decreasingprices.get(i) < decreasingprices.get(i+1))
                    {
                        decreaseflag=false;
                    }
                }

                try{
                    Assert.assertTrue(decreaseflag,"Validate Price High to low");
                    Log.info("Items are sorted in high to low ");
                    ExtentManager.getExtentTest().pass("Items are sorted in high to low " ,MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());

                } catch (Exception e) {
                    Log.error("Items are not sorted in high to low ");
                    ExtentManager.getExtentTest().fail("Items are not sorted in high to low " ,MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
                }

                break;
            case "Newest First":
                WaitUtils.waitExplicitlyForWebElementVisible(driver,filterPage.productClass);

               Thread.sleep(1000);
                TestUtils.scrollUsingJS(driver,sortingPage.trendingItem);
                WaitUtils.waitExplicitlyForWebElementVisible(driver,sortingPage.trendingItem);


                Thread.sleep(1000);
                List<WebElement> trendingProd= driver.findElements(sortingPage.trending);
                try{
                    Assert.assertFalse(trendingProd.isEmpty(),"Validate presence of trending items");
                    for(WebElement trending: trendingProd)
                    {
                        String trendingProductName= trending.getText();
                        Log.info("Trending items present "+trendingProductName);
                        ExtentManager.getExtentTest().pass("Trending items present " + trendingProductName,MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
                    }
                } catch (Exception e) {
                    Log.error("Trending items are not present ");
                    ExtentManager.getExtentTest().fail("Trending items are not present " ,MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
                }

                break;
            case "Discount":
                List<WebElement> bigBillionDayDiscountProd= driver.findElements(sortingPage.bigBillionDaysOffer);
                try{
                    Assert.assertFalse(bigBillionDayDiscountProd.isEmpty(),"Validate presence of big billion days discounted items");
                    for(WebElement discountedItems: bigBillionDayDiscountProd)
                    {
                        String discountedProductName= discountedItems.getText();
                        Log.info("Discounted items present "+discountedProductName);
                        ExtentManager.getExtentTest().pass("Discounted items present " + discountedProductName,MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
                    }
                } catch (Exception e) {
                    Log.error("Discounted items are not present ");
                    ExtentManager.getExtentTest().fail("Discounted items are not present " ,MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
                }
                break;
        }
    }

    public void reloadPage() {
        driver.navigate().refresh();
    }
}
