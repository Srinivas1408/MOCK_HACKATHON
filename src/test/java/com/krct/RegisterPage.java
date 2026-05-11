package com.krct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

    private WebDriver driver;
    private WebDriverWait wait;


    public String emailValue;

    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void openLoginPage() {
        driver.get("https://rahulshettyacademy.com/client/#/auth/login");
    }

    private final By registerLink =
            By.xpath("//a[contains(text(),'Register') or contains(text(),'REGISTER')]");

    private final By firstName = By.id("firstName");
    private final By lastName = By.id("lastName");
    private final By email = By.id("userEmail");
    private final By phone = By.id("userMobile");
    private final By password = By.id("userPassword");
    private final By confirmPassword = By.id("confirmPassword");

    private final By genderMale = By.xpath("//input[@value='Male']");
    private final By genderFemale = By.xpath("//input[@value='Female']");

    private final By occupationDropdown =
            By.xpath("//*[contains(@name,'occupation') or contains(text(),'Occupation')]");

    private final By ageCheckbox = By.xpath("//input[@type='checkbox']");
    private final By registerBtn = By.id("login");

    public String generateEmail() {
        return "test" + System.currentTimeMillis() + "@gmail.com";
    }

    public void clickRegisterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
    }

    public void setFirstName(String fname) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(fname);
    }

    public void setLastName(String lname) {
        driver.findElement(lastName).sendKeys(lname);
    }

    public void setEmail(String mail) {
        driver.findElement(email).sendKeys(mail);
    }

    public void setPhone(String ph) {
        driver.findElement(phone).sendKeys(ph);
    }

    public void setPassword(String pass) {
        driver.findElement(password).sendKeys(pass);
    }

    public void setConfirmPassword(String cpass) {
        driver.findElement(confirmPassword).sendKeys(cpass);
    }

    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("male")) {
            driver.findElement(genderMale).click();
        } else {
            driver.findElement(genderFemale).click();
        }
    }

    public void selectOccupation(String occ) {
        wait.until(ExpectedConditions.elementToBeClickable(occupationDropdown)).click();
        driver.findElement(By.xpath("//*[text()='" + occ + "']")).click();
    }

    public void clickAgeCheckbox() {
        driver.findElement(ageCheckbox).click();
    }

    public void clickRegisterButton() {
        driver.findElement(registerBtn).click();
    }


    public void registerAccount(String fname, String lname, String phoneNo,
                                String pass, String cpass,
                                String occ, String gender) {

        emailValue = generateEmail();

        clickRegisterLink();

        setFirstName(fname);
        setLastName(lname);
        setEmail(emailValue);
        setPhone(phoneNo);

        selectGender(gender);
        selectOccupation(occ);

        setPassword(pass);
        setConfirmPassword(cpass);

        clickAgeCheckbox();
        clickRegisterButton();
    }
}