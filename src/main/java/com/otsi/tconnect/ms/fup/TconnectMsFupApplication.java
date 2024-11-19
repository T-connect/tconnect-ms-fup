package com.otsi.tconnect.ms.fup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TconnectMsFupApplication {

	public static void main(String[] args) {
		SpringApplication.run(TconnectMsFupApplication.class, args);
	}

}
