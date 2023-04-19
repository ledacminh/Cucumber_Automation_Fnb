package actions;

import commons.BasePage;
import interfaces.LoginUI;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

public class LoginStepDefinitions extends BasePage {
    private final WebDriver driver;

    public LoginStepDefinitions() {
        driver = Hooks.getDriver();
    }

    @Before
    public void openBrowser() {
        getBrowser(driver);
    }

    @When("Input to name store" )
    public void inputToNameStore() {
        enterTextToElement(driver, "a", "autotest1111" );
    }

    @When("Input to user name" )
    public void inputToUserName() {
        enterTextToElement(driver, LoginUI.TEN_DANG_NHAP, "0966152432" );
    }

    @When("Input to password" )
    public void inputToPassword() {
        enterTextToElement(driver, LoginUI.MAT_KHAU, "HaNoi@2023" );
    }

    @When("Click to button manage" )
    public void clickToButtonManage() {
        clickToElement(driver, LoginUI.BUTTON_QUAN_LY);
    }

    @When("Close application" )
    public void closeApplication() {
        driver.quit();
    }

    @When("Input to name store with {string}" )
    public void inputToNameStoreWith(String nameStore) {
        enterTextToElement(driver, LoginUI.TEN_GIAN_HANG, nameStore);
    }

    @When("Input to user name with {string}" )
    public void inputToUserNameWith(String userName) {
        enterTextToElement(driver, LoginUI.TEN_GIAN_HANG, userName);
    }

    @When("Input to password with {string}" )
    public void inputToPasswordWith(String password) {
        enterTextToElement(driver, LoginUI.MAT_KHAU, password);
    }

    @And("Input to user name with {} and password with {}" )
    public void inputToUserNameWithAndPasswordWith(String userName, String password) {
        enterTextToElement(driver, LoginUI.TEN_DANG_NHAP, userName);
        enterTextToElement(driver, LoginUI.MAT_KHAU, password);
    }

    @When("Input to user name with user name and password" )
    public void inputToUserNameWithUserNameAndPassword(DataTable dataTable) {
        List<Map<String, String>> account = dataTable.asMaps(String.class, String.class);
        enterTextToElement(driver, LoginUI.TEN_DANG_NHAP, account.get(0).get("Username" ));
        enterTextToElement(driver, LoginUI.MAT_KHAU, account.get(0).get("Password" ));
    }
}
