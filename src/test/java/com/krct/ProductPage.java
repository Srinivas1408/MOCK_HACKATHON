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
        WebElement cartBadge = driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']//label"));
        String countText = cartBadge.getText();

        if (countText.equals("")) {
            return 0;
        }
        return Integer.parseInt(countText);
    }

    public void addProductByIndex(int productNumber) throws InterruptedException {
        Thread.sleep(2000);

        List<WebElement> allProducts = driver.findElements(By.cssSelector(".card-body"));
        WebElement myProduct = allProducts.get(productNumber);

        WebElement addToCartButton = myProduct.findElement(By.xpath(".//button[contains(text(),'Add To Cart')]"));
        addToCartButton.click();

        Thread.sleep(1500);
    }

    public void addFirstProduct() throws InterruptedException {
        addProductByIndex(0);
    }

    public void addSecondProduct() throws InterruptedException {
        addProductByIndex(1);
    }

    public void addThirdProduct() throws InterruptedException {
        addProductByIndex(2);
    }

    public void clickCartIcon() throws InterruptedException {
        Thread.sleep(1000);
        WebElement cartIcon = driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']"));
        cartIcon.click();
        Thread.sleep(2000);
    }
}