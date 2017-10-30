package ru.vyukov.bakapa.agent.service.upload;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;

@Data
@Component
@ConfigurationProperties("upload")
public class UploadConfig {

	/**
	 * Количество файлов бекапа на диске в очереди. Больше не будет приниматся и
	 * будет тормозить stdout
	 */
	@Min(1)
	private int uploadPartionsSize = 3;
	
	/**
	 * Количество паралельных загрузок
	 */
	@Min(1)
	private int paralel = 1;

}
