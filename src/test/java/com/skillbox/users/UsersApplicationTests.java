package com.skillbox.users;

import com.skillbox.users.support.PostgreSQLInitializer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ContextConfiguration(initializers = PostgreSQLInitializer.class)
class UsersApplicationTests {

	@Test
	void contextLoads() {
	}

}
