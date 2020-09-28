package ru.yandex;

import io.qameta.allure.Step;
import ru.yandex.market.YandexMarketPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.yandex.HeaderServiceLink.*;

/**
 * A class that realize control main page of Yandex (yandex.ru)
 */
public class YandexRuPage {

	private static final String URL = "https://yandex.ru";

	@Step
	public YandexMarketPage clickOnMarketHeaderLink() {
		market.click();
		return new YandexMarketPage();
	}

	public static YandexRuPage openYandexRuPage() {
		open(URL);
		return new YandexRuPage();
	}
}
