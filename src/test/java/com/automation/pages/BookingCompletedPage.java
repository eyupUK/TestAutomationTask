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
        // captures the success message only and saves into the captures folder
        captureSuccessMessage(wholeMessage);

        scrollToElement(successMessage);
        highlight(successMessage);

        // captures the page and saves into test-output/screenshot folder
        takeScreenShot("BookingCompleted");
        return successMessage.getText();
    }


    public void captureSuccessMessage(WebElement targetElement)
    {
        File screenshot = ((TakesScreenshot)Driver.get()).getScreenshotAs(OutputType.FILE);
        BufferedImage fullImg = null;
        try {
            fullImg = ImageIO.read(screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Get the location of element on the page
        Point point = targetElement.getLocation();

        // Get width and height of the element
        int eleWidth = targetElement.getSize().getWidth();
        int eleHeight = targetElement.getSize().getHeight();


        // Crop the entire page screenshot to get only element screenshot
        BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), (int)(point.getY()*3.4), eleWidth, eleHeight);
        try {
            ImageIO.write(eleScreenshot, "png", screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // names screenshot
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        formattedDate = formattedDate.replace("-","_").replace(" ", "_").replace(":","_");

        String tagNameOfTargetElement = targetElement.getTagName();
        // Copy the element screenshot to disk
        try {
            FileUtils.copyFile(screenshot, new File("captures/"+ tagNameOfTargetElement +"_"+ formattedDate + ".jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
