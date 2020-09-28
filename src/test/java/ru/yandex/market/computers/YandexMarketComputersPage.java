package ru.yandex.market.computers;

import io.qameta.allure.Step;
import ru.yandex.market.computers.notebooks.YandexMarketNotebooksPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.yandex.market.computers.navigationtree.NavTreeSubLink.*;

public class YandexMarketComputersPage {
	final private static String URL = "https://market.yandex.ru/catalog--kompiuternaia-tekhnika/";

	public static YandexMarketComputersPage openYandexMarketCatalogComputersPage() {
		open(URL);
		return new YandexMarketComputersPage();
	}

	@Step
	public YandexMarketNotebooksPage clickOnNotebooksCatalogLink() {
		notebook.click();
		return new YandexMarketNotebooksPage();
	}
}
