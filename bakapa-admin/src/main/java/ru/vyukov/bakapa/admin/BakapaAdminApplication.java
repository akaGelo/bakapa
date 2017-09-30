package ru.vyukov.bakapa.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class BakapaAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(BakapaAdminApplication.class, args);
	}
	
	@RequestMapping("/")
	public String home() {
		return "dashboard";
	}
}
