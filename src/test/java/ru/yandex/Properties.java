package ru.yandex;

import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.IOException;

public class Properties {
	private static final String FILE_PROPERTIES = "src/test/resources/ru.yandex.Service.properties";
	private static final java.util.Properties PROPERTIES = new java.util.Properties();

	static {
		try (FileInputStream fileInputStream = new FileInputStream(FILE_PROPERTIES)) {
			PROPERTIES.load(fileInputStream);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public static String getProperty(@NotNull String key) {
		return PROPERTIES.getProperty(key);
	}
}
