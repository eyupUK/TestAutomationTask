package com.automation.pages;

import com.automation.step_defs.Hooks;
import com.automation.utilities.BrowserUtils;
import com.automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;


import java.util.List;

import static com.automation.utilities.BrowserUtils.getElementsText;
import static com.automation.utilities.BrowserUtils.waitFor;

public class RoomTypesPage extends BasePage{

    WebDriver driver = Hooks.getDriver();


    @FindBy(xpath = "//div[@class='h2 text-center']")
    public WebElement selectedDate;

    @FindBy(xpath = "//h2[contains(text(),'Deluxe Appartment')]")
    public WebElement textDeluxeApartment;

    @FindBy(xpath = "//*[@id='new_form_period']/a[2]")
    public WebElement buttonNext12Days;

    @FindBy(xpath = "//input[@name='commit']")
    public WebElement buttonSearch;

    @CacheLookup
    @FindBy(xpath = "//*[@id=\"bookable_container_15343\"]//td[2]/h4") // //div[@id='bookable_container_15343']//td[@class='text-right hidden-xs']/h4
    public List<WebElement> listPricesOfDeluxeApartment;

    @FindBy(xpath = "//*[@id='bookable_container_15343']//tbody/tr/td[3]/span[1]/a[1]")
    public List<WebElement> listSelectButtons;

    @CacheLookup
    @FindBy(xpath = "//tr/td[1]/h4")
    public List<WebElement> rateTextList;

    //@CacheLookup
    @FindBy(css = ".col-md-12")
    public List<WebElement> roomTypeOptions;

    @FindBy(xpath = "/html/body/div[1]/div[4]/div/div[1]/div/h2")
    public List<WebElement> titleOfRoomTypes;


    public static String date;


    // scrolls down to display a specific element
    // if it could NOT find the element, then finds new valid date and selects it, then goes to the element
    public String find(String elementText){
        waitFor(1);
        String dynamicXPath = "//h2[contains(text(),'"+elementText+"')]";
        By roomTypeTextLocator = By.xpath(dynamicXPath);

        if (driver.findElements(By.tagName("iframe")).size() > 0) {
            driver.switchTo().frame(0);
        }
        List<String> roomTypes = getElementsText(titleOfRoomTypes);
        System.out.println(roomTypes);
        boolean found = false;
        for (String roomType : roomTypes) {
            if (roomType.contains("Deluxe")){found=true;}
        }
        if (!found){
            driver.findElement(By.xpath("//a[@class='text-center']")).click();  //   // //a[contains(text(),'Check availability calendar')]
            waitFor(1);
            findAvailableDate();
        }
        else {
            WebElement textDeluxeApartment = driver.findElement(roomTypeTextLocator);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();", textDeluxeApartment);
        }
        return elementText;
    }

    public void findAvailableDate(){
        List<WebElement> deluxeTimeBoxesList = driver.findElements(By.xpath("//div[2]/div/div/div[@class='list-group']/a[2]/b"));
        List<String> list = getElementsText(deluxeTimeBoxesList);
        boolean clickSearchButton = false;
        for (int i = 0; i < 9; i++){
            int count = 0;
            for (int j = 0; j < 4; j++){
                if(!list.get(i+j).equals("")){
                    count++;
                }
            }

            if (count == 2) {
                i += 1;
            }

            else if (count == 3) {
                i += 2;
            }

            else if (count == 4){
                deluxeTimeBoxesList.get(i).click();
                clickSearchButton = true;
                break;
            }
        }
        if (clickSearchButton) {
            buttonSearch.click();
        }
        else{
            buttonNext12Days.click();
            waitFor(1);
            findAvailableDate();
        }

    }

    public String selectHighestPrice(){
        setDate();
        waitFor(1);
        List<WebElement> listPricesOfDeluxe = listPricesOfDeluxeApartment;
        List<String> listStr = getElementsText(listPricesOfDeluxe);
        int highest = 0;
        int price = 0;
        int index = 0;
        for (String s: listStr) {
            String[] arrStr = s.split(" EUR");
            String str = arrStr[0].trim();
            str = str.replace(",", "").trim();
            str = str.replace(".", "").trim();
            try{
                price = Integer.parseInt(str);
            }
            catch(NumberFormatException ex){
                ex.printStackTrace();
            }
            if (price > highest) {
                highest = price;
                index = listStr.indexOf(s);
            }
        }

        String rate = rateTextList.get(index).getText();
        String[] arrRate = rate.split(" ");
        rate = arrRate[0];
        waitFor(1);
        listSelectButtons.get(index).click();
        return rate;
    }

    public void setDate(){
        String[] arrDate = selectedDate.getText().split("-");
        date = arrDate[0].trim();
    }

    public String getDate() {
        return date;
    }
}