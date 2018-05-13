package com.m.chirkov;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CheckLogsForErrors extends Login {

    public List<WebElement> GetAllRows() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".row")));
        List<WebElement> rows = driver.findElements(By.cssSelector(".row"));
        return rows;
    }

    public void CheckLogsForErr() throws Exception {
        LogEntries logs = driver.manage().logs().get("browser");

        for (LogEntry entry : logs) {
            String msg = entry.getMessage();
            if (msg.toLowerCase().contains("error")) {
                throw new Exception(msg);
            }
        }
    }


    @Test
    public void CheckGoodsPagesForErrs() throws Exception {
        loginAsAdmin();
        List<WebElement> menuItems = driver.findElements(By.cssSelector("#box-apps-menu > li[id='app-'] > a"));
        menuItems.get(1).click();

        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".row")));

        List<WebElement> rows;
        int counter = 0;

        while (true) {
            try {
                rows = GetAllRows();
                rows.get(counter).findElement(By.tagName("a")).click();
                counter++;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("All done");
                break;
            }

            try {
                // Проверяю что попал на страницу товара
                WebElement code = driver.findElement(By.name("name[en]"));

                CheckLogsForErr();
                ((JavascriptExecutor) driver).executeScript("window.history.go(-1)");
            } catch (NoSuchElementException e) {

            }
        }

    }

}
