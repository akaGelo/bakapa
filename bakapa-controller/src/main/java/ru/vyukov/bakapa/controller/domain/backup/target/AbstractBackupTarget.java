package ru.vyukov.bakapa.controller.domain.backup.target;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.vyukov.bakapa.controller.domain.View.Full;
import ru.vyukov.bakapa.controller.domain.View.Summary;
import ru.vyukov.bakapa.controller.domain.agent.Agent;
import ru.vyukov.bakapa.controller.domain.backup.target.database.DatabaseBackupTarget;
import ru.vyukov.bakapa.domain.BackupTargetType;
import ru.vyukov.bakapa.validators.CronExpression;

import javax.validation.constraints.NotNull;


/**
 * Abstract backup target
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = {"backupTargetId", "targetType"})
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
    @JsonView(Full.class)
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

    public static AbstractBackupTarget demo(Agent agent) {
        return demo(agent,1);
    }

    public static AbstractBackupTarget demo(Agent agent,int id) {
        return FilesystemBackupTarget.demo(agent, id);
    }

    public static AbstractBackupTarget demo() {
        return FilesystemBackupTarget.demo(Agent.demo("testAgent"), 1);
    }

    public static AbstractBackupTarget demo(int i) {
        return FilesystemBackupTarget.demo(Agent.demo("testAgent"), i);
    }
}