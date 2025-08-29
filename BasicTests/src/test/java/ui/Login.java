package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.io.File;


public class Login {

    String url="https://www.flipkart.com";

    WebDriver driver=new ChromeDriver();


    @Test
    public void launchURL() throws InterruptedException {
        // Path to your downloaded ad-block extension (.crx file)
//        String adBlockExtensionPath = "/path/to/adblock_extension.crx";
//
//        ChromeOptions options = new ChromeOptions();
//        options.addExtensions(new File(adBlockExtensionPath));
        driver.manage().window().maximize();
        driver.get(url);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@placeholder='Search for Products, Brands and More']")).sendKeys("Laptop");


        driver.close();

    }



}
