package ru.vyukov.bakapa.controller.domain.backup.database;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.bakapa.domain.BackupTargetType;
import ru.vyukov.bakapa.controller.domain.View.Summary;
import ru.vyukov.bakapa.controller.domain.agent.Agent;
import ru.vyukov.bakapa.controller.domain.backup.AbstractBackupTarget;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.beans.ConstructorProperties;

import static org.bakapa.domain.BackupTargetType.POSTGRESQL;
import static ru.vyukov.bakapa.controller.domain.backup.database.DatabaseLocation.databaseLocation;
import static ru.vyukov.bakapa.controller.domain.backup.database.DatabaseUserCredentials.userCredentials;
import static ru.vyukov.bakapa.controller.domain.backup.database.DatabaseBackupOptions.backupOptions;


/**
 * Target database
 */
@Getter
public class DatabaseBackupTarget extends AbstractBackupTarget {


    @Valid
    @NotNull
    @JsonView(Summary.class)
    private DatabaseLocation location;

    @Valid
    @NotNull
    @JsonView(Summary.class)
    private DatabaseUserCredentials userCredentials;

    @Valid
    @NotNull
    @JsonView(Summary.class)
    private DatabaseBackupOptions options;


    @Builder(toBuilder = true)
    @JsonCreator
    @ConstructorProperties({"backupTargetId", "agent", "targetType", "location", "userCredentials", "options"})
    public DatabaseBackupTarget(String backupTargetId, Agent agent, BackupTargetType targetType, DatabaseLocation location, DatabaseUserCredentials userCredentials, DatabaseBackupOptions options) {
        super(backupTargetId, agent, targetType);
        this.location = location;
        this.userCredentials = userCredentials;
        this.options = options;
    }

    public static DatabaseBackupTarget demo(Agent agent, int i) {
        return builder()
                .backupTargetId("databaseBackupTarget-" + i)
                .agent(agent)
                .targetType(POSTGRESQL)
                .location(databaseLocation()
                        .database("demodb")
                        .host("localhost")
                        .port(5432)
                        .build())
                .userCredentials(userCredentials()
                        .password("qwerty")
                        .username("root")
                        .build())
                .options(backupOptions()
                        .excludeTable("table1")
                        .excludeTable("table2")
                        .build())

                .build();
    }

    @Override
    public DatabaseBackupTarget agent(Agent agent) {
        return toBuilder().agent(agent).build();
    }

}