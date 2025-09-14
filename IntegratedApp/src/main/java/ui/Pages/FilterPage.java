package ui.Pages;

import org.openqa.selenium.By;

import java.util.List;

public class FilterPage {
    public By productShowingHeader = By.xpath("//span[contains(text(),'Showing')]");
    public By questionMarkIcon=By.className("question");
    public By productClass=By.xpath("//div[@class='_75nlfW']");
    public By priceMinInput=By.xpath("//div[@class='suthUA']/select/option");
    public By priceMaxInput=By.xpath("//div[@class='tKgS7w']/select/option");
    public By priceFilteredProducts=By.xpath("//div[@class='Nx9bqj']");
    public By header=By.tagName("h1");
    public By customerRatings=By.xpath("//div[text()='Customer Ratings']");
    public By FilterHeading=By.xpath("//section//span[text()='Filters']");
    public By filterValues=By.xpath("//div[@class='_6tw8ju']");
    public By clearAllFilter=By.xpath("//span[text()='Clear all']");
    public By noProdsFound=By.xpath("//div[contains(text(),'Sorry')]");



    public By filterSection(String filterType)
    {
        return By.xpath("//div[text()='"+filterType+"']");
    }
    public By filterCheckbox(String filterOption)
    {
        return By.xpath("//label[@class='tJjCVx _3DvUAf']/div[text()='"+filterOption+"']/preceding-sibling::div[@class='XqNaEv']");
    }
    public By filteredProducts(String filterType)
    {
        return By.xpath("//a[contains(@title,'"+filterType+"')]");
    }
    public By brandFilteredProducts(String filtername)
    {
        return By.xpath("//div[contains(text(),'"+filtername+"')]");
    }

    public By multipleFilteredProducts(List<String> filternames)
    {
        String filter1=filternames.get(0);
        String filter2=filternames.get(1);
        String filter3=filternames.get(2);
        return By.xpath("//a[contains(@title,'"+filter1+"')][contains(@title,'"+filter2+"')][contains(@title,'"+filter3+"')]");
    }

    public By removeFilter(String brand)
    {
        return By.xpath("//section//div[text()='"+brand+"']/preceding-sibling::div");
    }

}
