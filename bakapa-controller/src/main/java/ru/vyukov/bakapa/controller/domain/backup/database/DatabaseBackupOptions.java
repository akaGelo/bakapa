package ru.vyukov.bakapa.controller.domain.backup.database;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;
import ru.vyukov.bakapa.controller.domain.View;
import ru.vyukov.bakapa.controller.domain.View.Summary;

import javax.validation.constraints.NotNull;
import java.util.List;

@Value
@Builder
public class DatabaseBackupOptions {

    @NonNull
    @NotNull
    @Singular
    @JsonView(Summary.class)
    private List<String> excludeTables;

    public static DatabaseBackupOptionsBuilder backupOptions() {
        //lombok static import builder not compiled in javac
        return builder();
    }
}
