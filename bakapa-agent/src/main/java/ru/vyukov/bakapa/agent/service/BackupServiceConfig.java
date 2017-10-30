package ru.vyukov.bakapa.agent.service;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Component
@ConfigurationProperties("backup")
public class BackupServiceConfig {

	private final static long KB_FACTOR = 1024;
	private final static long MB_FACTOR = 1024 * KB_FACTOR;
	private final static long GB_FACTOR = 1024 * MB_FACTOR;

	@NotNull
	@Pattern(regexp = "[0-9]+(MB|GB)$", message = "format 2MB or 1GB")
	@Getter
	private String backupPartionSize;

	private volatile long sizeInBytes;

	public long getBackupPartionSizeInBytes() {
		return sizeInBytes;
	}

	public void setBackupPartionSize(String backupPartionSize) {
		try {
			this.sizeInBytes = parse(backupPartionSize);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("[" + backupPartionSize + "] not valid format");
		}
		this.backupPartionSize = backupPartionSize;
	}

	private static long parse(String size) {
		String factor = StringUtils.substring(size, -2).toUpperCase();

		long ret = Long.parseLong(size.substring(0, size.length() - 2));
		switch (factor) {
		case "GB":
			return ret * GB_FACTOR;
		case "MB":
			return ret * MB_FACTOR;
		default:
			throw new IllegalArgumentException("[" + size + "] not valid format");
		}
	}
};