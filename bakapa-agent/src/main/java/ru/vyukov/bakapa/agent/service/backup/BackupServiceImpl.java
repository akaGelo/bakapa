package ru.vyukov.bakapa.agent.service.backup;

import lombok.Setter;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vyukov.bakapa.agent.service.upload.UploadService;
import ru.vyukov.bakapa.agent.service.upload.UploadTask;

import java.io.*;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;

import static lombok.AccessLevel.PROTECTED;

@Service
public class BackupServiceImpl implements BackupService {

	private final static File TMP_DIR = new File(SystemUtils.JAVA_IO_TMPDIR);

	@Autowired
	@Setter(value=PROTECTED)
	private BackupToolsProvider backupToolsProvider;

	@Autowired
	@Setter(value=PROTECTED)
	private UploadService uploadService;

	public BackupServiceImpl() {
	}

	public void backup(BackupConfig config) {

		BackupTools backupTools = backupToolsProvider.getTools(config);

		try (InputStream inputStream = backupTools.dump()) {
			BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

			File part = null;
			int partNumer = 0;
			final String backupId = config.getBackupId();
			while (null != (part = nextPart(bufferedInputStream, partNumer++, backupId))) {
				UploadTask task = new UploadTask(part, config);
				if (!uploadService.offerUpload(task, 10, TimeUnit.MINUTES)) {
					// исключение, не удалось выгрузить бекап и таймаут прошел
					throw new BackupTimeoutException(backupId);
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (BackupTimeoutException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO offerUpload прерван
			e.printStackTrace();
		}
	}

	/**
	 * Из входящего bufferdInputStream скачивает в файл часть размером из
	 * конфигурации
	 * 
	 * @param bufferedInputStream
	 * @return файл или null если стрим закончен
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private File nextPart(BufferedInputStream bufferedInputStream, int partNumber, String backupId)
			throws FileNotFoundException, IOException {

		File file = createTmpFile(backupId, partNumber);
		long sumReadedBytes = 0;
		try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file))) {

			byte[] buf = new byte[8 * 1024];
			int read = -1;
			while (-1 != (read = bufferedInputStream.read(buf))) {
				bufferedOutputStream.write(buf, 0, read);
				sumReadedBytes += read;
			}

		}

		if (0 == sumReadedBytes) {
			Files.delete(file.toPath());
			return null;
		}
		return file;
	}

	private File createTmpFile(String backupId, int partNumber) {
		return new File(TMP_DIR, backupId + "." + partNumber);
	}
}
