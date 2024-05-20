package dev.aaiyvan.invitationcodeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class InvitationCodeServiceApplication {

	public static void main(
			final String[] args
	) {
		SpringApplication.run(InvitationCodeServiceApplication.class, args);
	}

}
