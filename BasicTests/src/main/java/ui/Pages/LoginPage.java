package ui.Pages;

import org.openqa.selenium.By;

public class LoginPage {

    public By header = By.tagName("title");
    public By loginCTAPopup = By.xpath("//a[contains(text(),'Login')]");
    public By loginbtn = By.xpath("//span[contains(text(),'Login')]");
    public By mobilenumberTxtBox = By.xpath("//form/div/input[@type='text']");
    public By mobilenumberTxt= By.xpath("//form//span[contains(text(),'Email/Mobile')]");
    public By registerLink=By.partialLinkText("Create an account");
    public By signupText=By.xpath("//span[contains(text(),'Sign up')]" );
    public By ContinueBtn=By.xpath("//span[contains(text(),'CONTINUE')]");
    public By loginHeading=By.xpath("//span[contains(text(),'Login')]");
    public By loginTexts=By.xpath("//span[contains(text(),'Login')]/following::span[contains(text(),'Get access')]");
    public By invalidNumErrorMsg=By.xpath("//span[contains(text(),'Please enter a valid Mobile number')]");

}
