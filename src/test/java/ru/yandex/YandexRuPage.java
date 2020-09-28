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

	/**
	 * A function clicks on header service link Маркет
	 * @return associated with clickable link class
	 */
	@Step
	public YandexMarketPage clickOnMarketHeaderLink() {
		market.click();
		return new YandexMarketPage();
	}

	/**
	 * A function open yandex.ru and return associated with that page class
	 * @return associated with that page class
	 */
	public static YandexRuPage openYandexRuPage() {
		open(URL);
		return new YandexRuPage();
	}
}
