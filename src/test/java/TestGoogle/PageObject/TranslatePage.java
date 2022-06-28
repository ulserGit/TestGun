package TestGoogle.PageObject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Переводчик Google
 */

public class TranslatePage {

    // Поле текста для перевода
    private final SelenideElement translateTextAreaLocator = $x("//textarea[@aria-label = 'Исходный текст']");

    // Результат перевода
    private final SelenideElement resultTextLocator = $x("//span[@jsname = 'W297wb']");

    // Кнопка переключения направления перевода
    private final SelenideElement translateSwitchButtonLocator = $x("//button[@jsname = 'dnDxad']");

    // Кнопка языка перевода "Английский"
    private final SelenideElement engSelectButtonLocator = $x("//button[@id = 'i12']");


    public TranslatePage (String url) {
        Selenide.open(url);
    }

    @Step(value = "Тайтл")
    public void title(String title) {
        assertEquals(title, Selenide.title());
    }

    @Step(value = "Вставка текста для перевода")
    public void setTranslateText(String translateText) {
        translateTextAreaLocator.setValue(translateText);
    }

    @Step(value = "Результат перевода")
    public void translateResultText(String resultTranslateText) {
        resultTextLocator.shouldHave((text(resultTranslateText)));
    }

    @Step(value = "Язык перевода Английский")
    public void engTranslation() {
        engSelectButtonLocator.click();
    }

    @Step(value = "Переключение направления перевода")
    public void translateSwitch() {
        translateSwitchButtonLocator.click();
    }

    // foo...
}
