package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Topic_14_Window_Tab {
    WebDriver driver;
    Actions action;
    Alert alert;
    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        Actions action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_Tab() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
//        Get ID của tab cha
        String parent_tab = driver.getWindowHandle();
        System.out.println(parent_tab);
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        sleepInSecond(3);

//        Trên UI tab mới tự mở và mặc định là tab đang đưuọc active
//        Nhưng ở code thì vẫn đang active ở tab cũ nên cần switch qua mới thao tác vs element trên tab mới
        Set<String> tabs = driver.getWindowHandles();
        for(String tab : tabs){
            if(!tab.equals(parent_tab)){
                driver.switchTo().window(tab);
                sleepInSecond(3);

            }
        }
        String child_tab = driver.getWindowHandle();
        System.out.println(child_tab);
        Assert.assertEquals(driver.getTitle(), "Google");

//        Switch về parent tab
        driver.switchTo().window(parent_tab);
    }

    @Test
    public void TC_02_Window() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//li[@class='level0 nav-1 first']")).click();
        driver.findElement(By.xpath("//a[@title='Xperia']/following-sibling::div//li[2]/a")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
                "The product Sony Xperia has been added to comparison list.");

        driver.findElement(By.xpath("//a[@title='IPhone']/following-sibling::div//li[2]/a")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
                "The product IPhone has been added to comparison list.");

        driver.findElement(By.cssSelector("button[title='Compare']")).click();
//        Sau khi click, window mới được bật lên tự động, cần switchTo qua
//        Get ra ID của window hiện tại trước, rồi get Set của cả 2
        String window_parent = driver.getWindowHandle();
        System.out.println(window_parent);
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows){
            if(!window.equals(window_parent)){
                driver.switchTo().window(window);
            }
        }
        String title_child = driver.getTitle();
        System.out.println("title window " + title_child);
        Assert.assertEquals(title_child, "Products Comparison List - Magento Commerce");
        driver.close();

//        Chuyển về parent window
        driver.switchTo().window(window_parent);
        sleepInSecond(2);
        driver.findElement(By.xpath("//a[text()='Clear All']")).click();
        sleepInSecond(2);
        alert = driver.switchTo().alert();
        alert.accept();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
                "The comparison list was cleared.");
    }

    @Test
    public void TC_03_Window_2() {
        driver.get("https://dictionary.cambridge.org/vi/#google_vignette");
        String window_parent = driver.getWindowHandle();
        System.out.println(window_parent);
        driver.findElement(By.xpath("//div[@class='hdn hdib-s']//span[text()='Đăng nhập']")).click();

        switchWindowTab(window_parent);
        driver.findElement(By.xpath("//input[@value='Log in']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//input[@aria-label='Email' and @name='username']/following-sibling::span"))
                .getText(), "This field is required");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@aria-label='Email' and @name='username']/following-sibling::span"))
                .getText(), "This field is required");

        driver.close();
        driver.switchTo().window(window_parent);
        driver.findElement(By.id("searchword")).sendKeys("automation");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        sleepInSecond(3);
        Assert.assertTrue(driver.getPageSource().contains("automation"));
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

    public void switchWindowTab(String window_parent){
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows){
            if(!window.equals(window_parent)){
                driver.switchTo().window(window);
            }
        }
        String title_child = driver.getTitle();
        System.out.println("title window " + title_child);
    }
}
