package com.sadsulsoft.ai_powerd_task_assistance;

import com.sadsulsoft.ai_powerd_task_assistance.entity.Role;
import com.sadsulsoft.ai_powerd_task_assistance.entity.User;
import com.sadsulsoft.ai_powerd_task_assistance.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AiPowerdTaskAssistanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiPowerdTaskAssistanceApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			// Check if an admin user already exists
			if (userRepository.findByUsername("admin").isEmpty()) {
				User admin = new User();
				admin.setUsername("admin");
				admin.setEmail("admin@intellitask.ai");
				admin.setPassword(passwordEncoder.encode("adminpassword"));
				admin.setRole(Role.ADMIN);
				userRepository.save(admin);
				System.out.println("Default admin user created.");
			}
		};
	}
}