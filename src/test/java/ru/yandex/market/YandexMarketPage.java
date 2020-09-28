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

	/**
	 * A function open market.yandex.ru and return associated with that page class
	 * @return associated with that page class
	 */
	public static YandexMarketPage openYandexMarketPage() {
		open(URL);
		return new YandexMarketPage();
	}

	/**
	 * A function click on header catalog link "Компьютеры"
	 * and return associated with link address class
	 * @return associated with link address class
	 */
	@Test
	public YandexMarketComputersPage clickOnComputersCatalogLink() {
		computers.click();
		return new YandexMarketComputersPage();
	}
}
