package ru.vyukov.bakapa.controller.domain.backup.target.database;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.hibernate.validator.constraints.NotEmpty;
import ru.vyukov.bakapa.controller.domain.View;
import ru.vyukov.bakapa.controller.domain.View.Summary;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Value
@Builder
public class DatabaseLocation {

    @NonNull
    @NotNull
    @NotEmpty
    @JsonView(Summary.class)
    private String host;

    @NonNull
    @NotNull
    @Min(0)
    @JsonView(Summary.class)
    private Integer port;


    @NonNull
    @NotNull
    @NotEmpty
    @JsonView(Summary.class)
    private String database;

    public static DatabaseLocationBuilder databaseLocation() {
        //lombok static import builder not compiled in javac
        return builder();
    }


}
