package ru.vyukov.bakapa.dto.backups;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;
import ru.vyukov.bakapa.domain.BackupState;
import ru.vyukov.bakapa.dto.backups.target.FullBackupTargetDTO;
import ru.vyukov.bakapa.dto.backups.target.impl.FilesystemBackupTargetDTO;
import ru.vyukov.bakapa.dto.backups.target.impl.FullFilesystemBackupTargetDTO;

import javax.validation.constraints.NotNull;
import java.text.DecimalFormat;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

import static java.time.Instant.now;
import static ru.vyukov.bakapa.domain.BackupState.INPROGRESS;

/**
 * Base artifact - files and other output shit
 */
@Data
@EqualsAndHashCode(of = "backupId")
//@AllArgsConstructor
@NoArgsConstructor
public class BackupDTO {

    private String backupId;

    @NotNull
    private FullBackupTargetDTO backupTarget;


    @NotNull
    private Instant startTimestamp;


    private Instant finishTimestamp;


    @NotNull
    private BackupState state = INPROGRESS;

    private long size = 0L;


    @JsonIgnore
    public String getSizeReadable() {
        if (size <= 0) return "0";
        final String[] units = new String[]{"B", "kB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }


    @JsonIgnore
    public String getDurationReadable() {
        return getDurationReadable(Clock.systemUTC());
    }

    @JsonIgnore
    String getDurationReadable(Clock clock) {
        Duration duration = Duration.between(startTimestamp, now(clock));
        if (duration.toDays() >= 1) {
            return "1 day+";
        }
        long millis = duration.toMillis();
        return DurationFormatUtils.formatDuration(millis, "H:mm:ss");
    }

    @JsonIgnore
    public String getNameReadable() {
        String shortId = StringUtils.substring(backupId, -6);
        String shortTargetName = StringUtils.abbreviate(backupTarget.getNameReadable(), 15);
        return backupTarget.getAgent().getAgentId() + "->" + shortTargetName + "->" + shortId;

    }

    @JsonIgnore
    public String getFullNameReadable() {
        return backupTarget.getAgent().getAgentId() + "->" + backupTarget.getNameReadable() + "->" + backupId;

    }



    public static BackupDTO demo(int id) {
        BackupDTO backupDTO = new BackupDTO();
        backupDTO.setBackupId("backupId-" + id);
        backupDTO.setBackupTarget(FullFilesystemBackupTargetDTO.defaultExample());
        backupDTO.setStartTimestamp(Instant.now());
        backupDTO.setState(INPROGRESS);
        return backupDTO;
    }


}
