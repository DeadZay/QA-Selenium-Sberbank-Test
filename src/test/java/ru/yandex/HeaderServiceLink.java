package ru.yandex;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import qa.selenide.interfaces.*;

import static com.codeborne.selenide.Selenide.$;

public enum HeaderServiceLink implements Clickable, SelenideElementGetter, XpathGetter, XpathFormatGetter, AssertURL {
	video,
	images,
	news,
	maps,
	market,
	translate,
	music,
	tv,
	autoru;

	private static final String[] TITLE = {
			"Видео",
			"Картинки",
			"Новости",
			"Карты",
			"Маркет",
			"Переводчик",
			"Музыка",
			"Программа",
			"Авто.ру"
	};

	private static final String[] URL = {
			"https://yandex.ru/video/",
			"https://yandex.ru/images/",
			"https://yandex.ru/news/",
			"https://yandex.ru/maps/",
			"https://market.yandex.ru/",
			"https://translate.yandex.ru/",
			"https://music.yandex.ru/",
			"https://tv.yandex.ru/",
			"https://auto.ru/"
	};

	private final String XPATH_FORMAT = "//a[@data-id = '%s']";
	private final String XPATH = String.format(XPATH_FORMAT, this.name());
	private final SelenideElement LINK = $(By.xpath(XPATH));

	@Override
	public String toString() {
		return TITLE[this.ordinal()];
	}

	@Override
	public void	click() {
		LINK.shouldBe(Condition.and(
				"SelenideElement check",
				Condition.exist, Condition.enabled, Condition.visible))
				.click();
	}

	public String getXpathFormat() {
		return XPATH_FORMAT;
	}

	public String getXpath() {
		return XPATH;
	}

	public SelenideElement getSelenideElement() {
		return LINK;
	}

	@Override
	public boolean assertURL(@NotNull String url) {
		return URL[this.ordinal()].contains(url);
	}
}
