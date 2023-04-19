package actions;

import commons.GlobalConstants;
import commons.Log;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.UnreachableBrowserException;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class Hooks {
    private static WebDriver driver;
    private static final Map<String, String> mapDataProperties = new HashMap<String, String>();

    static {
        try {
            String env = System.getProperty("env") + "";
            String pathFileEnvConfig = "";
            switch (env) {
                case "testing":
                    pathFileEnvConfig = GlobalConstants.TESTING_PROPERTY_PATH;
                    break;
                case "production":
                    pathFileEnvConfig = GlobalConstants.PRODUCTION_PROPERTY_PATH;
                    break;
                case "staging":
                    pathFileEnvConfig = GlobalConstants.STAGING_PROPERTY_PATH;
                    break;
                default:
                    pathFileEnvConfig = GlobalConstants.DEV_PROPERTY_PATH;
                    break;
            }
            Properties properties = new Properties();
            properties.load(new FileInputStream(pathFileEnvConfig));
            Enumeration<?> enumeration = properties.propertyNames();
            while (enumeration.hasMoreElements()) {
                String key = (String) enumeration.nextElement();
                String value = properties.getProperty(key);
                mapDataProperties.put(key, value);
            }
        } catch (IOException e) {
            throw new RuntimeException("[Hooks] " + e.getMessage());
        }
    }

    public static String getString(String key) {
        String value = "";
        try {
            value = mapDataProperties.get(key);
        } catch (Exception e) {
            throw new RuntimeException("[Hooks].[getString]" + e.getMessage());
        }
        return value;
    }

    public static void openAndQuitBrowser() {
        // Run by Maven command line
        String browser = System.getProperty("BROWSER") + "";
        String env = System.getProperty("env");

        // Check driver đã được khởi tạo hay chưa?
        if (driver == null) {
            // Happy path case
            try {
                if (browser.equals("hchrome")) {
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("headless");
                    chromeOptions.addArguments("window-size=1920x1080");
                    driver = new ChromeDriver(chromeOptions);
                } else {
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    driver = new ChromeDriver(chromeOptions);
                }
                // Browser crash/ stop
            } catch (UnreachableBrowserException e) {
                driver = new ChromeDriver();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(chromeOptions);
                // Driver crash
            } catch (WebDriverException e) {
                driver = new ChromeDriver();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(chromeOptions);
            } finally {
                Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
            }
            driver.get(getString("url"));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.SHORT_TIMEOUT));
            driver.manage().window().maximize();
            Log.info("------------- Started the browser -------------");
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
                Log.info("------------- Closed the browser -------------");
            }
        } catch (UnreachableBrowserException e) {
            System.out.println("Can not close the browser");
        }
    }

    private static class BrowserCleanup implements Runnable {
        @Override
        public void run() {
            close();
        }
    }
}
