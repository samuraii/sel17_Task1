package com.m.chirkov;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GoodsClicks extends Login {

    private void CheckPriceColorAndText(WebElement good) {
        WebElement sale_good_price = good.findElement(By.className("campaign-price"));
        WebElement regular_good_prcie = good.findElement(By.className("regular-price"));

        // Получаю значения атрибутов для цен
        String sale_price_text_size = sale_good_price.getCssValue("font-size");
        String regular_price_text_size = regular_good_prcie.getCssValue("font-size");

        // Очищаю значения от букв и перевожу в цифры, сравниваю
        float sale_price_text_size_int = Float.parseFloat(sale_price_text_size.replaceAll("[^0-9.]", ""));
        float regular_price_text_size_int = Float.parseFloat(regular_price_text_size.replaceAll("[^0-9.]", ""));
        Boolean sale_price_bigger = sale_price_text_size_int > regular_price_text_size_int;
        Assert.assertTrue(sale_price_bigger);

        // Проверяю на красный
        String regular_price_text_color = regular_good_prcie.getCssValue("color");
        String regular_price_text_color_val = regular_price_text_color.replaceAll("[a-z() ]+", "");
        String[] vals = regular_price_text_color_val.split(",");
        Boolean grey_color = Integer.parseInt(vals[0]) == Integer.parseInt(vals[1]) && Integer.parseInt(vals[1]) == Integer.parseInt(vals[2]);
        Assert.assertTrue(grey_color);

        // Проверяю на серый цвет
        String sale_price_text_color = sale_good_price.getCssValue("color");
        String sale_price_text_color_val = sale_price_text_color.replaceAll("[a-z() ]+", "");
        String[] vals_c = sale_price_text_color_val.split(",");
        Boolean red = Integer.parseInt(vals_c[1]) == Integer.parseInt(vals_c[2]);
        Assert.assertTrue(red);
    }

    private void CheckClick(WebElement good) {
        String good_href = good.findElement(By.tagName("a")).getAttribute("href");
        String good_name = good.findElement(By.className("name")).getText();
        // Сохраняем всю строку с ценами для сравнения на странице товара
        String good_price = good.findElement(By.className("price-wrapper")).getText();

        // Если у товара есть акционная цена
        try {

            CheckPriceColorAndText(good);

        } catch (ElementNotFoundException ex) {

        } catch (NoSuchElementException ex) {

        }

        OpenNewTab(good_href);

        String page_good_name = driver.findElement(By.cssSelector("h1.title")).getText();
        String page_good_price = driver.findElement(By.className("price-wrapper")).getText();

        Assert.assertEquals(good_name, page_good_name);
        Assert.assertEquals(good_price, page_good_price);

        CloseTab();
        SwitchToTab(0);
    }

    @Test
    public void ClickGoods() {
        List<WebElement> goods_items = driver.findElements(By.className("product"));

        for (int i = 0; i < goods_items.size(); i++) {

            // Если у товара есть акционная цена
            try {

                CheckPriceColorAndText(goods_items.get(i));

            } catch (ElementNotFoundException ex) {

            } catch (NoSuchElementException ex) {

            }

            CheckClick(goods_items.get(i));
        }
    }
}
