package com.automation.pages;

import com.automation.step_defs.Hooks;
import com.automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



import java.util.List;

import static com.automation.utilities.BrowserUtils.*;


public class DashboardPage extends BasePage{

    WebDriver driver = Hooks.getDriver();


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
        waitForPageToLoad(10);
        waitForVisibility(inputDate, 10);
        inputDate.click();
        int range = validTimeBoxes.size();
        int index = (int) (Math.random() * range);
        date = validTimeBoxes.get(index).getText();
        waitForVisibility(validTimeBoxes.get(index), 10);
        validTimeBoxes.get(index).click();
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
        WebElement button = driver.findElement(By.xpath(dynamicXPath));
        button.click();
    }
}
