package com.krct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProductPage {

    WebDriver driver;
    WebDriverWait wait;

    public ProductPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public int getCartCount() {
        try {
            // CHANGE THIS LOCATOR - Use this instead of your old one
            WebElement cartBadge = driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']//label"));
            String countText = cartBadge.getText();

            if (countText.equals("")) {
                return 0;
            }
            return Integer.parseInt(countText);
        } catch (Exception e) {
            return 0;
        }
    }

    public void addProductByIndex(int productNumber) {
        List<WebElement> allProducts = driver.findElements(By.cssSelector(".card-body"));
        WebElement myProduct = allProducts.get(productNumber);

        // CHANGE THIS LOCATOR - Use this instead of your old one
        WebElement addToCartButton = myProduct.findElement(By.xpath(".//button[contains(text(),'Add To Cart')]"));
        addToCartButton.click();

        // Wait for 1 second for cart to update
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }

    public void addFirstProduct() {
        addProductByIndex(0);
    }

    public void addSecondProduct() {
        addProductByIndex(1);
    }

    public void addThirdProduct() {
        addProductByIndex(2);
    }
}