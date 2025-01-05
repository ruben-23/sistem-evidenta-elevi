package com.liceu.sistem_evidenta_elevi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clasa principală care lansează aplicația Spring Boot pentru sistemul de evidență a elevilor.
 */
@SpringBootApplication  // Marchează această clasă ca o aplicație Spring Boot
public class SistemDeEvidentaAElevilorApplication {

	/**
	 * Punctul de intrare pentru aplicația Spring Boot.
	 * Această metodă rulează aplicația.
	 *
	 * @param args Argumente din linia de comandă (dacă sunt)
	 */
	public static void main(String[] args) {
		// Lansează aplicația Spring Boot
		SpringApplication.run(SistemDeEvidentaAElevilorApplication.class, args);
	}
}