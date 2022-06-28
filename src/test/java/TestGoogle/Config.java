package TestGoogle;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import java.util.concurrent.TimeUnit;

public class Config {

    @BeforeAll
    public static void start() {
        System.setProperty("https.protocols", "TLSv1.1");
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.driverManagerEnabled = true;
        Configuration.browserSize = "1600x900";
        Configuration.timeout = 10000;
        Configuration.headless = false;
    }

    @BeforeEach
    public void setUp() {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().screenshots(true).savePageSource(true));
    }

    @AfterAll
    public static void finish() {
        Selenide.closeWebDriver();
    }

    public static void browserWait(long ms) {
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
            System.out.print("Browser is wait "+ms+"ms\n\n");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
