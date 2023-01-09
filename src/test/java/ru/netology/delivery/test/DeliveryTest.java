package ru.netology.delivery.test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.delivery.data.DataGenerator.generateDate;

public class DeliveryTest {
    String name = DataGenerator.generateName();
    String phone = DataGenerator.generatePhone();
    String city = DataGenerator.generateCity();

    @Test
    void testFormOfDeliveryCard() {
        open("http://localhost:9999");
        $x("//input[@placeholder=\"Город\"]").setValue(city);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@placeholder=\"Дата встречи\"]").sendKeys((CharSequence) generateDate(5));
        $("[name=\"name\"]").setValue(name);
        $("[name=\"phone\"]").setValue(phone);
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id='success-notification'] .notification__content").shouldHave(exactText("Встреча успешно запланирована на " + DataGenerator.generateDate(5)));
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@placeholder=\"Дата встречи\"]").sendKeys((CharSequence) DataGenerator.generateDate(8));
        $(".button").click();
        $("[class='button button_view_extra button_size_s button_theme_alfa-on-white']").click();
        $("[data-test-id='success-notification'] .notification__content").shouldHave(exactText("Встреча успешно запланирована на " + DataGenerator.generateDate(8)));

    }

}



