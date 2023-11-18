package com.skillbox.users;

import com.skillbox.users.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"com.skillbox.users"})
public class UsersApplication {

	final static Logger logger = LoggerFactory.getLogger(UsersApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(UsersApplication.class, args);
	}

	@Bean
	CommandLineRunner demoJPA(UserRepository userRepository) {
		return (args -> {
			logger.info("logger info");
			logger.debug("logger debug");
		});
	}

/*
	@Bean
	CommandLineRunner demoJPA(UserRepository userRepository) {
		return (args -> {
			User user1 = new User(
					  "FName1"
					, "LName1"
					, "MName1"
					, User.FEMALE
					, "Moscow"
					, "FName1@mail.ru"
					, LocalDate.parse("11.11.2000", DateTimeFormatter.ofPattern("dd.MM.yyyy")));

			User user2 = new User(
					"FName2"
					, "LName2"
					, "MName2"
					, User.MALE
					, "Tomsk"
					, "FName2@mail.ru"
					, LocalDate.parse("12.12.2002", DateTimeFormatter.ofPattern("dd.MM.yyyy")));

			userRepository.save(user1);
			userRepository.save(user2);

			user1.follow(user2);
			user2.follow(user1);

			user1.unfollow(user1);
			user1.unfollow(user2);

			userRepository.save(user1);
			userRepository.save(user2);
			userRepository.delete(user1);

		});
	}*/
}
