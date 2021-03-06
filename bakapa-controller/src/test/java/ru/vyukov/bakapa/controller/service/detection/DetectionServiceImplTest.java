package ru.vyukov.bakapa.controller.service.detection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.MockitoJUnitRunner;
import ru.vyukov.bakapa.controller.domain.config.DetectionConfig;
import ru.vyukov.bakapa.controller.repo.ConfigsRepository;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DetectionServiceImplTest {
	@Mock
	private ConfigsRepository configsRepository;

	@InjectMocks
	private DetectionServiceImpl detectionServiceImpl;

	@Test
	public void testInit() throws Exception {
		when(configsRepository.findOneDetectionConfigById(DetectionConfig.INSTANCE_ID)).thenReturn(null);

		detectionServiceImpl.init();

		verify(configsRepository).save(any(DetectionConfig.class));
	}

}
