import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class SberTest {
	@Test
	void userSearchNotebookAndCheckExist() {
		Configuration.startMaximized = true;
		open("https://yandex.ru");
		$(By.xpath("//a[@data-id = 'market']")).click();
		switchTo().window(1)
				.findElement(By.xpath("//div[@data-zone-name = 'category-link']//a[./span = 'Компьютеры']"))
				.click();
		$(By.xpath("//a[.='Ноутбуки']")).click();
		$(By.xpath("//fieldset[./legend = 'Цена, ₽']//*[./label[@aria-label = 'Цена от']]//input[@type = 'text']"))
				.setValue("10000");
		$(By.xpath("//fieldset[./legend = 'Цена, ₽']//*[./label[@aria-label = 'Цена до']]//input[@type = 'text']"))
				.setValue("30000");
		$(By.xpath("//fieldset[./legend = 'Производитель']//label[//input[@type = 'checkbox' and @name = 'Производитель HP']]//div[./span = 'HP']"))
				.click();
		$(By.xpath("//fieldset[./legend = 'Производитель']//label[//input[@type = 'checkbox' and @name = 'Производитель Lenovo']]//div[./span = 'Lenovo']"))
				.click();
		$(By.xpath("//div[./button = 'Показать ещё']/div[./button[@aria-expanded = 'false']]/button")).click();
		$(By.xpath("//div[./button = 'Показать ещё']//div[//button[@aria-expanded]]//div[@aria-hidden = 'false']//button[.='Показывать по 12']")).click();
		$$(By.xpath("//article")).shouldHaveSize(12);
		final String firstArticleTitle = $(By.xpath("//*[@data-zone-name = 'title']/a")).getAttribute("title");
		$("#header-search").sendKeys(firstArticleTitle);
		$(By.xpath("//form[//input[@id = 'header-search']]//button")).click();
		assert $(By.xpath("//*[@data-zone-name = 'title']/a")).text().equals(firstArticleTitle);
	}
}
