package com.krct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait=wait;
    }

    private final By loginemail = By.id("userEmail");
    private final By loginpass = By.id("userPassword");
    private final By loginbtn = By.id("login");

    public void setLoginemail(String email) {
        driver.findElement(loginemail).sendKeys(email);
    }

    public void setLoginPassword(String pass) {
        driver.findElement(loginpass).sendKeys(pass);
    }

    public void clickloginbtn() {
        driver.findElement(loginbtn).click();
    }

    public void login(String email, String pass) {
        setLoginemail(email);
        setLoginPassword(pass);
        clickloginbtn();
    }
}