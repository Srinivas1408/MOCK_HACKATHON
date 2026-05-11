package com.krct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductPage {

    WebDriver driver;
    WebDriverWait wait;

    public ProductPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void addFirstProductToCart() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card-body")));

        List<WebElement> products = driver.findElements(By.cssSelector(".card-body"));

        WebElement firstProduct = products.get(0);

        WebElement addToCartBtn = firstProduct.findElement(
                By.xpath(".//button[contains(text(),'Add to Cart')]")
        );

        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));
        addToCartBtn.click();
    }

    public int getCartCount() {

        WebElement cartBadge = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("label[style*='background-color']")
                )
        );

        String text = cartBadge.getText().trim();

        if (text.isEmpty()) {
            return 0;
        }

        return Integer.parseInt(text);
    }
    public void addSecondProductToCart() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card-body")));

        List<WebElement> products = driver.findElements(By.cssSelector(".card-body"));

        WebElement secondProduct = products.get(1);

        WebElement addToCartBtn = secondProduct.findElement(
                By.xpath(".//button[contains(text(),'Add to Cart')]")
        );

        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));
        addToCartBtn.click();
    }
    public void addThirdProductToCart() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card-body")));

        List<WebElement> products = driver.findElements(By.cssSelector(".card-body"));

        WebElement thirdProduct = products.get(2);

        WebElement addToCartBtn = thirdProduct.findElement(
                By.xpath(".//button[contains(text(),'Add to Cart')]")
        );

        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));
        addToCartBtn.click();
    }
}