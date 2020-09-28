package ru.yandex.market;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.yandex.market.headerstab.TabLink;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class YandexMarketPage {

	@ParameterizedTest
	@EnumSource(TabLink.class)
	private void clickTabLink(TabLink link) {
		Configuration.startMaximized = true;
		open("https://market.yandex.ru");
		link.click();
		assertFalse(link.assertURL(url()), "Current url isn't contain current category url");
	}
}
