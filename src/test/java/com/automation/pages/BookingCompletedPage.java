package com.automation.pages;

import com.automation.utilities.Driver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.automation.utilities.BrowserUtils.*;
import static com.automation.utilities.Utility.takeScreenShot;

public class BookingCompletedPage extends BasePage{

    WebDriver driver = Driver.get();

    @FindBy(xpath = "//*[@id=\"top_position_container\"]/div[2]/h1")
    public WebElement successMessage;

    @FindBy(xpath = "//*[@id=\"top_position_container\"]/div[2]")
    public WebElement wholeMessage;


    public String getSuccessMessage(){

        scrollToElement(successMessage);
        highlight(successMessage);

        // captures the page and saves into test-output/screenshot folder
        takeScreenShot("BookingCompleted");
        return successMessage.getText();
    }

}
