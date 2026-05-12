package com.krct;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Testing extends BaseTest
{
    //module 1
    @Test(priority = 1)
    public void loginSucessTest() {

        RegisterPage reg = new RegisterPage(driver, wait);
        LoginPage login = new LoginPage(driver,wait);

        reg.openLoginPage();

        String password = "Abcd@1234";

        reg.registerAccount("Srinivas", "ABC", "1234567890", password, password, "Student", "Male");

        WebElement successText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h1[contains(text(),'Account Created Successfully')]")
                )
        );

        Assert.assertTrue(successText.isDisplayed());

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Login')]")
        )).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.id("userEmail")
        ));

        // use stored email
        login.login("srinivas1408@gmail.com", "Abcd@1234");
    }
    @Test(priority = 2)
    public void loginfailureTest()
    {
        RegisterPage reg = new RegisterPage(driver, wait);
        LoginPage login = new LoginPage(driver,wait);

        reg.openLoginPage();

        Assert.assertEquals(driver.getCurrentUrl(), "https://rahulshettyacademy.com/client/#/auth/login");

        login.login("srinivas1408@gmail.com", "Abcd1234");

        By errorMsg = By.cssSelector(".toast-message"); // adjust if needed

        WebElement toast = wait.until(
                ExpectedConditions.visibilityOfElementLocated(errorMsg)
        );

        String actualMsg = toast.getText();

        Assert.assertTrue(actualMsg.contains("Incorrect email or password"));

    }
    @Test(priority = 3)
    public void LogoutTest()
    {
        RegisterPage reg = new RegisterPage(driver, wait);
        LoginPage login = new LoginPage(driver,wait);

        reg.openLoginPage();

        String password = "Abcd@1234";

        reg.registerAccount("Srinivas", "ABC", "1234567890", password, password, "Student", "Male");

        WebElement successText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h1[contains(text(),'Account Created Successfully')]")
                )
        );

        Assert.assertTrue(successText.isDisplayed());

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Login')]")
        )).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.id("userEmail")
        ));

        // use stored email
        login.login("srinivas1408@gmail.com", "Abcd@1234");



        WebElement logout = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[normalize-space()='Sign Out']")
                )
        );
        logout.click();

        wait.until(ExpectedConditions.urlContains("/auth/login"));

        Assert.assertTrue(driver.getCurrentUrl().contains("/auth/login"));

    }
    @Test(priority = 4)
    public void emptyloginpasswordTest()
    {
        RegisterPage reg = new RegisterPage(driver, wait);
        LoginPage login = new LoginPage(driver,wait);

        reg.openLoginPage();

        login.login(" ","");

        WebElement emailError = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(text(),'Email is required')]")
                )
        );

        Assert.assertTrue(emailError.isDisplayed());
        Assert.assertEquals(emailError.getText().trim(), "*Email is required");


        WebElement passwordError = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(text(),'Password is required')]")
                )
        );

        Assert.assertTrue(passwordError.isDisplayed());
        Assert.assertEquals(passwordError.getText().trim(), "*Password is required");

    }

    //module 2
    @Test(priority = 5)
    public void productDashboardTest()
    {
        LoginPage login = new LoginPage(driver,wait);
        login.login("srinivas1408@gmail.com", "Abcd@1234");

        List<WebElement> products = driver.findElements(
                By.cssSelector(".card-body")
        );

        Assert.assertTrue(products.size() > 0);

    }
    @Test(priority = 6)
    public void prductnamepriceTest()
    {
        LoginPage login = new LoginPage(driver,wait);
        login.login("srinivas1408@gmail.com", "Abcd@1234");

        List<WebElement> products = driver.findElements(
                By.cssSelector(".card-body")
        );

        Assert.assertTrue(products.size() > 0);

        for (WebElement product : products) {

            WebElement name = product.findElement(By.tagName("b"));
            WebElement price = product.findElement(By.tagName("h5"));

            Assert.assertTrue(name.isDisplayed());
            Assert.assertTrue(price.isDisplayed());
        }
    }
    @Test(priority = 7)
    public void verifySingleProductAddToCart() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver, wait);
        ProductPage productPage = new ProductPage(driver, wait);
        loginPage.login("srinivas1408@gmail.com", "Abcd@1234");

        int countBefore = productPage.getCartCount();
        productPage.addFirstProduct();
        int countAfter = productPage.getCartCount();

        Assert.assertEquals(countAfter, countBefore + 1);
    }

    @Test(priority = 8)
    public void verifyMultipleProductsAddToCart() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver, wait);
        ProductPage productPage = new ProductPage(driver, wait);
        loginPage.login("srinivas1408@gmail.com", "Abcd@1234");

        int countBefore = productPage.getCartCount();

        productPage.addFirstProduct();
        productPage.addSecondProduct();
        productPage.addThirdProduct();

        int countAfter = productPage.getCartCount();

        Assert.assertEquals(countAfter, countBefore + 3);
    }

    //module 3
    @Test(priority = 9)
    public void verifyCartProductsWithNamesAndPrices()
    {
        LoginPage loginPage=new LoginPage(driver,wait);
        ProductPage productPage=new ProductPage(driver,wait);
        loginPage.login("srinivas1408@gmail.com", "Abcd@1234");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".card-body")));

        try {
            productPage.addFirstProduct();
            productPage.addSecondProduct();
            productPage.addThirdProduct();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            productPage.clickCartIcon();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".cartSection")));

        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

        Assert.assertFalse(cartProducts.isEmpty());
        Assert.assertEquals(cartProducts.size(), 3);
    }
    @Test(priority = 10)
    public void verifyDeleteProductFromCart() throws InterruptedException
    {
        LoginPage loginPage=new LoginPage(driver,wait);
        ProductPage productPage=new ProductPage(driver,wait);

        loginPage.login("srinivas1408@gmail.com", "Abcd@1234");
        Thread.sleep(3000);

        productPage.addFirstProduct();
        Thread.sleep(500);
        productPage.addSecondProduct();
        Thread.sleep(500);
        productPage.addThirdProduct();

        productPage.clickCartIcon();
        Thread.sleep(2000);

        int beforeCount = driver.findElements(By.cssSelector(".cartSection h3")).size();
        Assert.assertEquals(beforeCount, 3);

        driver.findElements(By.cssSelector(".cartSection .btn-danger")).get(0).click();
        Thread.sleep(2000);

        int afterCount = driver.findElements(By.cssSelector(".cartSection h3")).size();
        Assert.assertEquals(afterCount, beforeCount - 1);
    }
    @Test(priority = 11)
    public void verifySubtotalAndTotalAreEqual() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver, wait);
        ProductPage productPage = new ProductPage(driver, wait);

        loginPage.login("srinivas1408@gmail.com", "Abcd@1234");
        Thread.sleep(3000);

        productPage.addFirstProduct();
        productPage.addSecondProduct();
        productPage.addThirdProduct();

        productPage.clickCartIcon();
        Thread.sleep(3000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(2000);

        String subtotalText = driver.findElement(By.xpath("//div[contains(@class,'subtotal')]")).getText();
        String totalText = driver.findElement(By.xpath("//div[contains(@class,'total')]")).getText();

        Assert.assertEquals(subtotalText, totalText);
    }
    @Test(priority = 12)
    public void verifyOrderPlacedSuccessfully() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver, wait);
        ProductPage productPage = new ProductPage(driver, wait);

        loginPage.login("srinivas1408@gmail.com", "Abcd@1234");
        Thread.sleep(3000);

        productPage.addFirstProduct();
        productPage.addSecondProduct();
        productPage.addThirdProduct();

        productPage.clickCartIcon();
        Thread.sleep(2000);

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollBy(0, 500)");
        Thread.sleep(1000);

        WebElement checkoutButton = driver.findElement(By.xpath("//button[contains(text(),'Checkout')]"));
        js.executeScript("arguments[0].click();", checkoutButton);
        Thread.sleep(2000);

        WebElement countryField = driver.findElement(By.xpath("//input[@placeholder='Select Country']"));
        js.executeScript("arguments[0].scrollIntoView(true);", countryField);
        Thread.sleep(1000);
        js.executeScript("arguments[0].click();", countryField);
        countryField.sendKeys("India");
        Thread.sleep(2000);

        WebElement countryOption = driver.findElement(By.xpath("//span[text()=' India']"));
        js.executeScript("arguments[0].click();", countryOption);
        Thread.sleep(1000);

        WebElement placeOrderButton = driver.findElement(By.xpath("//a[contains(text(),'Place Order')]"));
        js.executeScript("arguments[0].scrollIntoView(true);", placeOrderButton);
        Thread.sleep(1000);
        js.executeScript("arguments[0].click();", placeOrderButton);
        Thread.sleep(3000);

        String successMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertEquals(successMessage, "THANKYOU FOR THE ORDER.");
    }
}


