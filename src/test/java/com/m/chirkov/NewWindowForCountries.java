package com.m.chirkov;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NewWindowForCountries extends Login {

    @Test
    public void NewWindowsInForm() {
        loginAsAdmin();

        driver.get(host + "/admin/?app=countries&doc=countries");
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Add New Country")));
        WebElement add_new_country_btn = driver.findElement(By.partialLinkText("Add New Country"));
        add_new_country_btn.click();

        List<WebElement> links_in_form = driver.findElements(By.cssSelector("form td a:not(#address-format-hint)"));
        System.out.println(links_in_form);

        for (WebElement link : links_in_form) {
            link.click();
            SwitchToTab(1);
            CloseTab();
            SwitchToTab(0);
        }
        
    }

}
