package com.automation.pages;

import com.automation.utilities.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;


import java.util.List;

import static com.automation.utilities.BrowserUtils.*;


public class AddOnsPage extends BasePage{

    WebDriver driver = Driver.get();
    JavascriptExecutor js = (JavascriptExecutor) driver;

    public By inputNumberAddOnsLocator = By.xpath("//input[@type='number']");
    public By pricesAddOnsLocator = By.xpath("//input[@type='number']//parent::div/preceding-sibling::div[1]");

    public By namesAndPricesAddOnsLocator = By.cssSelector(".form-control-static");


    @FindBy(css = "body > div:nth-child(1) > form:nth-child(7) > div:nth-child(21) > div:nth-child(1) > span:nth-child(1) > input:nth-child(2)")
    public WebElement buttonAddServicesList;


    public Integer selectRandomAddService(int numberOfAddOns){

        waitForPageToLoad(10);

        List<WebElement> inputNumberAddOnsList = driver.findElements(inputNumberAddOnsLocator);
        List<WebElement> pricesAddOns = driver.findElements(pricesAddOnsLocator);
        List<String> pricesList = getElementsText(pricesAddOns); // 15.00 EUR x

        int range = inputNumberAddOnsList.size();
        int temp = range;

        // selects add-ons
        int totalExtraServices = 0;
        int index;
        for (int i = 0; i < numberOfAddOns; i++) {
            index = generateRandomNumber(temp);
            WebElement input = inputNumberAddOnsList.get(index);
            scrollToElement(input);
            input.sendKeys("1");
            String s = pricesList.get(index);
            if(pricesList.get(index).length() > 12){
                s = s.substring(13,15).trim();
                int price = Integer.parseInt(s);
                int nOn = new DashboardPage().getNoOfNights();
                totalExtraServices += price * nOn;
            }
            else {
                s = s.substring(0,2).replace(".", "").trim();
                int price = Integer.parseInt(s);
                totalExtraServices += price;
            }
            inputNumberAddOnsList.remove(inputNumberAddOnsList.get(index));
            temp--;
        }

        return totalExtraServices;
    }


    public void clickAddServices(){
        waitForClickablility(buttonAddServicesList, 10);
        scrollToElement(buttonAddServicesList);
        buttonAddServicesList.click();
    }


}
