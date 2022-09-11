package com.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BookingCompletedPage extends BasePage{

    @FindBy(xpath = "//*[@id=\"top_position_container\"]/div[2]/h1")
    public WebElement successMessage;

    public String getSuccessMessage() {
        return successMessage.getText();
    }
}
