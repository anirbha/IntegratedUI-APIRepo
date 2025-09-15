package ui.Pages;

import org.openqa.selenium.By;

public class DynamicSelectionPage {

    String brandname;
    String itemname;


    public By searchBox=By.xpath("//input[contains(@title,'Search')]");
    public By addToCartBtn=By.xpath("//button[contains(text(),'Add to cart')]");
    public By priceOfItem=By.xpath("(//span[contains(text(),'Price details')]/following::div[contains(text(),'item')]/following::div/span[contains(text(),'₹')])[1]");
    public By discount=By.xpath("(//span[contains(text(),'Price details')]/following::div[contains(text(),'Discount')]/following::div//span[contains(text(),'₹')])[1]");
    public By protectPromiseFee=By.xpath("(//span[contains(text(),'Price details')]/following::div[contains(text(),'Protect Promise Fee')]/following::div//span[contains(text(),'₹')])[1]");
    public By totalamount=By.xpath("(//span[contains(text(),'Price details')]/following::div[contains(text(),'Total Amount')]/following::div//span[contains(text(),'₹')])[1]");
    public By totalSavingsStatement=By.xpath("//div[contains(text(),'You will save')]");
    public By suggList=By.xpath("//ul[@class='_1sFryS _2x2Mmc _3ofZy1']/li");
    public By avalStorageOptions=By.xpath("//span[@id='Storage']/following::ul/li/a");
    public By avalColourOptions=By.xpath("//span[@id='Color']/following::ul/li/a");
    public By pinIcon=By.id("pincodeInputId");
    public By priceDetailsHeader=By.xpath("//span[contains(text(),'Price details')]");
    public By placeOrderBtn=By.xpath("//span[contains(text(),'Place Order')]");



    public By searchingBrandLists(String brandname)
    {
        this.brandname=brandname.toLowerCase();
        return By.xpath("//ul/li/following::span[contains(text(),'"+ this.brandname+"')]");
    }

    public By searchedItemsHeader(String itemname)
    {
        this.itemname=itemname;
        return By.xpath("//span[contains(text(),'"+brandname+"+"+itemname+"')]");
    }
    public By searchedItemShowingHeader()
    {
        return By.xpath("//span[contains(text(),'"+brandname+"+"+itemname+"')]" +
                "/ancestor::span[contains(text(),'Showing')]");
    }
    public By requiredProduct(String colour,String storage)
    {

        return By.xpath("//span[contains(text(),'"+brandname+"')][contains(text(),'"+itemname+"')][contains(text(),'"+colour+"')][contains(text(),'"+storage+"')]" );
    }
    public By productTitle(String productname)
    {
        return By.xpath("//a[contains(text(),'"+productname+"')]");
    }

    public By productInCart(String productname)
    {
        return By.xpath("//div[contains(text(),'"+productname+"')]");
    }
    public By colourOption(String colour) {

        return By.xpath("//div[contains(text(),'"+colour+"')]/parent::div/parent::li/a");
    }
}
