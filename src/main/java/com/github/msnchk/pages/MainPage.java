package com.github.msnchk.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class MainPage extends BasePage{

    public MainPage(WebDriver driver, Actions actions) {
        super(driver, actions);
    }

    @FindBy(xpath = "//ul[contains(@class, 'menu-new')]//span[text()='Продукты и решения']")
    private WebElement txtProductsAndSolutions;

    @FindBy(xpath = "//ul[contains(@class, 'menu-new')]//span[text()='Инструменты для ИТ-команд']")
    private WebElement txtToolsForItTeams;

    @FindBy(linkText = "Nexign Nord")
    private WebElement txtNexignNord;


    public void openProductsAndSolutions() {
        performClick(txtProductsAndSolutions);
    }

    public void openToolsForItTeams() {
        performClick(txtToolsForItTeams);
    }

    public void openNexignNord() {
        performClick(txtNexignNord);
    }

    public int countWordNexignJava() {
        String pageText = getPageText().toLowerCase();
        return pageText.split("nexign").length - 1;
    }

    public int countWordNexignJavascript() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Object result = js.executeScript("return document.body.innerText;");
        String pageText = result != null ? result.toString().toLowerCase() : "";
        return pageText.split("nexign").length - 1;
    }

    public List<String> getAllInternalLinks() {
        String baseUrl = "https://nexign.com";

        return getDriver().findElements(By.tagName("a")).stream()
                .map(e -> Optional.ofNullable(e.getDomAttribute("href")).orElse(""))
                .filter(url -> url.contains("/ru/") && !url.contains("habr.com"))
                .map(url -> url.startsWith("/") ? baseUrl + url : url)
                .distinct()
                .collect(Collectors.toList());
    }
}
