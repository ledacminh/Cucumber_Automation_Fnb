package actions;

import commons.GlobalConstants;
import commons.Log;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.UnreachableBrowserException;

import java.time.Duration;


public class Hooks {
    // Run for many thread
    private static WebDriver driver;

    public static void openAndQuitBrowser() {
        // Run by Maven command line
        String browser = System.getProperty("BROWSER" );
        System.out.println("Browser name run by command line = " + browser);

        // Check driver đã được khởi tạo hay chưa?
        if (driver == null) {
            // Happy path case
            try {
                // Kiem tra BROWSER = null -> gan = chrome/ firefox (browser default for project)
                if (browser == null) {
                    // Get browser name from Environment Variable in OS
                    browser = System.getenv("BROWSER" );
                    if (browser == null) {
                        // Set default browser
                        browser = "firefox";
                    }
                }

                if (browser.equals("hchrome" )) {
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("headless" );
                    chromeOptions.addArguments("window-size=1920x1080" );
                    driver = new ChromeDriver(chromeOptions);
                } else {
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--remote-allow-origins=*" );
                    driver = new ChromeDriver(chromeOptions);
                }
                // Browser crash/ stop
            } catch (UnreachableBrowserException e) {
                driver = new ChromeDriver();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*" );
                driver = new ChromeDriver(chromeOptions);
                // Driver crash
            } catch (WebDriverException e) {
                driver = new ChromeDriver();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*" );
                driver = new ChromeDriver(chromeOptions);
            } finally {
                Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
            }
            driver.get(GlobalConstants.PROJECT_URL);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            driver.manage().window().maximize();
            Log.info("------------- Started the browser -------------" );
        }
    }

    public synchronized static WebDriver getDriver() {
        openAndQuitBrowser();
        return driver;
    }

    public static void close() {
        try {
            if (driver != null) {
                getDriver().quit();
                Log.info("------------- Closed the browser -------------" );
            }
        } catch (UnreachableBrowserException e) {
            System.out.println("Can not close the browser" );
        }
    }

    private static class BrowserCleanup implements Runnable {
        @Override
        public void run() {
            close();
        }
    }
}
