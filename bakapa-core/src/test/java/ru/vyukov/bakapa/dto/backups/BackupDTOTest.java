package ru.vyukov.bakapa.dto.backups;

import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import static java.time.Instant.now;
import static org.junit.Assert.*;

public class BackupDTOTest {

    private Clock clock = Clock.fixed(now(), ZoneId.systemDefault());

    @Test
    public void getDurationReadable() throws Exception {
        BackupDTO backup = new BackupDTO();
        backup.setStartTimestamp(now(clock).minus(125, ChronoUnit.MINUTES));
        assertEquals("2:05:00", backup.getDurationReadable(clock));


        backup.setStartTimestamp(now(clock).minus(625, ChronoUnit.MINUTES));
        assertEquals("10:25:00", backup.getDurationReadable(clock));

        backup.setStartTimestamp(now(clock).minus(1, ChronoUnit.DAYS).minusSeconds(1));
        assertEquals("1 day+", backup.getDurationReadable(clock));
    }

    @Test
    public void getSizeReadable() throws Exception {
        BackupDTO backup = new BackupDTO();

        backup.setSize(1024);
        assertEquals("1 kB", backup.getSizeReadable());

        backup.setSize(1024 * 1024 + 1);
        assertEquals("1 MB", backup.getSizeReadable());

        backup.setSize(1024 * 1024 * 1024 + 1);
        assertEquals("1 GB", backup.getSizeReadable());
    }

}