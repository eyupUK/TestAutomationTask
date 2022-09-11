package com.automation.pages;

import com.automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.List;

import static com.automation.utilities.BrowserUtils.waitFor;


public class DashboardPage extends BasePage{

    WebDriver driver = Driver.get();

    @FindBy(xpath = "//td[@class='day']")
    public List<WebElement> validTimeBoxes;

    @FindBy(css = "#date-start")
    public WebElement inputDate;

    @FindBy(css = "#to-place")
    public WebElement inputNightNumber;

    @FindBy(xpath = "//input[@value='Book now !']")
    public WebElement buttonBookNow;

    @FindBy(css = "table[class=' table-condensed'] th[class='datepicker-switch']")
    public WebElement nameMonth;

    public static String date;
    public static int numberOfNights;

    public void clickTimebox() {
        inputDate.click();
        waitFor(1);
        int range = validTimeBoxes.size();
        int index = (int) (Math.random() * range);
        date = validTimeBoxes.get(index).getText();
        validTimeBoxes.get(index).click();
        waitFor(1);
        date += " " + nameMonth.getText();
    }



    public String setNumberOfNights(String numberOfNights){
        this.numberOfNights = Integer.parseInt(numberOfNights);
        inputNightNumber.clear();
        inputNightNumber.sendKeys(numberOfNights);
        return numberOfNights;
    }

    public int getNoOfNights(){
        return numberOfNights;
    }

    public void clickButton(String buttonText){
        String dynamicXPath = "//input[@value='" + buttonText + "']";
        waitFor(1);
        WebElement button = driver.findElement(By.xpath(dynamicXPath));
        button.click();
        System.out.println("button clicked");

    }
}
