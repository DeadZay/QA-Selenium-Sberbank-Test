package test.sberbank.qa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class WebPage {
	protected WebDriver webDriver;

	public WebPage(WebDriver webDriver)
	{
		PageFactory.initElements(webDriver, this);
		this.webDriver = webDriver;
	}
}
