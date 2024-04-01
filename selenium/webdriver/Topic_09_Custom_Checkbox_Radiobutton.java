package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Custom_Checkbox_Radiobutton {
    WebDriver driver;
    JavascriptExecutor executor;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        executor = (JavascriptExecutor)driver;

    }

    @Test
    public void TC_01_Custom_Radiobutton_Cach1() {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        WebElement checkbox = driver.findElement(By.xpath("//div[@data-value='Cần Thơ']/div/div/div"));
        Assert.assertFalse(checkbox.isDisplayed());

// Click vào element
        executor.executeScript("arguments[0].click()",checkbox);
// Kiểm tra element đã được click
        Assert.assertTrue(driver.findElement(By.xpath("//div[@data-value='Cần Thơ']")).isDisplayed());

    }

    @Test
    public void TC_02_Custom_Radiobutton_Cach2() {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        WebElement checkbox = driver.findElement(By.xpath("//div[@data-value='Cần Thơ']/div/div/div"));
        Assert.assertFalse(checkbox.isDisplayed());
// không cần khai báo biến và khởi tạo, vì cả JS và driver đều là Interface. nên ép kiểu như sau:
        JavascriptExecutor js = (JavascriptExecutor) driver;
// Click vào element
        js.executeScript("arguments[0].click()",checkbox);
// Kiểm tra element đã được click
        Assert.assertTrue(driver.findElement(By.xpath("//div[@data-value='Cần Thơ']")).isDisplayed());

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
