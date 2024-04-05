package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_13_Iframe_Frame {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_iFrame() {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
//        Click vào hình thì iframe mới load ra
        driver.findElement(By.cssSelector("div#imageTemplateContainer img")).click();
        sleepInSecond(3);
        driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='formTemplateContainer']/iframe")));
        Select year = new Select(driver.findElement(By.id("RESULT_RadioButton-2")));

        year.selectByVisibleText("Junior");

        Select residence = new Select(driver.findElement(By.id("RESULT_RadioButton-3")));
        residence.selectByVisibleText("East Dorm");

        if (!driver.findElement(By.xpath("//label[text()='Male']")).isSelected()){
            driver.findElement(By.xpath("//label[text()='Male']")).click();
        }

        driver.findElement(By.id("FSsubmit")).click();

        driver.switchTo().defaultContent();

        driver.findElement(By.cssSelector("nav.header--desktop-floater div>a.menu-item-login")).click();
        driver.findElement(By.id("login")).click();
        Assert.assertTrue(driver.findElement(By.id("message-error")).isDisplayed());

    }

    @Test
    public void TC_02_Frame() {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='login_page']")));
        driver.findElement(By.xpath("//input[@type='text']"))
                .sendKeys("automationtest");
        driver.findElement(By.cssSelector("a.login-btn")).click();

        driver.switchTo().defaultContent();
        Assert.assertTrue(driver.findElement(By.id("keyboard")).isDisplayed());
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
