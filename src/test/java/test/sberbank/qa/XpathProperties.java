package test.sberbank.qa;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class XpathProperties {
	protected static final String		XPATH_PROPERTIES_FILE = "src/test/resources/xpath.properties";
	protected static Properties			properties;

	static {
		try (FileInputStream fileInputStream = new FileInputStream(XPATH_PROPERTIES_FILE)) {
			properties = new Properties();
			properties.load(fileInputStream);
		} catch (IOException ioException)
		{
			ioException.printStackTrace();
		}
	}

	public static String getProperty(String key)
	{
		return (properties.getProperty(key));
	}
}
