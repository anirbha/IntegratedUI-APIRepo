package ui.Pages;

import org.openqa.selenium.By;

public class ComparePage {

    public By compareChkBoxInProduct = By.xpath("//input[@type='checkbox']/following::div");
    public By comparePopUp =By.xpath("//span[text()='COMPARE']");
    public By comparePgHeader=By.xpath("//div[contains(text(),'Compare')][1]");
    public By comparePopUpXsing=By.xpath("//span[text()='✕']");
    public By addToCompareChkboxList=By.xpath("//span[text()='Add to Compare']/parent::label/preceding-sibling::span//div");
    public By pricesOfSameModel=By.xpath("//div[@class='Nx9bqj']");
    public By chooseBrandDrpDwn=By.xpath("//div[text()='Choose Brand']");
    public By chooseBrandList=By.xpath("//div[@class='_8W-V8i']");
    public By chooseProductDrpDwn=By.xpath("(//div[normalize-space(text())='Choose a Product'])[1]");
    public By crosslinkOnProduct=By.xpath("(//div[@title='Remove Product'])[2]");
    public By diffProdCompareErrMsg=By.xpath("//div[contains(text(),'You can only compare similar products')]");

    public By comparedProducts(String product)
    {
        return By.xpath("//a[contains(text(),'"+product+"')]");
    }
    public By newlyAddedProduct(String brandname, String modelname)
    {
        return By.xpath("(//a[contains(text(),'"+brandname+" "+modelname+"')])[1]");
    }
    public By chooseModelList(String modelname)
    {
        return By.xpath("//div[@class='_8W-V8i'][contains(text(),'"+modelname+"')]");
    }
}
