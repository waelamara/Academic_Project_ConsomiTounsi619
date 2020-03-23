package tn.esprit.spring;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import Config.FileStorageProperties;


@SpringBootApplication
@EnableConfigurationProperties({ FileStorageProperties.class })
public class ConsomiTounsiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsomiTounsiApplication.class, args);
	
	}

}
