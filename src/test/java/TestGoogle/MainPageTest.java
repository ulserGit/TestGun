package TestGoogle;

import TestGoogle.PageObject.MainPage;
import com.codeborne.selenide.junit5.TextReportExtension;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TextReportExtension.class)
public class MainPageTest extends Config {

    private final static String BASE_URL = "https://www.google.ru/";
    private final static String TITLE = "Google";
    private final static String SEARCH_TEXT = "Mail.ru";

    @Epic(TITLE)

    @Feature(value = "Чек-лист")

    @DisplayName("Главная страница")
    @Description("Тестовый сценарий")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void mainPage() {
        MainPage mainPage = new MainPage(BASE_URL);
        mainPage.title(TITLE);
        mainPage.checkLogoImg();
        mainPage.checkLuckyButton();
        mainPage.checkSearchButton();
        mainPage.search(SEARCH_TEXT);
        browserWait(3000);
    }
}
