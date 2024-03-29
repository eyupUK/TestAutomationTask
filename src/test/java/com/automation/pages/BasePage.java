package com.automation.pages;


import com.automation.step_defs.Hooks;
import com.automation.utilities.Driver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    public BasePage() {
        PageFactory.initElements(Hooks.getDriver(), this);
    }
}
