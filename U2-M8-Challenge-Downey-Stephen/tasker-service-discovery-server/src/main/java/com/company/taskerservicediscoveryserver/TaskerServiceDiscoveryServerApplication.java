package com.company.taskerservicediscoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TaskerServiceDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskerServiceDiscoveryServerApplication.class, args);
	}

}
