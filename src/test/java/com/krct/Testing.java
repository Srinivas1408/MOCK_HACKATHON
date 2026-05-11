package com.krct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Testing extends BaseTest {

    @Test(priority = 1)
    public void loginSucessTest() {

        RegisterPage reg = new RegisterPage(driver, wait);
        LoginPage login = new LoginPage(driver);

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
        LoginPage login = new LoginPage(driver);

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
        LoginPage login = new LoginPage(driver);

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
        LoginPage login = new LoginPage(driver);

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

    }
