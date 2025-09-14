package ui.Pages;

import org.openqa.selenium.By;

public class NavigationPage {


    public By dropdownOptions=By.xpath("//div[@class='_16rZTH']//object//a");
    public By subCatagoryOptions=By.xpath("//div[@class='_3XS_gI _7qr1OC']//a");
    public By moreinHeading=By.xpath("//div[contains(text(),'More in')]");
    public By header=By.tagName("h1");

    public By navigationWOutDrpDwn(String option)
    {
        return By.xpath("//span[contains(text(),'"+option+"')]");
    }

    public By navigationDrpDwn(String dropdown) {
        return By.xpath("//span[contains(text(),'"+dropdown+"')]/following-sibling::span");
    }
    public By catagoryOption(String option)
    {
        return By.xpath("//a[contains(text(),'"+option+"')]");
    }

    public By brandHeader(String brand)
    {
        return By.xpath("//h2[contains(text(),'"+brand+"')]");
    }
    public By viewAllBtn(String brand)
    {
        return By.xpath("//h2[contains(text(),'"+brand+"')]/following::span[text()='VIEW ALL'][1]");
    }
    public By brandFilter(String brand)
    {
        return By.xpath("//div[text()='Brand']/following-sibling::div//label//span[text()='"+brand+"']");
    }
    public By brandProduct(String brand)
    {
        return By.xpath("//div[contains(text(),'"+brand+"')]");
    }
}
