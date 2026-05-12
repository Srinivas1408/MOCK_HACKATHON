package com.krct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CartPage {

    WebDriver driver;
    WebDriverWait wait;

    public CartPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public int getCartItemCount() {
        List<WebElement> products = driver.findElements(By.cssSelector(".cartSection h3"));
        return products.size();
    }

    public String getProductName(int index) {
        List<WebElement> products = driver.findElements(By.cssSelector(".cartSection h3"));
        return products.get(index).getText();
    }

    public String getProductPrice(int index) {
        List<WebElement> prices = driver.findElements(By.cssSelector(".cartSection .product-price"));
        return prices.get(index).getText();
    }
}