package ru.yandex.market.computers;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.yandex.market.computers.navigationtree.NavTreeLink;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class YandexMarketComputersPage {

	@ParameterizedTest
	@EnumSource(NavTreeLink.class)
	void clickOnNavTreeLink(NavTreeLink link) {
		Configuration.startMaximized = true;
		open("https://market.yandex.ru/catalog--kompiuternaia-tekhnika/54425");
		link.click();
		assertFalse(link.assertURL(url()), "Current url isn't contain current category url");
	}
}
