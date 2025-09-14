package ui.Actions;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ui.Pages.DynamicSelectionPage;
import ui.Utils.ExtentManager;
import ui.Utils.Log;
import ui.Utils.TestUtils;
import ui.Utils.WaitUtils;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static org.testng.Assert.assertTrue;


public class DynamicSelectionAction {

    private WebDriver driver;
    DynamicSelectionPage dynamicSelectionPage =new DynamicSelectionPage();
    String brandname;
    String itemname;
    List<String> itemsList=new ArrayList<>();
    List<WebElement> searchedItems;
    String firstProductname;
    int totaldiscount;
    String product;

    public DynamicSelectionAction(WebDriver driver)
    {
        this.driver = driver;
    }

    public void enterBrandNameInSearchBox(String brandname) {
        this.brandname=brandname;
        driver.findElement(dynamicSelectionPage.searchBox).sendKeys(brandname);
    }

    public void fetchTheListOfItems() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> suggestions = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(dynamicSelectionPage.suggList));


        for (WebElement item : suggestions) {
            List<WebElement> appleSpans = item.findElements(By.xpath(".//span[contains(translate(text(),'APPLE','apple'),'apple')]"));
            for (WebElement appleSpan : appleSpans) {
                String productName = appleSpan.getText();
                System.out.println(productName);
                 Log.info(productName);
                 ExtentManager.getExtentTest().info(productName);
            }
        }
    }
    public void selectTheItemFromTheList(String itemName) {
        this.itemname=itemName;
        for(WebElement item:searchedItems)
        {
            if(item.getText().trim().equalsIgnoreCase(itemName))
            {
                item.click();
                break;
            }
        }
    }
    public void validateHeaderOfSearchedItems() {
        try{
            assertTrue(driver.findElement(dynamicSelectionPage.searchedItemsHeader(itemname)).isDisplayed());
            String showingText=driver.findElement(dynamicSelectionPage.searchedItemsHeader(itemname)).getText();
            String itemnameText=driver.findElement(dynamicSelectionPage.searchedItemShowingHeader()).getText();
            Log.info("Searched item and brand is present in the header "+ showingText + " "+itemnameText);
            ExtentManager.getExtentTest().pass("Searched item and brand is present in the header "+showingText + " "+itemnameText, MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        } catch (Exception e) {
            Log.error("Searched item and brand is present in the header");
            ExtentManager.getExtentTest().fail("Searched item and brand is not present in the header ", MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }
    }

    public void clickOnAProduct(String colour, String storage) {
     List<WebElement> products= driver.findElements(dynamicSelectionPage.requiredProduct(colour,storage));
        for(WebElement product:products)
        {
            firstProductname=product.getText();
            product.click();
            Log.info("Clicked on the first product " + firstProductname);
            ExtentManager.getExtentTest().info("Clicked on the first product " + firstProductname);
            break;
        }

    }

    public void valProductOpensInANewTab() throws InterruptedException {
        Thread.sleep(1000);
        Set<String> windowIds=driver.getWindowHandles();
        for(String windowId:windowIds)
        {
            if(!windowId.equals(TestUtils.getWindoWId(driver)))
            {
                driver.switchTo().window(windowId);
            }
        }
        WaitUtils.waitImplicitly(driver, 10);
        try{
            assertTrue(Objects.requireNonNull(driver.getTitle()).toLowerCase().contains(product.toLowerCase()),"Validate the product page is opened in a new tab");
            Log.info("Product page is opened in new tab");
            ExtentManager.getExtentTest().pass("Product page is opened in new tab "+ driver.getTitle(), MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        } catch (Exception e) {
            Log.error("Product page is not opened in a new tab");
            ExtentManager.getExtentTest().fail("Product page is not opened in new tab "+ driver.getTitle(), MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }


        
    }

    public void clickOnAddToCartBtn() {
        WaitUtils.waitExplicitlyForElemTobeClickable(driver, dynamicSelectionPage.addToCartBtn);
        driver.findElement(dynamicSelectionPage.addToCartBtn).click();
        Log.info("Clicked on the Add to cart button");
        ExtentManager.getExtentTest().info("Clicked on the Add to cart button",MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
    }

    public void valNavToShoppingCartPage() {
        try{

            WaitUtils.waitImplicitly(driver,10);
            WaitUtils.waitExplicitlyForWebElementVisible(driver, dynamicSelectionPage.priceDetailsHeader);
            TestUtils.assertEquals(driver.getTitle(), TestUtils.getProperty("ShoppingCartPageTitle"),"Navigated to shopping cart page");
            Log.info("Navigated to shopping cart page");
            ExtentManager.getExtentTest().pass("Navigated to shopping cart page "+ driver.getTitle(), MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        } catch (Exception e) {
            Log.error("Not navigated to shopping cart page");
            ExtentManager.getExtentTest().fail("Not navigated to shopping cart page "+ driver.getTitle(), MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }
    }

    public void valCorrectPrdctAdded() {

            String cartProductName = driver.findElement(dynamicSelectionPage.productTitle(product)).getText().trim();
            if (cartProductName.startsWith(firstProductname) || firstProductname.contains(cartProductName)) {
                Log.info("Correct product is added to the cart " + driver.findElement(dynamicSelectionPage.productTitle(product)).getText());
                ExtentManager.getExtentTest().pass("Correct product is added to the cart " + driver.findElement(dynamicSelectionPage.productTitle(product)).getText(), MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
            }
            else{
            Log.error("Correct product is not added to the cart " + driver.findElement(dynamicSelectionPage.productTitle(product)).getText());
            ExtentManager.getExtentTest().fail("Correct product is not added to the cart " + driver.findElement(dynamicSelectionPage.productTitle(product)).getText(), MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }
    }

    public void calTotalDiscount() {

        int priceOfItem=Integer.parseInt(driver.findElement(dynamicSelectionPage.priceOfItem).getText().replace("₹","").replace(",","").trim());
        Log.info("Original MRP of the product "+ priceOfItem);
        ExtentManager.getExtentTest().info("Original MRP of the product "+ priceOfItem);

        int discount=Integer.parseInt(driver.findElement(dynamicSelectionPage.discount).getText().replace("₹","").replace(",","").replaceAll("[−-]", "").trim());
        Log.info("Discount on the product "+ discount);
        ExtentManager.getExtentTest().info("Discount on the product "+ discount);

        int protectPromiseFee=Integer.parseInt(driver.findElement(dynamicSelectionPage.protectPromiseFee).getText().replace("₹","").replace(",","").trim());
        Log.info("Protect promise fee of the product "+ protectPromiseFee);
        ExtentManager.getExtentTest().info("Protect promise fee of the product "+ protectPromiseFee);

        int expectedtotalamount=priceOfItem-discount+protectPromiseFee;
        Log.info("Calculated total amount is "+ expectedtotalamount);
        ExtentManager.getExtentTest().info("Calculated total amount is "+ expectedtotalamount);

        int totalamount=Integer.parseInt(driver.findElement(dynamicSelectionPage.totalamount).getText().replace("₹","").replace(",","").trim());
        totaldiscount=discount-protectPromiseFee;
        try
        {
            Assert.assertEquals(totalamount,expectedtotalamount,"Total amount validation");
            Log.info("The amount shown in the cart is as expected "+ totalamount);
            ExtentManager.getExtentTest().pass("The amount shown in the cart is as expected "+ totalamount, MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());

        } catch (Exception e) {
            Log.info("The amount shown in the cart is not matching as expected "+ totalamount);
            ExtentManager.getExtentTest().fail("The amount shown in the cart is not matching as expected "+ totalamount, MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }

    }

    public void valDiscount() {

        String totalsavingstatment=driver.findElement(dynamicSelectionPage.totalSavingsStatement).getText();
        int savings=Integer.parseInt(totalsavingstatment.split("₹")[1].split(" ")[0].replace(",","").trim());
        try{
            Assert.assertEquals(savings,totaldiscount,"Total discount validation");
            Log.info("The total discount shown is as expected "+ savings);
            ExtentManager.getExtentTest().pass("The total discount shown is as expected "+ savings, MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        } catch (Exception e) {
            Log.error("The total discount shown is not as expected "+ savings);
            ExtentManager.getExtentTest().pass("The total discount shown is not as expected "+ savings, MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }

    }

    public void enterProductNameInSearchBox(String product) {
        this.product=product;
        driver.findElement(dynamicSelectionPage.searchBox).clear();
        driver.findElement(dynamicSelectionPage.searchBox).sendKeys(product);
        TestUtils.pressEnterKey(driver, dynamicSelectionPage.searchBox);
        Log.info("Entered the product name in the search box "+ product);
        ExtentManager.getExtentTest().info("Entered the product name in the search box "+ product,MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());

    }

    public void getTheListOfAvlPrdcts() {
        List<WebElement> products=driver.findElements(dynamicSelectionPage.productInCart(product));
        for(WebElement product:products)
        {
            Log.info("Available products are "+ product.getText());
            ExtentManager.getExtentTest().info("Available products are "+ product.getText());
        }
    }

    public void clickOnTheFirstProduct() {
        List<WebElement> products=driver.findElements(dynamicSelectionPage.productInCart(product));
        try{
            for(WebElement product:products)
            {
                firstProductname=product.getText();
                product.click();

                Log.info("Clicked on the first product" + firstProductname);
                ExtentManager.getExtentTest().info("Clicked on the first product " + firstProductname,MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
                break;
            }
        } catch (Exception e) {
            Log.error("Not clicked on the first product" + firstProductname);
            ExtentManager.getExtentTest().fail("Not clicked on the first product " + firstProductname,MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }


    }

    public void valStorageOfTheProduct(String storage) throws InterruptedException {
        TestUtils.scrollUsingJS(driver, dynamicSelectionPage.pinIcon);
        WaitUtils.waitExplicitlyForWebElementVisible(driver, dynamicSelectionPage.avalStorageOptions);
        List<WebElement> storageOptions=driver.findElements(dynamicSelectionPage.avalStorageOptions);
        Log.info("Available storage options are: ");
        ExtentManager.getExtentTest().info("Available storage options are: ");
        try{
            for(WebElement option:storageOptions) {
                Log.info(option.getText());
                ExtentManager.getExtentTest().info(option.getText());

                if (option.getText().trim().contains(storage)) {
                    String selectedOption=option.getText();
                    option.click();
                    Log.info("Storage option available and selected " + selectedOption);
                    ExtentManager.getExtentTest().pass("Storage option available and selected " + selectedOption, MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
                    break;
                }
            }
        } catch (Exception e) {
            Log.error("Storage option not available and selected ");
            ExtentManager.getExtentTest().fail("Storage option not available and selected ", MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }

    }

    public void valColourOfTheProduct(String colour) {
        try{
                Log.info("Checking availability of colour: "+colour);
                ExtentManager.getExtentTest().info("Checking availability of colour: "+colour);


                TestUtils.scrollUsingJS(driver, dynamicSelectionPage.pinIcon);
                WaitUtils.waitExplicitlyForWebElementVisible(driver, dynamicSelectionPage.colourOption(colour));

                if(driver.findElement(dynamicSelectionPage.colourOption(colour)).isDisplayed())
                {
                    driver.findElement(dynamicSelectionPage.colourOption(colour)).click();
                    Log.info(colour + " colour is available and displayed");
                    ExtentManager.getExtentTest().info(colour + " colour is available and displayed ");

                    Log.info("Colour option available and selected "+ colour);
                    ExtentManager.getExtentTest().pass("Colour option available and selected "+ colour, MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
                }


        } catch (Exception e) {
            Log.error(("Colour option not available "+ colour));
            ExtentManager.getExtentTest().fail("Colour option not available "+ colour, MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }

    }

    public void clickOnThePlaceOrderBtn() {
        driver.findElement(dynamicSelectionPage.placeOrderBtn).click();
        Log.info("Clicked on the Place order button");
        ExtentManager.getExtentTest().info("Clicked on the Place order button",MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
    }

    public void valNavToPaymentPage() {
        try{
            assertTrue(driver.getTitle().contains("Secure Payment"),"Navigated to payment page");
            Log.info("Navigated to payment page");
            ExtentManager.getExtentTest().pass("Navigated to payment page ", MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        } catch (Exception e) {
            Log.error("Not navigated to payment page");
            ExtentManager.getExtentTest().fail("Not navigated to payment page ", MediaEntityBuilder.createScreenCaptureFromPath(TestUtils.takeScreenshot(driver)).build());
        }
    }
}
