package ru.yandex.market.computers;

import io.qameta.allure.Step;
import ru.yandex.market.computers.notebooks.YandexMarketNotebooksPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.yandex.market.computers.navigationtree.NavTreePcSubLink.*;

/**
 * A class to control page actions
 */
public class YandexMarketComputersPage {
	final private static String URL = "https://market.yandex.ru/catalog--kompiuternaia-tekhnika/";

	/**
	 * A function open yandex market catalog of pc
	 * and return associated with that page class
	 * @return associated with that page class
	 */
	public static YandexMarketComputersPage openYandexMarketCatalogComputersPage() {
		open(URL);
		return new YandexMarketComputersPage();
	}

	/**
	 * A function click on subcategory "Ноутбуки" of pc category at left side of page
	 * and return associated with that page class
	 * @return associated with that page class
	 */
	@Step
	public YandexMarketNotebooksPage clickOnNotebooksCatalogLink() {
		notebook.click();
		return new YandexMarketNotebooksPage();
	}
}
