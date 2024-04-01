package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_08_Default_Checkbox_Radiobutton {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Test
    public void TC_01_Default_Checkbox() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        By checkbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");
        sleepInSecond(3);
        if (!driver.findElement(checkbox).isSelected()) {
            driver.findElement(checkbox).click();
        }
        Assert.assertTrue(driver.findElement(checkbox).isSelected());
        sleepInSecond(2);

        // Bỏ chọn
        if (driver.findElement(checkbox).isSelected()) {
            driver.findElement(checkbox).click();
        }
        Assert.assertFalse(driver.findElement(checkbox).isSelected());

        //Radio button
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        driver.findElement(By.id("engine3")).click();
        if (driver.findElement(By.id("engine3")).isSelected()){
            System.out.println("radiobutton đã được chọn");
        } else {
            driver.findElement(By.id("engine3")).click();
        }

    }

    @Test
    public void TC_02_Select_All_Checkbox() {
        driver.get("https://automationfc.github.io/multiple-fields/");
        List<WebElement> allcheckbox = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for (WebElement checkbox : allcheckbox){
            if (!checkbox.isSelected()){
                checkbox.click();
            }
            Assert.assertTrue(checkbox.isSelected());
        }
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        // Kiểm tra nhiều điều kiện
        List<WebElement> allcheckbox_after = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for (WebElement checkbox : allcheckbox_after){
            if (checkbox.getAttribute("value").equals("Heart Attack") && !checkbox.isSelected()){
                checkbox.click();
            }
        }
        for (WebElement checkbox :allcheckbox_after){
            if (checkbox.getAttribute("value").equals("Heart Attack")){
                Assert.assertTrue(checkbox.isSelected());
            }
        }


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
