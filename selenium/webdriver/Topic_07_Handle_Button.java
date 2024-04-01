package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.sql.SQLOutput;

public class Topic_07_Handle_Button {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
    }

    @Test
    public void TC_01_Fahasa() {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
        Assert.assertFalse(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());
        String background_before =driver.findElement(By.cssSelector("button.fhs-btn-login")).getCssValue("background");
        System.out.println("color before " + background_before);
        Assert.assertTrue(background_before.contains("rgb(224, 224, 224)"));

        driver.findElement(By.id("login_username")).sendKeys("thu@gmail.com");
        driver.findElement(By.id("login_password")).sendKeys("huyen1234");
        Assert.assertTrue(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());
        sleepInSecond(3);
        String background_after =driver.findElement(By.cssSelector("button.fhs-btn-login")).getCssValue("background-color");
        System.out.println(background_after);

        // Chuyển đổi hệ màu hexa <=> rgb/rgba: dùng thư viện Selenium Color
//        1. Đổi từ String qua Color
        Color background_color = Color.fromString(background_after);
//        2. Đổi qua rbga, và đổi ngược lại String để verify
        String color_after = background_color.asHex().toUpperCase();
        System.out.println("color after "+ color_after);
//        3. Verify
        Assert.assertTrue(color_after.contains("#C92127"));

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
