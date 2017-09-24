package ru.vyukov.bakapa.agent.service.backup;

public class BackupTimeoutException extends Exception {
	private static final long serialVersionUID = 1234222106575310821L;

	private String backupId;

	public BackupTimeoutException(String backupId) {
		super("Timeout on backup " + backupId);
		this.backupId = backupId;
	}

	public String getBackupId() {
		return backupId;
	}
}
