package com.gdgteam7.idohyangchon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class IdohyangchonApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdohyangchonApplication.class, args);
	}

}
