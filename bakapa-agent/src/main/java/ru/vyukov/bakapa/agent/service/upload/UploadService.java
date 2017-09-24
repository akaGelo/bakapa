package ru.vyukov.bakapa.agent.service.upload;

import java.util.concurrent.TimeUnit;

public interface UploadService {

	boolean offerUpload(UploadTask task, long timeout, TimeUnit unit) throws InterruptedException;

}
