package ru.vyukov.bakapa.controller.domain.backup;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.bakapa.domain.BackupTargetType;
import org.hibernate.validator.constraints.NotEmpty;
import ru.vyukov.bakapa.controller.domain.View;
import ru.vyukov.bakapa.controller.domain.View.Summary;
import ru.vyukov.bakapa.controller.domain.agent.Agent;
import ru.vyukov.bakapa.controller.domain.backup.AbstractBackupTarget;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Target database
 */
@Getter
@AllArgsConstructor
@Builder
public class DatabaseBackupTarget extends AbstractBackupTarget {

    @NonNull
    @NotNull
    @NotEmpty
    @JsonView(Summary.class)
    private String host;

    @NonNull
    @NotNull
    @NotEmpty
    @JsonView(Summary.class)
    private String username;

    @NonNull
    @NotNull
    @Min(0)
    @JsonView(Summary.class)
    private Integer port;

    @NonNull
    @NotNull
    @NotEmpty
    @JsonView(Summary.class)
    private String password;

    @NonNull
    @NotNull
    @Singular
    @JsonView(Summary.class)
    private List<String> excludeTables;



}