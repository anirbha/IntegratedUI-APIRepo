package ui.Pages;

import org.openqa.selenium.By;

public class SortingPage {

    public By sortingOptins= By.xpath("//span[text()='Sort By']/following-sibling::div");
    public By bestseller=By.xpath("//div[text()='Bestseller']/following::a");
    public By trending=By.xpath("//div[text()='Trending']");
    public By bigBillionDaysOffer=By.xpath("//div[text()='Big Billion Days Price']");
    public By trendingItem=By.xpath("//a[contains(text(),'SanDisk')][1]");


    public By sortingOption(String sorting)
    {
        return By.xpath("//div[text()='"+sorting+"']");
    }
}
