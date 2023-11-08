package SkillBox.com.users;

import SkillBox.com.users.entity.User;
import SkillBox.com.users.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class UsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersApplication.class, args);
	}

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
	}
}
