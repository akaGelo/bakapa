package ru.vyukov.bakapa.controller.domain.backup;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.bakapa.domain.BackupTargetType;
import org.hibernate.validator.constraints.NotEmpty;
import ru.vyukov.bakapa.controller.domain.View.Summary;
import ru.vyukov.bakapa.controller.domain.agent.Agent;

import javax.validation.constraints.NotNull;
import java.beans.ConstructorProperties;

import static org.bakapa.domain.BackupTargetType.FILESYSTEM;

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
    @ConstructorProperties({"backupTargetId", "agent", "targetType", "path"})
    public FilesystemBackupTarget(String backupTargetId, Agent agent, BackupTargetType targetType, String path) {
        super(backupTargetId, agent, targetType);
        this.path = path;
    }


    public static FilesystemBackupTarget demo(Agent agent, int i) {
        return builder()
                .backupTargetId("FilesystemBackupTarget-" + i)
                .agent(agent)
                .targetType(FILESYSTEM)

                .path("/etc/")
                .build();
    }

    @Override
    public FilesystemBackupTarget agent(Agent agent) {
        return toBuilder().agent(agent).build();
    }


}
