package ru.vyukov.bakapa.agent.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BackupServiceConfigTest {

	@InjectMocks
	private BackupServiceConfig backupServiceConfig;

	@Test
	public void testGetBackupPartionSize() throws Exception {
		backupServiceConfig.setBackupPartionSize("2MB");
		assertEquals(2 * 1024 * 1024, backupServiceConfig.getBackupPartionSizeInBytes());

		//
		backupServiceConfig.setBackupPartionSize("2GB");
		assertEquals(2L * 1024 * 1024 * 1024, backupServiceConfig.getBackupPartionSizeInBytes());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetBackupPartionSizeIllegalFormat() throws Exception {
		backupServiceConfig.setBackupPartionSize("GB");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetBackupPartionSizeIllegalFormat1() throws Exception {
		backupServiceConfig.setBackupPartionSize("12.2GB");
	}

}
