package test.sberbank.qa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class WebPage {
	protected static WebDriver webDriver;

	public WebPage(WebDriver webDriver)
	{
		PageFactory.initElements(webDriver, this);
		WebPage.webDriver = webDriver;
	}
}
