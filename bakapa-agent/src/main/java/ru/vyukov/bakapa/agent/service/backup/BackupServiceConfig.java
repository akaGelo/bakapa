package ru.vyukov.bakapa.agent.service.backup;

import lombok.Getter;
import lombok.Setter;
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
    private String backupPartitionSize;

    private volatile long sizeInBytes;

    public long getBackupPartitionSizeInBytes() {
        return sizeInBytes;
    }

    public void setBackupPartitionSize(String backupPartitionSize) {
        try {
            this.sizeInBytes = parse(backupPartitionSize);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[" + backupPartitionSize + "] not valid format");
        }
        this.backupPartitionSize = backupPartitionSize;
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