package ru.yandex;

import org.openqa.selenium.WebDriver;

public class WebPage {
	protected static WebDriver webDriver;

	public WebPage(WebDriver webDriver) {
		WebPage.webDriver = webDriver;
	}
}
