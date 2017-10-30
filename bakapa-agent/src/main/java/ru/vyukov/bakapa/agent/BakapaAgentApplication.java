package ru.vyukov.bakapa.agent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.apache.commons.lang3.SystemUtils.IS_OS_LINUX;
import static org.apache.commons.lang3.SystemUtils.OS_ARCH;

@Slf4j
@SpringBootApplication
public class BakapaAgentApplication {

	private static final int EXIT_ERROR_OS = 2;

	public static void main(String[] args) {
		if (!IS_OS_LINUX) {
			log.error("Work only linux");
			System.exit(EXIT_ERROR_OS);
		}
		
		if(! ("x86_64".equals(OS_ARCH) || "amd64".equals(OS_ARCH))) {
			log.error("Work only linux 64-bit");
			System.exit(EXIT_ERROR_OS);
		}

		SpringApplication.run(BakapaAgentApplication.class, args);
	}
}
