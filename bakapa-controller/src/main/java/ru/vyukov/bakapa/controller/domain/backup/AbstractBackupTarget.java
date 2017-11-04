package ru.vyukov.bakapa.controller.domain.backup;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import ru.vyukov.bakapa.domain.BackupTargetType;
import ru.vyukov.bakapa.validators.CronExpression;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.vyukov.bakapa.controller.domain.View.Summary;
import ru.vyukov.bakapa.controller.domain.agent.Agent;
import ru.vyukov.bakapa.controller.domain.backup.database.DatabaseBackupTarget;

import javax.validation.constraints.NotNull;


/**
 * Abstract backup target
 */
@Getter
@AllArgsConstructor
@Document(collection = "backupTargets")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "targetType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = FilesystemBackupTarget.class, name = "FILESYSTEM"),
        //
        @JsonSubTypes.Type(value = DatabaseBackupTarget.class, name = "MYSQL"),
        @JsonSubTypes.Type(value = DatabaseBackupTarget.class, name = "MONGODB"),
        @JsonSubTypes.Type(value = DatabaseBackupTarget.class, name = "POSTGRESQL")
})
abstract public class AbstractBackupTarget {

    @Id
    @JsonView(Summary.class)
    protected String backupTargetId;

    @NotNull
    @Indexed
    @DBRef
    protected Agent agent;

    @NonNull
    @NotNull
    @JsonView(Summary.class)
    protected BackupTargetType targetType;


    @NonNull
    @NotNull
    @JsonView(Summary.class)
    @CronExpression
    protected String trigger;


    /**
     * set agent
     *
     * @param agent
     * @return new object
     */
    public abstract AbstractBackupTarget agent(Agent agent);
}