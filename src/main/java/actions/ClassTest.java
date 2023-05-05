package actions;

import commons.BasePage;
import interfaces.LoginUI;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class ClassTest extends BasePage {
    private final WebDriver driver;

    public ClassTest() {
        driver = Hooks.getDriver();
    }

//    @When("Input to user name")
//    public void inputToUserName() {
//        enterTextToElement(driver, LoginUI.TEN_DANG_NHAP, "0966152432");
//        System.out.println("pppppppppppppppp" + ListGlobalScenarioVariable.getValue(LoginStepDefinitions.TestData.USERID.toString()));
//
//    }

}
