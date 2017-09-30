package ru.vyukov.bakapa.controller.service.detection;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ru.vyukov.bakapa.controller.domain.DetectionConfig;
import ru.vyukov.bakapa.controller.repo.DetectionConfigRepository;

@RunWith(MockitoJUnitRunner.class)
public class DetectionServiceImplTest {
	@Mock
	private DetectionConfigRepository detectionConfigRepository;
	@InjectMocks
	private DetectionServiceImpl detectionServiceImpl;

	@Test
	public void testInit() throws Exception {
		when(detectionConfigRepository.findOne(DetectionConfig.INSTANCE_ID)).thenReturn(null);

		detectionServiceImpl.init();

		verify(detectionConfigRepository).save(any(DetectionConfig.class));
	}

}
