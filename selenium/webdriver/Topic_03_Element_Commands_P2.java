package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Element_Commands_P2 {
    WebDriver driver;
    By email = By.id("mail");
    By under_18 = By.id("under_18");
    By edu = By.id("edu");
    By user5 = By.xpath("//h5[text()='Name: User5']");
    By job_role_1 = By.id("job1");
    By job_role_2 = By.id("job2");
    By development = By.id("development");
    By slilder1 = By.id("slider-1");
    By java = By.id("java");



    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
    }

    @Test
    public void TC_01_Check_Element_Displayed(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        if (driver.findElement(email).isDisplayed()){
            driver.findElement(email).sendKeys("Automation testing");
            System.out.println("Email is displayed");
        } else {
            System.out.println("Email is not displayed");}

        if (driver.findElement(edu).isDisplayed()){
            driver.findElement(edu).sendKeys("Automation testing");
            System.out.println("Education is displayed");
        }   else {
            System.out.println("Education is not displayed");}

        if (driver.findElement(under_18).isDisplayed()){
            driver.findElement(under_18).click();
            System.out.println("Under18 is displayed");
        }   else {
            System.out.println("Under18 is not displayed");}

        Assert.assertFalse(driver.findElement(user5).isDisplayed());
        System.out.println("User5 is not displayed");
    }

    @Test
    public void TC_02_Check_Element_Enabled_Disabled(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        if (driver.findElement(email).isEnabled()){
            System.out.println("Email is enabled");
        } else { System.out.println("Email is disnabled");}

        if (driver.findElement(under_18).isEnabled()){
            System.out.println("Age under 18 is enabled");
        } else { System.out.println("Age under 18 is disnabled");}

        if (driver.findElement(edu).isEnabled()){
            System.out.println("Education is enabled");
        } else { System.out.println("education is disnabled");}

        if (driver.findElement(job_role_1).isEnabled()){
            System.out.println("Job role 1 is enabled");
        } else { System.out.println("Job role 1 is disnabled");}

        if (driver.findElement(job_role_2).isEnabled()){
            System.out.println("Job role 2 is enabled");
        } else { System.out.println("Job role 2 is disnabled");}

        if (driver.findElement(development).isEnabled()){
            System.out.println("Development is enabled");
        } else { System.out.println("Development is disnabled");}

        if (driver.findElement(slilder1).isEnabled()){
            System.out.println("Slider1 is enabled");
        } else { System.out.println("Slider1 is disnabled");}

    }

    @Test
    public void TC_03_Check_Element_Selected(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(under_18).click();
        driver.findElement(java).click();
        Assert.assertTrue(driver.findElement(under_18).isSelected());
        Assert.assertTrue(driver.findElement(java).isSelected());
        driver.findElement(java).click();
        Assert.assertFalse(driver.findElement(java).isSelected());
    }

    @Test
    public void TC_04_Register_Function_With_MailChimp(){
        driver.get("https://login.mailchimp.com/signup/");
        driver.findElement(By.id("email")).sendKeys("thuhuyen111@gmail.com");
        driver.findElement(By.id("email")).submit();
        sleepInSecond(3);

        // Nhập number
        driver.findElement(By.id("new_password")).sendKeys("1");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

        // Nhập chữ thường
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("abc");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

        // Nhập chữ hoa
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("HH");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

        // Kí tự đặc biệt
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("@!");
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

        //Nhập hơn 8 kí tự
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("ab12@AB123");
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());

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
