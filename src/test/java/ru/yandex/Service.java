package ru.yandex;

import org.openqa.selenium.*;

public enum Service {
	video,
	images,
	news,
	maps,
	market,
	translate,
	music,
	tv,
	autoru,
	more;

	private static final String[] SERVICE_TITLE = {
			"Видео",
			"Картинки",
			"Новости",
			"Карты",
			"Маркет",
			"Переводчик",
			"Музыка",
			"Программа",
			"Авто.ру",
			"ещё"
	};

	private final WebElement serviceLink = WebPage.webDriver.findElement(By.xpath(String.format(
			Properties.getProperty("xpathServiceFormat"),
			this.toString())));

	@Override
	public String toString() {
		return SERVICE_TITLE[this.ordinal()];
	}

	public WebElement getServiceLink() {
		return serviceLink;
	}
}
