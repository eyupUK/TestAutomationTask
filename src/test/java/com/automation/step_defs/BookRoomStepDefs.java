package com.automation.step_defs;

import com.automation.pages.*;
import com.automation.utilities.ConfigurationReader;
import com.automation.utilities.Driver;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import static com.automation.utilities.BrowserUtils.waitFor;
import static org.junit.Assert.assertEquals;

public class BookRoomStepDefs {

    SoftAssert softAssertion= new SoftAssert();
    Faker faker = new Faker();
    DashboardPage dashboardPage = new DashboardPage();
    RoomTypesPage roomTypesPage = new RoomTypesPage();
    AddOnsPage addOnsPage = new AddOnsPage();
    CreateBookingPage createBookingPage = new CreateBookingPage();
    PaymentPage paymentPage = new PaymentPage();
    BookingCompletedPage bookingCompletedPage = new BookingCompletedPage();

    String url = ConfigurationReader.get("url");
    WebDriver driver = Driver.get();

    static String expectedDate;
    static String expectedNumberOfNights;
    static String expectedRoomType;
    static String expectedRate;
    static int expectedExtraServices;
    static int expectedTotal;

    @Given("go to home page")
    public void go_to_home_page() {
        driver.get(url);
        waitFor(1);
    }

    @Given("select any valid date")
    public void select_any_valid_date() {
        dashboardPage.clickTimebox();
        waitFor(1);
    }

    @Given("select {string} nights")
    public void select_nights(String number) {
        expectedNumberOfNights = dashboardPage.setNumberOfNights(number);
        waitFor(1);
    }

    @When("click {string} button")
    public void click_button(String buttonText) {

        try{
            dashboardPage.clickButton(buttonText);
        }
        catch(Exception e){
            addOnsPage.clickAddServices();
        }
    }

    @When("scroll down to the {string}")
    public void scroll_down_to_the(String elementText) {
        expectedRoomType = roomTypesPage.scrollDownToThe(elementText);
        waitFor(1);
    }

    @When("select the most expensive package")
    public void select_the_most_expensive_package() {
        expectedRate = roomTypesPage.selectHighestPrice();
        expectedDate = roomTypesPage.getDate();
        waitFor(1);
    }

    @When("select any {int} add ons")
    public void select_any_add_ons(int numberOfAddOns) {
        expectedExtraServices = addOnsPage.selectRandomAddService(numberOfAddOns);
        waitFor(1);
    }

    @Then("verify all details")
    public void verify_all_details() {

        String actualDate = createBookingPage.getDate();
        assertEquals("Verify that the date was displayed correctly! ", expectedDate, actualDate);

        String actualNoOfNights = createBookingPage.getNumberOfNights();
        assertEquals("Verify that the number of nights is displayed correctly! ", expectedNumberOfNights, actualNoOfNights);

        String actualRoomType = createBookingPage.getRoomType();
        assertEquals("Verify that the room type is displayed correctly! ", expectedRoomType, actualRoomType);

        String actualRate = createBookingPage.getRate();
        assertEquals("Verify that the rate is displayed correctly! ", expectedRate, actualRate);

        int actualExtraServices = createBookingPage.getExtraServices();
        assertEquals("Verify that the total amount of extra services is displayed correctly", expectedExtraServices, actualExtraServices);

        int actualRoomsPrice = createBookingPage.getRoomsPrice();
        expectedTotal = actualRoomsPrice + actualExtraServices;
        int actualTotal = createBookingPage.getTotal();
        assertEquals("Verify that the total amount is displayed correctly! ", expectedTotal, actualTotal);

    }

    @Given("enter contact info")
    public void enter_contact_info() {
        createBookingPage.setContactInfo();
        waitFor(1);
    }

    @Given("select payment method {string}")
    public void select_payment_method(String paymentMethod) {
        createBookingPage.selectPaymentMethod(paymentMethod);
        waitFor(1);
    }

    @When("tick agreement policy")
    public void tick_agreement_policy() {
        createBookingPage.tickIAgree.click();
        waitFor(1);
    }

    @Given("enter credit card info")
    public void enter_credit_card_info() {
        paymentPage.setCardInfo();
        waitFor(1);
    }

    @Given("enter address info")
    public void enter_address_info() {
        paymentPage.setAddressInfo();
        waitFor(1);
    }


    @When("click Pay button")
    public void click_pay_button() {
        paymentPage.buttonPay.click();
        waitFor(1);
    }

    @Then("verify booking complete message {string}")
    public void verify_booking_complete_message(String expectedMessage) {
        String actualMessage = bookingCompletedPage.getSuccessMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}
