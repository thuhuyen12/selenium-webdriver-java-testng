package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_05_Default_Dropdown {
    WebDriver driver;
    String firstname= "thu";
    String lastname = "huyen";
    String day = "12";
    String month = "December";
    String year = "1997";
    String email = ranEmail();
    String password ="123456";

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }

    @Test
    public void TC_01_Default_Dropdown() {
        driver.get("https://demo.nopcommerce.com/register");
        driver.findElement(By.id("FirstName")).sendKeys(firstname);
        driver.findElement(By.id("LastName")).sendKeys(lastname);

        Select day = new Select(driver.findElement(By.name("DateOfBirthDay")));
        day.selectByVisibleText(this.day);
        Assert.assertEquals(day.getOptions().size(), 32);

        Select month = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        month.selectByVisibleText(this.month);
        Assert.assertEquals(month.getOptions().size(), 13);


        Select year = new Select(driver.findElement(By.name("DateOfBirthYear")));
        year.selectByVisibleText(this.year);
        Assert.assertEquals(year.getOptions().size(), 112);

        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
        driver.findElement(By.cssSelector("button#register-button")).click();
//        driver.findElement(By.className("ico-logout")).click();

        driver.findElement(By.className("ico-login")).click();
        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        driver.findElement(By.className("ico-account")).click();
        driver.findElement(By.id("FirstName")).getAttribute("value");
        Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstname);
        Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastname);

        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(), this.day);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(), this.month);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(), this.year);
        Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), email);

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
    public String ranEmail (){
        Random random = new Random();
        int ranNumber = random.nextInt(9999);
        return "thuhuyen" + ranNumber + "@gmail.com";
    }

}
