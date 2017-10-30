package ru.vyukov.bakapa.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class BakapaAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(BakapaAdminApplication.class, args);
	}

}
