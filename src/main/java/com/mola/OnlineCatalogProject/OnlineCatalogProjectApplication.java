package com.mola.OnlineCatalogProject;

import com.mola.OnlineCatalogProject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineCatalogProjectApplication implements CommandLineRunner {

	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(OnlineCatalogProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
