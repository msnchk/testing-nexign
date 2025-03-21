package com.github.msnchk.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected WebDriver driver;
    protected Actions actions;

    public BasePage(WebDriver driver, Actions actions) {
        this.driver = driver;
        this.actions = actions;
        PageFactory.initElements(driver, this);
    }

    public void performClick(WebElement element) {
        actions.moveToElement(element).click().perform();
    }

    public String getPageText() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return document.body.innerText;");
    }

    public WebDriver getDriver() {
        return driver;
    }
}
