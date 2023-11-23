package com.skillbox.users;

import com.skillbox.users.support.PostgreSQLInitializer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class UsersApplicationTests extends PostgreSQLInitializer {

	@Test
	void contextLoads() {
	}

}
