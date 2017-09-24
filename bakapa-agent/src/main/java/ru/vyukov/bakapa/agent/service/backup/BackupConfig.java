package ru.vyukov.bakapa.agent.service.backup;

import lombok.Value;

/**
 * TODO ходит по конвееру, должен быть немутабельный
 * 
 * @author gelo
 *
 */
@Value
public class BackupConfig {

	/**
	 * Уникальный id бекапа
	 */
	private String backupId;

}
