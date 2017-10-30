package ru.vyukov.bakapa.controller.domain.backup;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.bakapa.domain.BackupTargetType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.vyukov.bakapa.controller.domain.View.Summary;
import ru.vyukov.bakapa.controller.domain.agent.Agent;

import javax.validation.constraints.NotNull;


/**
 * Abstract backup target
 */
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Document(collection = "backupTargets")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "targetType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DirectoryBackupTarget.class, name = "FILESYSTEM"),
        //
        @JsonSubTypes.Type(value = DatabaseBackupTarget.class, name = "MYSQL"),
        @JsonSubTypes.Type(value = DatabaseBackupTarget.class, name = "MONGODB"),
        @JsonSubTypes.Type(value = DatabaseBackupTarget.class, name = "POSTGRESQL")
})
abstract public class AbstractBackupTarget {

    @Id
    @JsonView(Summary.class)
    private String backupTargetId;

    @NotNull
    @NonNull
    @Indexed
    @DBRef
    private Agent agent;

    @NonNull
    @NotNull
    @Getter
    @JsonView(Summary.class)
    private BackupTargetType targetType;

}