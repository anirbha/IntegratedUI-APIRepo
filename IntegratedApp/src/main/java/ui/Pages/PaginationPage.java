package ui.Pages;

import org.openqa.selenium.By;

public class PaginationPage {
    public By lookingForText=By.xpath("//span[contains(text(),'Did you find')]");
    public By pagenoStatment=By.xpath("//span[contains(text(),'Page')]");
    public By showingTxt=By.xpath("//span[contains(text(),'Showing')]");

    public By pageno(String pageno)
    {
        return By.xpath("//a[text()='"+pageno+"']");
    }

    public By button(String button)
    {
        return By.xpath("//span[text()='"+button+"']");
    }
}
