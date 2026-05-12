package com.krct;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    public void deleteProductByIndex(int index) {
        List<WebElement> deleteButtons = driver.findElements(By.cssSelector(".cartSection .btn-danger"));
        deleteButtons.get(index).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteFirstProduct() {
        deleteProductByIndex(0);
    }

    public void clickCheckoutButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500)");
        WebElement checkout = driver.findElement(By.xpath("//button[contains(text(),'Checkout')]"));
        js.executeScript("arguments[0].click();", checkout);
    }

    public void selectCountry(String countryName) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement country = driver.findElement(By.xpath("//input[@placeholder='Select Country']"));
        js.executeScript("arguments[0].scrollIntoView(true);", country);
        js.executeScript("arguments[0].click();", country);
        country.sendKeys(countryName);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement option = driver.findElement(By.xpath("//span[text()=' " + countryName + "']"));
        js.executeScript("arguments[0].click();", option);
    }

    public void clickPlaceOrder() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement placeOrder = driver.findElement(By.xpath("//a[contains(text(),'Place Order')]"));
        js.executeScript("arguments[0].scrollIntoView(true);", placeOrder);
        js.executeScript("arguments[0].click();", placeOrder);
    }

    public String getSuccessMessage() {
        return driver.findElement(By.cssSelector(".hero-primary")).getText();
    }
}