package ru.vyukov.bakapa.controller.service.backupstargets;

import ru.vyukov.bakapa.controller.domain.agent.Agent;

public class BackupTargetNotFoundException extends Exception {
    public BackupTargetNotFoundException(String backupTargetId) {
        super("Backup target [" + backupTargetId + "] not found");
    }

    public BackupTargetNotFoundException(Agent agent, String backupTargetId) {
        super("Backup target [" + backupTargetId + "] not found on agent [" + agent +"]");
    }
}
