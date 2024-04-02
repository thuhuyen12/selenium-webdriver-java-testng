package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Handle_Alert {
    WebDriver driver;
    WebDriverWait   explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
    }

    @Test
    public void TC_01_Accept_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");

    }

    public void TC_01_Accept_Alert_Cach2_Dung_ExplicitWait() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
//        Chờ cho alert is present
//        Nếu trong thời gian chờ mà xuất hiện thì tự switch vào
//        Nếu hết thời gian chờ mà chưa xuất hiện mới fail
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");
    }

    @Test
    public void TC_02_Confirm_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
//        Ok
        driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Ok");
//        Cancel
        driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();
        Alert alert_cancel = driver.switchTo().alert();
        alert_cancel.dismiss();
        Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");
    }

    @Test
    public void TC_03_Promp_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
//        Enter text và OK
        driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("Test Engineer");
        alert.accept();
        Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: Test Engineer");

//        Enter text và cancel
        driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();
        Alert alert_cancel = driver.switchTo().alert();
        alert_cancel.sendKeys("Test Engineer");
        alert_cancel.dismiss();
        Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: null");

    }

    @Test
    public void TC_04_Authenication_Alert() {
//        Cách 1: truyền thẳng username -password vào URL
        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
//        Cách 2: dùng AutoIt (Window)

//        Cách 3: Dùng cho Selenium 4.x
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    public void sleepInSecond (long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
