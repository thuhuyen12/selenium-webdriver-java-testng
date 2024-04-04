package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.KeyInput;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Topic_11_User_Interaction {
    WebDriver driver;
    Actions actions;
    Alert alert;


    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        actions = new Actions (driver) ;

    }

    @Test
    public void TC_01_Hover_Tooltip() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        actions.moveToElement(driver.findElement(By.id("age"))).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),
                "We ask for your age only for statistical purposes.");

    }

    @Test
    public void TC_02_Hover_To_Element() {
        driver.get("https://www.myntra.com/");
        actions.moveToElement(driver.findElement(By.xpath("//a[@data-group='kids']"))).perform();
        driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.title-container h1")).getText(), "Kids Home Bath");
    }

    @Test
    public void TC_03_Hover_To_Element() {
        driver.get("https://www.fahasa.com/");
        actions.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
        actions.moveToElement(driver.findElement(By.xpath("//a[@title='Sách Trong Nước']"))).perform();
    }

    @Test
    public void TC_04_Click_and_hold() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List <WebElement> items = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
//      Giu chuot tai 1, keo den 4, nha chuot ra
        actions.clickAndHold(items.get(0))
                .moveToElement(items.get(3))
                .release()
                .perform();
//      Verify co 4 phan tu duooc chon
        List<WebElement> itemsSelected = driver.findElements(By.cssSelector("li.ui-state-default.ui-selectee.ui-selected"));
        Assert.assertEquals(itemsSelected.size(), 4);

//       Verify text của các phần tử
//        Bước 1: Lấy các phần tử lưu vào 1 list
        List<String> allNumberExpected = new ArrayList<String>();
        allNumberExpected.add("1");
        allNumberExpected.add("2");
        allNumberExpected.add("3");
        allNumberExpected.add("4");
//        Bước 2: Get text của List WebElement đã được chọn (itemsSelected) lưu vào 1 biến khác
        List<String> allNumberActual = new ArrayList<String>();

        for (WebElement element : itemsSelected){
            allNumberActual.add(element.getText());
        }
//      Bước 3: Verify
        Assert.assertEquals(allNumberExpected, allNumberActual );
    }

    @Test
    public void TC_05_Click_and_Select() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List <WebElement> items = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
//      Dùng Ctrl trên bàn phím để chọn nhiều, tương ứng với keyDown, keyUp
        actions.keyDown(Keys.CONTROL).perform();
        actions.click(items.get(1)).click(items.get(3)).click(items.get(6)).click(items.get(11)).perform();
        actions.keyUp(Keys.CONTROL).perform();
    }
    @Test
    public void TC_06_Double_Click() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
//        Scroll tới element cần tương tác-  Chỉ dùng với Chrome
        actions.scrollToElement(driver.findElement(By.xpath("//button[@ondblclick='doubleClickMe()']"))).perform();
        sleepInSecond(2);
        actions.doubleClick(driver.findElement(By.xpath("//button[@ondblclick='doubleClickMe()']"))).perform();
        Assert.assertEquals(driver.findElement(By.id("demo")).getText(), "Hello Automation Guys!");
    }

    @Test
    public void TC_07_Context_Click() {
        driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");
        actions.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
        Assert.assertTrue(driver.findElement(By.cssSelector("ul.context-menu-list")).isDisplayed());
        actions.moveToElement(driver.findElement(By.cssSelector(" li.context-menu-icon-quit"))).perform();

        Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-hover')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-visible')]")).isDisplayed());
//      Click chọn Quit => Hiển thị Aler => OK alert => verify context menu không còn hiển thị
        actions.click(driver.findElement(By.cssSelector(" li.context-menu-icon-quit"))).perform();
        alert = driver.switchTo().alert();
        alert.accept();
        Assert.assertFalse(driver.findElement(By.cssSelector("ul.context-menu-list")).isDisplayed());

    }

    @Test
    public void TC_08_Drag_And_Drop_HTML4() {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        WebElement source = driver.findElement(By.id("draggable"));
        WebElement targer = driver.findElement(By.id("droptarget"));
        actions.dragAndDrop(source,targer).perform();
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='You did great!']")).isDisplayed());
        String background = driver.findElement(By.id("droptarget")).getCssValue("background-color");
        String color = Color.fromString(background).asHex();
        Assert.assertEquals(color, "#03a9f4");
    }

    @Test
    public void TC_09_Drag_And_Drop_HTML5() {

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
