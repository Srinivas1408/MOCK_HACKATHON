package com.krct;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected ExtentTest test;
    protected static ExtentReports extent;

    @BeforeSuite
    public void reportSetup(){
        ExtentSparkReporter reporter = new ExtentSparkReporter("reports/report.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    @BeforeMethod
    public void setup(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());

        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless");

        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client/#/auth/login");
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if(result.getStatus() == ITestResult.SUCCESS){
            test.pass("Test Passed");
        } else if(result.getStatus() == ITestResult.FAILURE){
            File folder = new File("screenshots");
            if(!folder.exists()){
                folder.mkdir();
            }
            String path = "screenshots/" + System.currentTimeMillis() + ".png";
            File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            Files.copy(src.toPath(), new File(path).toPath());
            test.fail(result.getThrowable());
            test.addScreenCaptureFromPath(path);
        }

        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
    }
}