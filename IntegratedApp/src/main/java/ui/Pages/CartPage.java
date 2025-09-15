package ui.Pages;

import org.openqa.selenium.By;

public class CartPage {

    public By plusIcon = By.xpath("//button[text()='+']");
    public By minusIcon = By.xpath("//button[text()='–']");
    public By noOfItemsAdded=By.xpath("//input[@class='p6sArZ']");
    public By removeLink=By.xpath("//div[contains(text(),'Remove')]");
    public By removeItemPopUpHdng=By.xpath("//div[text()='Remove Item']");
    public By removeBtn=By.xpath("//div[text()='Remove Item']/following-sibling::div//div[text()='Remove']");
    public By cancelBtn=By.xpath("//div[text()='Remove Item']/following-sibling::div//div[text()='Cancel']");
    public By quantityNotification =By.xpath("//div[contains(text(),'QUANTITY')]");
    public By upperLimitWarning= By.xpath("//div[contains(text(),'sorry')]");
    public By removeNotification=By.xpath("//div[contains(text(),'Successfully removed')]");
    public By saveForLaterLink=By.xpath("//div[contains(text(),'Save for later')]");
    public By moveToCartLink=By.xpath("//div[contains(text(),'Move to cart')]");
    public By savedForLaterNotification=By.xpath("//div[contains(text(),'Saved For Later.')]");
    public By moveToCartNotification=By.xpath("//div[contains(text(),'moved to your Cart.')]");
    public By homeLink=By.xpath("//div[@class='F9+fd2']");

    public By cartIcon=By.xpath("//*[normalize-space()='Cart' or contains(normalize-space(),'Cart')]/ancestor::a[1]");
    public By loginPopUpCloseLink=By.xpath("//span[text()='✕']");
    public By loginPopUp=By.xpath("//div[@class='JFPqaw']");
    public By loginCTAPopup = By.xpath("//a[contains(text(),'Login')]");
    public By OutOfStockTxt=By.xpath("//div[contains(text(),'out of stock')]");
    public By notifyMeBtn=By.xpath("//button[contains(text(),'NOTIFY ME')]");
    public By buyNowBtn=By.xpath("//button[text()='Buy Now']");


    public By itemCounter(int number)
    {
        return By.xpath("//input[@value='"+number+"']");
    }
    public By cartProd(String prodcut)
    {
        return By.xpath("//a[contains(text(),'"+prodcut+"')]");
    }

}
