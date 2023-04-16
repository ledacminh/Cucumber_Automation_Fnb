package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class StepDefinitions {
    WebDriver driver;

    @Given("Open Fnb system")
    public void openFnbSystem() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://fnb.kiotviet.vn/autotest1111");
    }

    @When("Input to name store")
    public void inputToNameStore() {
        driver.findElement(By.id("Retailer")).clear();
        driver.findElement(By.id("Retailer")).sendKeys("autotest1111");
    }

    @When("Input to user name")
    public void inputToUserName() {
        driver.findElement(By.id("UserName")).clear();
        driver.findElement(By.id("UserName")).sendKeys("0966152432");
    }

    @When("Input to password")
    public void inputToPassword() {
        driver.findElement(By.id("Password")).clear();
        driver.findElement(By.id("Password")).sendKeys("HaNoi@2023");
    }

    @When("Click to button manage")
    public void clickToButtonManage() {
        driver.findElement(By.xpath("//i[@type='submit']")).click();
    }

    @When("Close application")
    public void closeApplication() {
        driver.quit();
    }

    @When("Input to name store with {string}")
    public void inputToNameStoreWith(String nameStore) {
        driver.findElement(By.id("Retailer")).clear();
        driver.findElement(By.id("Retailer")).sendKeys(nameStore);
    }

    @When("Input to user name with {string}")
    public void inputToUserNameWith(String userName) {
        driver.findElement(By.id("UserName")).clear();
        driver.findElement(By.id("UserName")).sendKeys(userName);
    }

    @When("Input to password with {string}")
    public void inputToPasswordWith(String password) {
        driver.findElement(By.id("Password")).clear();
        driver.findElement(By.id("Password")).sendKeys(password);
    }

    @And("Input to user name with {} and password with {}")
    public void inputToUserNameWithAndPasswordWith(String userName, String password) {
        driver.findElement(By.id("UserName")).clear();
        driver.findElement(By.id("UserName")).sendKeys(userName);
        driver.findElement(By.id("Password")).clear();
        driver.findElement(By.id("Password")).sendKeys(password);
    }

    @When("Input to user name with user name and password")
    public void inputToUserNameWithUserNameAndPassword(DataTable dataTable) {
        List<Map<String, String>> account =dataTable.asMaps(String.class, String.class);
        driver.findElement(By.id("UserName")).clear();
        driver.findElement(By.id("UserName")).sendKeys(account.get(0).get("Username"));
        driver.findElement(By.id("Password")).clear();
        driver.findElement(By.id("Password")).sendKeys(account.get(0).get("Password"));
    }
}
