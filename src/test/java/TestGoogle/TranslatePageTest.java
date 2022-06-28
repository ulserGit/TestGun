package TestGoogle;

import TestGoogle.PageObject.TranslatePage;
import com.codeborne.selenide.junit5.TextReportExtension;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TextReportExtension.class)
public class TranslatePageTest extends Config {

    private final static String BASE_URL = "https://translate.google.ru/?hl=ru";
    private final static String TITLE = "Google Переводчик";
    private final static String TRANSLATE_TEXT_RU = "Перевод";
    private final static String TRANSLATE_TEXT_ENG = "Translation";

    @Epic(TITLE)

    @Feature(value = "Чек-лист")

    @DisplayName("Страница перевода")
    @Description("Тестовый сценарий")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void TranslatePage() {
        TranslatePage translatePage = new TranslatePage(BASE_URL);
        translatePage.title(TITLE);
        translatePage.setTranslateText(TRANSLATE_TEXT_RU);
        translatePage.engTranslation();
        translatePage.translateResultText(TRANSLATE_TEXT_ENG);
        translatePage.translateSwitch();
        translatePage.translateResultText(TRANSLATE_TEXT_RU);
        browserWait(3000);
    }
}
