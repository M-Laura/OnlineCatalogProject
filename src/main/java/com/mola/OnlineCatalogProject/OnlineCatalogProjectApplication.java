package com.mola.OnlineCatalogProject;

import com.mola.OnlineCatalogProject.model.Role;
import com.mola.OnlineCatalogProject.model.User;
import com.mola.OnlineCatalogProject.repository.RoleRepository;
import com.mola.OnlineCatalogProject.repository.StudentRepository;
import com.mola.OnlineCatalogProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class OnlineCatalogProjectApplication implements CommandLineRunner {

	@Autowired
	private StudentRepository studentRepository;

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(OnlineCatalogProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Role role = new Role();
		role.setRoleName("ROLE_ADMIN");
		roleRepository.save(role);

		User user = new User();
		user.setUsername("test");
		user.setPassword(encoder().encode("test"));

		user.setRole(roleRepository.findByRoleName("ROLE_ADMIN").get());
		userRepository.save(user);

	}
}
