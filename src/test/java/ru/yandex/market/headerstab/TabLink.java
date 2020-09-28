package ru.yandex.market.headerstab;

import com.codeborne.selenide.SelenideElement;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import qa.selenide.interfaces.*;

import static com.codeborne.selenide.Selenide.$;

public enum TabLink implements Clickable, SelenideElementGetter, XpathFormatGetter, XpathGetter, AssertURL {
	electronics,
	appliances,
	computers,
	repairs,
	house,
	countryHouse,
	children,
	beauty,
	health,
	food;

	private final String[] TITLE = {
			"Электроника",
			"Бытовая техника",
			"Компьютеры",
			"Ремонт",
			"Дом",
			"Дача",
			"Детям",
			"Красота",
			"Здоровье",
			"Продукты"
	};

	private static final String[] URL = {
			"https://market.yandex.ru/catalog--elektronika/",
			"https://market.yandex.ru/catalog--bytovaia-tekhnika/",
			"https://market.yandex.ru/catalog--kompiuternaia-tekhnika/",
			"https://market.yandex.ru/catalog--stroitelstvo-i-remont/",
			"https://market.yandex.ru/catalog--tovary-dlia-doma/",
			"https://market.yandex.ru/catalog--dacha-sad-i-ogorod/",
			"https://market.yandex.ru/catalog--detskie-tovary/",
			"https://market.yandex.ru/catalog--tovary-dlia-krasoty/",
			"https://market.yandex.ru/catalog--tovary-dlia-zdorovia/",
			"https://market.yandex.ru/catalog--produkty-napitki/"
	};

	private final String XPATH_FORMAT = "//div[@data-zone-name = 'category-link']//a[./span = '%s']";
	private final String XPATH = String.format(XPATH_FORMAT, TITLE[this.ordinal()]);
	private final SelenideElement LINK = $(By.xpath(XPATH));

	public String getXpathFormat() {
		return XPATH_FORMAT;
	}

	public String getXpath() {
		return XPATH;
	}

	@Override
	public String toString() {
		return TITLE[this.ordinal()];
	}

	@Override
	public void click() {
		LINK.click();
	}

	public SelenideElement getSelenideElement() {
		return LINK;
	}

	@Override
	public boolean assertURL(@NotNull String url) {
		return URL[this.ordinal()].contains(url);
	}
}
