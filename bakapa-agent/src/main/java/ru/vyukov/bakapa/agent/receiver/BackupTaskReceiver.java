package ru.vyukov.bakapa.agent.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.vyukov.bakapa.agent.service.backup.BackupService;
import ru.vyukov.bakapa.dto.backups.task.BackupTaskDTO;
import ru.vyukov.stomp.Subscribe;

/**
 * @author Oleg Vyukov
 */
@Controller
public class BackupTaskReceiver {

    @Autowired
    private BackupService backupService;

    @Subscribe(BackupTaskDTO.DESTINATION)
    public void backupTask(BackupTaskDTO backupTask) {
        backupService.execute(backupTask);
    }
}
