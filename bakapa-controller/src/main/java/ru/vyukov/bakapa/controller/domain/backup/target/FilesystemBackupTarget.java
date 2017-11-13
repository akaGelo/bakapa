package ru.vyukov.bakapa.controller.domain.backup.target;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import ru.vyukov.bakapa.domain.BackupTargetType;
import org.hibernate.validator.constraints.NotEmpty;
import ru.vyukov.bakapa.controller.domain.View.Summary;
import ru.vyukov.bakapa.controller.domain.agent.Agent;

import javax.validation.constraints.NotNull;
import java.beans.ConstructorProperties;

import static ru.vyukov.bakapa.domain.BackupTargetType.FILESYSTEM;
import static ru.vyukov.bakapa.dto.backups.target.BackupTarget.EVERY_DAY_CRON_TRIGGER;

/**
 * Target directory
 */
@Getter
public class FilesystemBackupTarget extends AbstractBackupTarget {


    @NonNull
    @NotNull
    @NotEmpty
    @JsonView(Summary.class)
    private String path;

    @JsonCreator
    @Builder(toBuilder = true)
    @ConstructorProperties({"backupTargetId", "agent", "targetType", "trigger", "path"})
    public FilesystemBackupTarget(String backupTargetId, Agent agent, BackupTargetType targetType, String trigger, String path) {
        super(backupTargetId, agent, targetType, trigger);
        this.path = path;
    }


    public static FilesystemBackupTarget demo(Agent agent, int i) {
        return builder()
                .backupTargetId("FilesystemBackupTarget-" + i)
                .agent(agent)
                .targetType(FILESYSTEM)
                .trigger(EVERY_DAY_CRON_TRIGGER)

                .path("/etc/")
                .build();
    }

    @Override
    public FilesystemBackupTarget agent(Agent agent) {
        return toBuilder().agent(agent).build();
    }


}
