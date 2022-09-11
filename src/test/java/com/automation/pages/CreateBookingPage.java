package com.automation.pages;

import com.automation.utilities.Driver;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.automation.utilities.BrowserUtils.waitFor;

public class CreateBookingPage extends BasePage{

    Faker faker = new Faker();
    WebDriver driver = Driver.get();

    @FindBy(css = "#booking_guest_attributes_e_mail")
    public WebElement inputEmail;

    @FindBy(css = "#booking_agreed")
    public WebElement tickIAgree;

    @FindBy(css = ".col-sm-7")
    public List<WebElement> bookingDetails;




    // generates and returns a WebElement dynamically
    public String selectPaymentMethod(String paymentMethod){
        String xpath = "//input[contains(@title,'" + paymentMethod + "')]";
        WebElement buttonPaymentMethod = driver.findElement(By.xpath(xpath));
        waitFor(1);
        buttonPaymentMethod.click();
        return paymentMethod;
    }

    public void setContactInfo(){
        waitFor(1);
        inputEmail.sendKeys(faker.internet().emailAddress(), Keys.TAB, faker.name().lastName(), Keys.TAB, faker.name().firstName(), Keys.TAB, faker.phoneNumber().cellPhone());
    }



    public String getDate() {
        return bookingDetails.get(0).getText();
    }

    public String getNumberOfNights() {
        return bookingDetails.get(1).getText();
    }

    public String getRoomType() {
        return bookingDetails.get(4).getText();
    }

    public String getRate() {
        String rate = bookingDetails.get(5).getText();
        String[] arrRate = rate.split(" ");
        rate = arrRate[0];
        return rate;
    }

    public int getRoomsPrice(){
        String s = bookingDetails.get(7).getText(); // 1,600.00 EUR
        s = s.substring(0, s.length()-7).replace(",", "");
        return Integer.parseInt(s);
    }

    public int getExtraServices() {
        String s = bookingDetails.get(8).getText();
        s = s.substring(0, s.length()-7);
        return Integer.parseInt(s);
    }

    public int getTotal() {
        String s = bookingDetails.get(10).getText();
        s = s.substring(0, s.length()-7).replace(",", "");
        return Integer.parseInt(s);
    }

}
