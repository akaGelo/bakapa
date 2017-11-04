package ru.vyukov.bakapa.dto.backups.database;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseBackupOptionsDTO {

    @NonNull
    @NotNull
    @Singular
    private List<String> excludeTables;

    public static DatabaseBackupOptionsDTOBuilder backupOptions() {
        //lombok static import builder not compiled in javac
        return builder();
    }
}
