package br.com.totvs.reportgenerator;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.totvs.reportgenerator.controller.UploadController;



@SpringBootApplication
public class ReportgeneratorApplication implements CommandLineRunner {

	private final UploadController uploadController;

	public ReportgeneratorApplication(UploadController uploadController) {
		this.uploadController = uploadController;
	}

	public static void main(String[] args) {
		SpringApplication.run(ReportgeneratorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
