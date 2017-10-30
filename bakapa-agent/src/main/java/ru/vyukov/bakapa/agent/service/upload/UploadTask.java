package ru.vyukov.bakapa.agent.service.upload;

import lombok.NonNull;
import lombok.Value;
import ru.vyukov.bakapa.agent.service.backup.BackupConfig;

import java.io.File;

/**
 * Все ресурсы, в том чсле файлы, уничтожаются сразу после выгрузки
 * 
 * @author gelo
 *
 */
@Value
public class UploadTask {

	@NonNull
	private File file;

	@NonNull
	private BackupConfig backupConfig;

}