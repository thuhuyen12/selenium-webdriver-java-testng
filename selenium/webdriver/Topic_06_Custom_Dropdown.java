package webdriver;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.time.Duration;
import java.util.List;

public class Topic_06_Custom_Dropdown {
    WebDriver driver;
    WebDriverWait explicitWait;
    String expectedItem = "16";

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_Jquery_Cach1() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
//        1. Click vào dropdown
        driver.findElement(By.id("number-button")).click();
//        2. Chờ cho tất cả item load ra thành công
//        Lấy Locator đại diện cho tất cả các items và lấy đến thẻ có chứa text trên UI
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu div")));
//        3. Đưa hết item vào 1 list
        List<WebElement> allItems = driver.findElements(By.cssSelector("ul#number-menu div"));
//        4. Tìm item xem có cái đang cần hay không
        for (WebElement item : allItems) {
            String itemText = item.getText().trim();
            System.out.println(itemText);
            if (itemText.equals(expectedItem)){
                item.click();
                System.out.println("Click vào item " + itemText);

                break;
            }
        }

    }

    @Test
    public void TC_02_Jquery_Cach2() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        selectItemInDropdown("span#salutation-button",
                "ul#salutation-menu div", "Dr." );

        Assert.assertEquals(driver.findElement
                (By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(), "Dr.");
    }

    @Test
    public void TC_03_ReactJS() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectItemInDropdown("div.ui.fluid.selection.dropdown","div.visible.menu.transition span", "Matt" );
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

    public void selectItemInDropdown(String cssDropdown, String cssAllItems, String expectedItem){
//        1. Click vào dropdown
        driver.findElement(By.cssSelector(cssDropdown)).click();
//        2. Chờ cho tất cả item load ra thành công
//        Lấy Locator đại diện cho tất cả các items và lấy đến thẻ có chứa text trên UI
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(cssAllItems)));
//        3. Đưa hết item vào 1 list
        List<WebElement> allItems = driver.findElements(By.cssSelector(cssAllItems));
//        4. Tìm item xem có cái đang cần hay không
        for (WebElement item : allItems) {
            String itemText = item.getText().trim(); //trim: cắt bỏ khoảng trắng thừa
            System.out.println(itemText);
            if (itemText.equals(expectedItem)){
                item.click();
                System.out.println("Click vào item " + itemText);

                break;
            }
        }

    }


}
