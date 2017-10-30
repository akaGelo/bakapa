package ru.vyukov.bakapa.agent.service.upload;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.minio.MinioClient;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmlpull.v1.XmlPullParserException;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.*;
import java.util.stream.IntStream;

@Slf4j
@Service
public class UploadServiceImpl implements UploadService {

	private ExecutorService uploadExecutor;

	private BlockingQueue<UploadTask> uploadQueue;

	private UploadConfig uploadConfig;

	public UploadServiceImpl(@Autowired UploadConfig uploadConfig) {
		this.uploadConfig = uploadConfig;
		int queueSize = uploadConfig.getUploadPartionsSize();
		uploadQueue = new ArrayBlockingQueue<>(queueSize);
	}

	@PreDestroy
	public void destroy() {
		uploadExecutor.shutdown();
	}

	@Override
	public boolean offerUpload(UploadTask task, long timeout, TimeUnit unit) throws InterruptedException {
		if (uploadExecutor.isShutdown()) {
			throw new RejectedExecutionException();
		}
		initUploadExecutor();
		return uploadQueue.offer(task, timeout, unit);
	}

	synchronized private void initUploadExecutor() {
		if (null == uploadExecutor) {
			ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("upload-pool-%d").build();
			int paralel = uploadConfig.getParalel();
			uploadExecutor = Executors.newFixedThreadPool(paralel, threadFactory);
			IntStream.range(0, paralel).forEach((i) -> {
				uploadExecutor.execute(new Task());
			});
		}
	}

	/**
	 * For hide Runnable.run method
	 * 
	 * @author gelo
	 *
	 */
	private class Task implements Runnable {
		public void run() {
			try {
				while (!uploadExecutor.isShutdown()) {
					uploadQueue.take();
				}
			} catch (InterruptedException e) {
				log.info("Upload thread iterrupted");
			}
		}
	}

	public void upload()
			throws InvalidEndpointException, InvalidPortException, InvalidKeyException, InvalidBucketNameException,
			NoSuchAlgorithmException, InsufficientDataException, NoResponseException, ErrorResponseException,
			InternalException, IOException, XmlPullParserException, RegionConflictException, InvalidArgumentException {

		MinioClient minioClient = new MinioClient("https://play.minio.io:9000", "Q3AM3UQ867SPQQA43P2F",
				"zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG");
		// Check if the bucket already exists.
		boolean isExist = minioClient.bucketExists("asiatrip");
		if (isExist) {
			System.out.println("Bucket already exists.");
		} else {
			// Make a new bucket called asiatrip to hold a zip file of photos.
			minioClient.makeBucket("asiatrip");
		}

		// Upload the zip file to the bucket with putObject
		minioClient.putObject("asiatrip", "asiaphotos.zip", "/home/user/Photos/asiaphotos.zip");

	}
}
