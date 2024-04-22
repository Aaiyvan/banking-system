package dev.aaiyvan.qrservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * QrServiceApplication is the main entry point for the QR Service application.
 * It uses Spring Boot's SpringApplication.run() method to launch the application.
 */
@SpringBootApplication
public class QrServiceApplication {

	/**
	 * The main method which serves as the entry point for the JVM.
	 * @param args command line arguments passed to the application. Not currently used in this application.
	 */
	public static void main(
			final String[] args
	) {
		SpringApplication.run(QrServiceApplication.class, args);
	}

}
