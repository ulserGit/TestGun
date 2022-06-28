package TestGoogle.PageObject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Главная страница Google
 */

public class MainPage {

    // Логотип
    private final SelenideElement logoImgLocator = $x("//img[@data-frt = '0']");
    // Форма поиска
    private final SelenideElement inputFormLocator = $x("//input[@name = 'q']");
    // Кнопка "Мне повезет!"
    private final SelenideElement luckyButtonLocator = $x("//input[@name = 'btnI']");
    // Кнопка "Поиск в Google"
    private final SelenideElement searchButtonLocator = $x("//input[@name = 'btnK']");
    // Текст правой колонки после поиска
    private final SelenideElement resultTextLocator = $x("//H2[@data-attrid = 'title']");


    public MainPage (String url) {
        Selenide.open(url);
    }


    @Step(value = "Тайтл")
    public void title(String title) {
        assertEquals(title, Selenide.title());
    }

    @Step(value = "Кнопка \"Мне повезет!\"")
    public void checkLuckyButton() {
        assertTrue(luckyButtonLocator.exists());
        assertTrue(luckyButtonLocator.isEnabled());
    }

    @Step(value = "Кнопка \"Поиск в Google\"")
    public void checkSearchButton() {
        assertTrue(searchButtonLocator.exists());
        assertTrue(searchButtonLocator.isEnabled());
    }

    @Step(value = "Логотип")
    public void checkLogoImg() {
        if (!logoImgLocator.isImage() || !logoImgLocator.isDisplayed()) {
            throw new IllegalStateException("Отсутствует логотип!");
        }
    }

    @Step(value = "Поисковый запрос")
    public void search(String searchText) {
        enterSearchText(searchText);
        sendSearchText();
        resultSearchText(searchText);
    }

    @Step(value = "Вставить текст в поле запроса")
    public void enterSearchText(String searchText) {
        inputFormLocator.setValue(searchText);
    }

    @Step(value = "Отправить поисковый запрос")
    public void sendSearchText() {
        inputFormLocator.pressEnter();
    }

    @Step(value = "Результат поискового запроса")
    public void resultSearchText(String valid) {
        resultTextLocator.shouldHave((text(valid)));
    }

    // foo...
}
