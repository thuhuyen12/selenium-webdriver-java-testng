package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.time.Duration;
import java.util.Random;

public class Topic_04_Textbox_TextArea {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_Login_With_Empty_Email_Password() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='block-title']/following-sibling::ul//a[@title='My Account']")).click();
        driver.findElement(By.xpath("//button[@title='Login']")).click();
        Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
    }

    @Test
    public void TC_02_Login_With_Invalid_Email() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='block-title']/following-sibling::ul//a[@title='My Account']")).click();
        driver.findElement(By.id("email")).sendKeys("12345@1234");
        driver.findElement(By.id("pass")).sendKeys("123456");
        driver.findElement(By.xpath("//button[@title='Login']")).click();
        Assert.assertEquals(driver.findElement(By.className("validation-advice")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");

    }


    @Test
    public void TC_03_Login_With_Password_Less_Than_6_Char() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='block-title']/following-sibling::ul//a[@title='My Account']")).click();
        driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("1234");
        driver.findElement(By.xpath("//button[@title='Login']")).click();
        Assert.assertEquals(driver.findElement(By.className("validation-advice")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");

    }

    @Test
    public void TC_04_Login_With_Incorrect_Password() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='block-title']/following-sibling::ul//a[@title='My Account']")).click();
        driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("123123123");
        driver.findElement(By.xpath("//button[@title='Login']")).click();
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");
    }

    @Test
    public void TC_05_Create_Account() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='block-title']/following-sibling::ul//a[@title='My Account']")).click();
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        driver.findElement(By.id("firstname")).sendKeys("thu");
        driver.findElement(By.id("lastname")).sendKeys("huyen");
        String email = ranEmail();
        driver.findElement(By.id("email_address")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys("12345678");
        driver.findElement(By.id("confirmation")).sendKeys("12345678");
        driver.findElement(By.xpath("//button[@title ='Register']")).click();
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");

        String text = driver.findElement(By.xpath("//div[@class='box-content']//a//parent::p")).getText();
        Assert.assertTrue(text.contains("thu huyen"));
        Assert.assertTrue(text.contains(email));

        driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
        driver.findElement(By.xpath("//a[@title='Log Out']")).click();
        sleepInSecond(6);
        Assert.assertEquals(driver.getTitle(), "Home page");


    }

/*    @Test
    public void TC_06_Orange_Hrm() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
        driver.findElement(By.name("firstName")).sendKeys("thu");
        driver.findElement(By.name("lastName")).sendKeys("huyen");

        String employeeId = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getText();
        System.out.println(employeeId);

        driver.findElement(By.cssSelector("span.oxd-switch-input")).click();
        driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys("thuhuyen123");
        driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys("thuhuyen123");
        driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys("thuhuyen123");
        driver.findElement(By.xpath("//button[text()=' Save ']")).click();

    }*/


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
