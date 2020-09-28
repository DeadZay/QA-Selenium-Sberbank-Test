package ru.yandex.market;

import org.junit.jupiter.api.Test;
import ru.yandex.market.computers.YandexMarketComputersPage;

import static com.codeborne.selenide.Selenide.*;
import static ru.yandex.market.headerstab.TabLink.*;

/**
 * A class that contains methods to control web page
 */
public class YandexMarketPage {

	private static final String URL = "market.yandex.ru";

	public static YandexMarketPage openYandexMarketPage() {
		open(URL);
		return new YandexMarketPage();
	}

	@Test
	public YandexMarketComputersPage clickOnComputersCatalogLink() {
		computers.click();
		return new YandexMarketComputersPage();
	}
}
