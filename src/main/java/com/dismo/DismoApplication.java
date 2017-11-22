package com.dismo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dismo.mapper")
public class DismoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DismoApplication.class, args);
	}
}
