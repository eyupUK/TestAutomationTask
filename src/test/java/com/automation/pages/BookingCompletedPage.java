package com.automation.pages;

import com.automation.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BookingCompletedPage extends BasePage{

    WebDriver driver = Driver.get();

    @FindBy(xpath = "//*[@id=\"top_position_container\"]/div[2]/h1")
    public WebElement successMessage;

    @FindBy(xpath = "//*[@id=\"top_position_container\"]/div[4]")
    public WebElement successMessageComplete;

    public String getSuccessMessage(){
        return successMessage.getText();
    }
}
