package ru.vyukov.bakapa.dto.backups.target.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.NotEmpty;
import ru.vyukov.bakapa.domain.BackupTargetType;
import ru.vyukov.bakapa.dto.backups.target.SummaryBackupTargetDTO;

import javax.validation.constraints.NotNull;
import java.beans.ConstructorProperties;

@Data
@NoArgsConstructor
public class FilesystemBackupTargetDTO extends AbstractBackupTargetDTO implements SummaryBackupTargetDTO {

    @NonNull
    @NotNull
    @NotEmpty
    private String path;


    @Builder
    @JsonCreator
    @ConstructorProperties({"backupTargetId", "trigger", "path"})
    public FilesystemBackupTargetDTO(String backupTargetId, String trigger, String path) {
        super(backupTargetId, BackupTargetType.FILESYSTEM, trigger);
        this.path = path;
    }


    public static FilesystemBackupTargetDTO defaultExample() {
        return new FilesystemBackupTargetDTO(null, EVERY_DAY_CRON_TRIGGER, "/etc/");
    }

    @Override
    public String getNameReadable() {
        return path;
    }
}
