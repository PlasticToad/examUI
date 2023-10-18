package steps;

import com.codeborne.selenide.WebDriverRunner;
import elements.TestProjectPageElements;
import hooks.WebHooks;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.opentest4j.AssertionFailedError;

import java.time.Duration;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class TestProjectPage extends TestProjectPageElements {
    @Step("Проверить версию 2.0")
    @Тогда("Проверка версии")
    public static void checkVersion() {
        try {
            Assertions.assertEquals("Version 2.0", version.getText());
            makeScreenShot();
        } catch (AssertionFailedError e) {
            makeScreenShot();
            Assertions.fail("Колличество задач меньше 0");
        }
    }
    @Step("Проверить статус Сделать")
    @Тогда("Проверка статуса СДЕЛАТЬ")
    public static void checkStatusToDo() {
        try {
            Assertions.assertEquals("СДЕЛАТЬ", status.getText());
            makeScreenShot();
        } catch (AssertionFailedError e) {
            makeScreenShot();
            Assertions.fail("Колличество задач меньше 0");
        }
    }
    @Step("Проверить статус Готово")
    @Тогда("Проверка статуса ГОТОВО")
    public static void checkStatusDone() {
        try {
            Assertions.assertEquals("ГОТОВО", status.getText());
            makeScreenShot();
        } catch (AssertionFailedError e) {
            makeScreenShot();
            Assertions.fail("Колличество задач меньше 0");
        }
    }
    @Step("Проверить количество задач")
    @Дано("Проверка количества задач")
    public static void checkQuantity() {
        String value = quantity.getText();
        int val = Integer.parseInt(value.substring(5).trim());
        try {
            Assertions.assertTrue(val > 0);
            makeScreenShot();
        } catch (AssertionFailedError e) {
            makeScreenShot();
            Assertions.fail("Колличество задач меньше 0");
        }
    }
    @Step("Перейти на страницу задачи TestSelenium")
    @Дано("Переход на страницу задачи TestSelenium")
    public static void goToSelenTask() {
        quickSearch.sendKeys("TestSelenium");
        selenTask.click();
        makeScreenShot();
    }
    @Step("Создать задачу")
    @Дано("Создать задачу")
    public static void createBug() {
        create.click();
        makeScreenShot();
    }
    @Step("Заполнить поля задачи")
    @Тогда("Заполнить поля задачи")
    public static void fillFields() {
        taskType.click();
        taskType.sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE,"Ошибка");
        subject.sendKeys("Серьезная ошибка в важном модуле");
        description.sendKeys("Что то нажал и все исчезло!");
        versionFix.click();
        priorityDrop.sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE,"Highest");
        tags.sendKeys("bugs");
        environment.sendKeys("Везде ничего не работает");
        versionRelate.click();
        relatedTasks.click();
        task.sendKeys("TEST-28235");
        assign.click();
        createSaveBtn.click();
        makeScreenShot();
    }
    @Step("Перейти к созданой задаче")
    @Когда("Перейти к созданой задаче")
    public static void openCreatedBug() {
        open(WebHooks.prop.getProperty("JIRA_MAIN"));
        lastTask.click();
        makeScreenShot();
    }
    @Step("Исполнить задачу")
    @Тогда("Исполнить задачу")
    public static void closeBug() {
        workBtn.click();
        bsDrop.click();
        doneBtn.shouldBe(visible, Duration.ofSeconds(5)).click();
        sleep(5000);
        makeScreenShot();
    }
    @Attachment("image/png")
    public static byte[] makeScreenShot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
