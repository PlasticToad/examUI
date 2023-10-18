package hooks;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.java.Before;
import io.qameta.allure.selenide.AllureSelenide;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static steps.LoginPage.authorization;


public class WebHooks {
    public static Properties prop = new Properties();
    static void propLoad() {
        try(InputStream input = Files.newInputStream(Paths.get("src/test/resources/application.properties"))) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @Before
    public static void allureSubThreadParallel() {
        String listerName = "AllureSelenide";
        if (!(SelenideLogger.hasListener(listerName))) {
            SelenideLogger.addListener(listerName,
                    (new AllureSelenide().screenshots(true).savePageSource(false)));
        }
    }
    @Before
    public void driverSetup() {
        propLoad();
        open(prop.getProperty("JIRA_MAIN"));
        getWebDriver().manage().window().maximize();
        authorization(prop.getProperty("LOGIN"), prop.getProperty("PASS"));
        sleep(5000);
        open(prop.getProperty("JIRA_TEST_SPACE"));
    }
}
