import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class SelenideTest {

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        WebDriverManager.chromedriver().setup();
    }

    @AfterEach
    void afterEach() {
        closeWebDriver();
    }

    @Test
    void selenideTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com/");
        $(".search-input-container").click();
        $("#query-builder-test").sendKeys("eroshenkoam/allure-example");
        $("#query-builder-test").submit();

        //$(By.linkText("eroshenkoam/allure-example")).click();
        $("[href*='/eroshenkoam/allure-example']").click();
        $("#issues-tab").click();
        $("#issue_89_link").should(Condition.exist);
    }
}
