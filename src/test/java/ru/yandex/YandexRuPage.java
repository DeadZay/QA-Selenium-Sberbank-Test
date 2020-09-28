package ru.yandex;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class YandexRuPage {

	@ParameterizedTest
	@EnumSource(HeaderServiceLink.class)
	void clickOnHeaderServiceAndCheckUrl(HeaderServiceLink link) {
		Configuration.startMaximized = true;
		open("https://yandex.ru");
		link.click();
		assertFalse(link.assertURL(url()));
	}

	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {"market", "Авто.ру", "tv", "Картинки"})
	void getHeaderServiceLinkByNameAndClick(String name) {
		final HeaderServiceLink link = HeaderServiceLink.valueOf(name);
		Configuration.startMaximized = true;
		open("https://yandex.ru");
		link.click();
		assertFalse(link.assertURL(url()), "Current url isn't contain current category url");
	}
}
