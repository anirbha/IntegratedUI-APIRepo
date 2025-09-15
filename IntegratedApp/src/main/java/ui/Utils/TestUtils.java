package ui.Utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TestUtils {

    private static Properties properties = new Properties();

    static {
        try (FileInputStream input = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/resources/TestData/Config.properties")) {
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static JSONObject readJsonData(String jsonpath) {
        try {

            String filePath = System.getProperty("user.dir") + "/"+ jsonpath;

            FileReader reader = new FileReader(filePath);
            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(reader);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String takeScreenshot(WebDriver driver) {
        String Screenshotfilename = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMMYY_HHmmss"));

        File screenshotfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String relativePath = getProperty("ScreenshotRelativePath") + Screenshotfilename + ".png";
        String destination = System.getProperty("user.dir") + "/" + relativePath;

        File folder=new File(destination);
        if(!folder.exists())
        {
            folder.mkdirs();
        }

        try {
            Files.copy(screenshotfile.toPath(), new File(destination).toPath(),StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return relativePath;
    }

    public static void launchUrl(WebDriver driver, String url)
    {
        driver.get(url);
    }

    public static void enterValue(WebDriver driver, By object, String text)
    {
        driver.findElement(object).sendKeys(text);
    }

    public static String getTextFromElement(WebDriver driver, By object)
    {
        return driver.findElement(object).getText();
    }

    public static void clickOnElements(WebDriver driver, By object)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        List<WebElement> elementslist=driver.findElements(object);

        for (WebElement element : elementslist) {

            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        }
    }

    public static List<WebElement> returnList(WebDriver driver, By object)
    {
        List<WebElement> elementslist=driver.findElements(object);
        return elementslist;
    }

    public static List<WebElement> returnList(WebDriver driver, int elementid, String baseLocator, String locator)
    {
        List<WebElement> propVal=driver.findElements(By.xpath(baseLocator+"["+(elementid+2)+"]"+locator));
        return propVal;
    }

    public static void pressEnterKey(WebDriver driver, By object)
    {
        driver.findElement(object).sendKeys(Keys.ENTER);
    }

    public static void scrollUsingJS(WebDriver driver, By object)
    {
        WebElement element = driver.findElement(object);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false);", element);

    }

    public static void scrollToTop(WebDriver driver) {
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,0);");
    }

    public static boolean checkPresenceOfElement(WebDriver driver, By object) {
        boolean flag=driver.findElement(object).isDisplayed();
        return flag;
    }

    public static boolean checkElementIsVisible(WebDriver driver, By object)
    {
        return driver.findElement(object).isDisplayed();
    }

    public static String getWindoWId(WebDriver driver)
    {
        return driver.getWindowHandle();
    }

    public static void assertEquals(String actual, String expected, String message) {
         Assert.assertEquals(actual, expected ,message);

    }
    // Utility method to check if all elements in a list are equal
    public static <T> boolean allElementsEqual(List<T> list) {
        if (list == null || list.isEmpty()) return true;
        T first = list.get(0);
        for (T item : list) {
            if (!first.equals(item)) {
                return false;
            }
        }
        return true;
    }

    public static int CurrentPageNo(String pageTxt)
    {
        Pattern pattern = Pattern.compile("Page\\s+(\\d+)\\s+of");
        Matcher matcher = pattern.matcher(pageTxt);
        int currentPage = 0;
        if (matcher.find()) {
            currentPage = Integer.parseInt(matcher.group(1));
        }
        return currentPage;
    }
}







