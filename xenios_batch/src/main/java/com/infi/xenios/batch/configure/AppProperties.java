package com.infi.xenios.batch.configure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = AppProperties.PREFIX)
@Configuration
public class AppProperties {
	public static final String PREFIX = "application";

	public static String fileUploadPath;

	public static String getFileUploadPath() {
		return fileUploadPath;
	}

	public static void setFileUploadPath(String fileUploadPath) {
		AppProperties.fileUploadPath = fileUploadPath;
	}
}
