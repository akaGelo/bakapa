package ru.vyukov.bakapa.controller.domain.backup.target.database;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import ru.vyukov.bakapa.controller.domain.backup.target.AbstractBackupTarget;
import ru.vyukov.bakapa.domain.BackupTargetType;
import ru.vyukov.bakapa.controller.domain.View.Summary;
import ru.vyukov.bakapa.controller.domain.agent.Agent;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.beans.ConstructorProperties;

import static ru.vyukov.bakapa.domain.BackupTargetType.POSTGRESQL;
import static ru.vyukov.bakapa.controller.domain.backup.target.database.DatabaseUserCredentials.userCredentials;
import static ru.vyukov.bakapa.dto.backups.target.BackupTarget.EVERY_DAY_CRON_TRIGGER;


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
    @ConstructorProperties({"backupTargetId", "agent", "targetType", "trigger", "location", "userCredentials", "options"})
    public DatabaseBackupTarget(String backupTargetId, Agent agent, BackupTargetType targetType, String trigger, DatabaseLocation location, DatabaseUserCredentials userCredentials, DatabaseBackupOptions options) {
        super(backupTargetId, agent, targetType, trigger);
        this.location = location;
        this.userCredentials = userCredentials;
        this.options = options;
    }

    public static DatabaseBackupTarget demo(Agent agent, int i) {
        return builder()
                .backupTargetId("databaseBackupTarget-" + i)
                .agent(agent)
                .trigger(EVERY_DAY_CRON_TRIGGER)
                .targetType(POSTGRESQL)
                .location(DatabaseLocation.databaseLocation()
                        .database("demodb")
                        .host("localhost")
                        .port(5432)
                        .build())
                .userCredentials(userCredentials()
                        .password("qwerty")
                        .username("root")
                        .build())
                .options(DatabaseBackupOptions.backupOptions()
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