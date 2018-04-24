package com.m.chirkov;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.WeakHashMap;

public class AlphabetSort extends Login {

    public void CheckAlphSort(ArrayList<String> list) {
        ArrayList<String> before_sort = (ArrayList<String>) list.clone();
        Collections.sort(list);
        Assert.assertEquals(before_sort, list);
    }

    public void SwitchToTab(int n) {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(n));
    }

    public void CloseTab() {
        JavascriptExecutor js;
        js = (JavascriptExecutor) driver;
        js.executeScript("window.close()");
    }

    public void openNewTab(String url) {
        JavascriptExecutor js;
        js = (JavascriptExecutor) driver;
        js.executeScript("window.open()");
        SwitchToTab(1);
        driver.get(url);
    }

    public String GetSelectValuse(WebElement sel) {
        Select select = new Select(sel);
        WebElement option = select.getFirstSelectedOption();
        return option.getText();
    }


    @Test
    public void AlphabetSortedCountries() {
        loginAsAdmin();

        driver.get("http://localhost/shop/admin/?app=countries&doc=countries");
        List<WebElement> c_lines = driver.findElements(By.cssSelector("form[name=\"countries_form\"] .dataTable tr.row"));
        ArrayList<String> c_names = new ArrayList<>();

        for (WebElement line : c_lines) {
            List<WebElement> line_data = line.findElements(By.tagName("td"));
            String c_name = line_data.get(4).getText();
            int c_time_zones = Integer.parseInt(line_data.get(5).getText());

            if (c_time_zones > 0) {
                String href = line_data.get(4).findElement(By.tagName("a")).getAttribute("href");
                openNewTab(href);

                ArrayList<String> t_zone_names = new ArrayList<>();
                List<WebElement> t_zones = driver.findElements(By.cssSelector("#table-zones tr"));
                for (int i = 0; i < t_zones.size(); i++) {
                    String t_zone_name = t_zones.get(2).getText();
                    t_zone_names.add(t_zone_name);
                }

                CheckAlphSort(t_zone_names);
                CloseTab();
                SwitchToTab(0);

            }

            c_names.add(c_name);
        }

        CheckAlphSort(c_names);
    }

    @Test
    public void AlphabetSortedGeoZones() {
        loginAsAdmin();

        driver.get("http://localhost/shop/admin/?app=geo_zones&doc=geo_zones");
        List<WebElement> c_lines = driver.findElements(By.cssSelector(".dataTable tr.row"));
        ArrayList<String> c_names = new ArrayList<>();

        for (WebElement line : c_lines) {
            List<WebElement> line_data = line.findElements(By.tagName("td"));
            String c_name = line_data.get(2).getText();
            int num_zones = Integer.parseInt(line_data.get(3).getText());

            if (num_zones > 0) {
                String href = line_data.get(2).findElement(By.tagName("a")).getAttribute("href");
                openNewTab(href);

                List<WebElement> rows = driver.findElements(By.cssSelector(".dataTable tr:not(.header)"));

                ArrayList<String> zone_names = new ArrayList<>();
                for (WebElement row : rows) {
                    List<WebElement> row_items = row.findElements(By.tagName("td"));
                    if (row_items.size() < 4) {
                        continue;
                    }
                    String name = GetSelectValuse(row_items.get(2).findElement(By.tagName("select")));
                    System.out.println(name);
                    zone_names.add(name);
                }

                CheckAlphSort(zone_names);
                CloseTab();
                SwitchToTab(0);
            }
            c_names.add(c_name);
        }
        CheckAlphSort(c_names);
    }

}
