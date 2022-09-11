package com.automation.pages;


import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;


import static com.automation.utilities.BrowserUtils.waitFor;

public class PaymentPage extends BasePage{

    Faker faker = new Faker();

    @FindBy(xpath = "//input[@id='cardNumber']")
    public WebElement cardNumber;

    @FindBy(css = "#credit_card_collect_purchase_brand")
    public WebElement cardBrand;

    @FindBy(css = "#cardExpirationMonth")
    public WebElement expireMonth;

    @FindBy(css = "#cardExpirationYear")
    public WebElement expireYear;

    @FindBy(css = "#credit_card_collect_purchase_address")
    public WebElement billingAddress;

    @FindBy(css = "#credit_card_collect_purchase_country")
    public WebElement country;

    @FindBy(css = "button[type='submit']")
    public WebElement buttonPay;



    //
    public void setCardInfo(){
        cardNumber.sendKeys("4716562078533227");
        waitFor(1);
        Select select = new Select(cardBrand);
        select.getOptions().get(1).click();
        waitFor(1);
        select = new Select(expireMonth);
        select.getOptions().get(2).click();
        waitFor(1);
        select = new Select(expireYear);
        select.getOptions().get(2).click();


    }

    //
    public void setAddressInfo(){
        billingAddress.sendKeys(faker.address().buildingNumber() + " " + faker.address().streetAddress(), Keys.TAB, faker.address().zipCode(), Keys.TAB, faker.address().city(), Keys.TAB, faker.address().state());
        waitFor(1);
        Select select = new Select(country);
        select.selectByVisibleText("United States");
        waitFor(1);
    }

}