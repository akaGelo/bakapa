package ru.vyukov.bakapa.controller.repo;

import static org.junit.Assert.*;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import ru.vyukov.bakapa.controller.config.MongoDbConfig;
import ru.vyukov.bakapa.controller.domain.DetectionConfig;

@RunWith(SpringRunner.class)
@Import({ MongoDbConfig.class, ValidationAutoConfiguration.class })
@DataMongoTest
public class DetectionConfigRepositoryTest {

	@Autowired
	private DetectionConfigRepository detectionConfigRepository;

	@Test
	public void test() {
		DetectionConfig config = detectionConfigRepository.findOne(DetectionConfig.INSTANCE_ID);
		if (null == config) {
			config = new DetectionConfig();
		}

		config.getMongoPorts().add(12);
		detectionConfigRepository.save(config);

		config = detectionConfigRepository.findOne(DetectionConfig.INSTANCE_ID);
		assertNotNull(config);
		assertTrue(config.getMongoPorts().contains(12));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nullFailtTest() {
		DetectionConfig config = new DetectionConfig();
		detectionConfigRepository.save(config);
	}

}
