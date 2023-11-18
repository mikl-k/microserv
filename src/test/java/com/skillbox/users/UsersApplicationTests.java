package com.skillbox.users;

import com.skillbox.users.support.PostgreSQLInitializer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class UsersApplicationTests extends PostgreSQLInitializer {

	@Test
	void contextLoads() {
	}

}
