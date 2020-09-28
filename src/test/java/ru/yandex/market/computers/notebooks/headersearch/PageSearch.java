package ru.yandex.market.computers.notebooks.headersearch;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.SelenideWait;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class PageSearch {
	private static final SelenideElement searchInput = $(By.id("header-search"));
	private static final SelenideElement searchButton = $(By.xpath(
			"//div[@data-apiary-widget-name = '@MarketNode/HeaderSearch']/form//button[@type = 'submit' and ./div = 'Найти']"));

	public static void request(@NotNull String text) {
		searchInput.shouldBe(Condition.visible).clear();
		searchInput.sendKeys(text);
		searchButton.click();
		SelenideWait wait = new SelenideWait(searchButton.getWrappedDriver(), 5000, 2500);
		wait.withTimeout(Duration.ofSeconds(5));
	}
}
