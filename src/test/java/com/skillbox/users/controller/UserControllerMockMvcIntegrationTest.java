package com.skillbox.users.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillbox.users.entity.User;
import com.skillbox.users.repository.UserRepository;
import com.skillbox.users.support.PostgreSQLInitializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerMockMvcIntegrationTest extends PostgreSQLInitializer {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    @BeforeEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void createUser() throws Exception {
        User user = new User("Nikolay", "Petrov", "Ivanovich", User.MALE,
                "Moscow", "petrov@mail.ru", LocalDate.of(1986, 7, 12));

        mockMvc.perform(
                post("/users")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().string(startsWith("User 'Petrov' added with id =")));
    }

    @Test
    void getUser() throws Exception {
        User user = new User(1L, "Sergey", "Vasin", "Petrovich", User.MALE,
                "Belgorod", "vasin@yandex.ru", LocalDate.of(1980, 2, 23));
        userRepository.save(user);

        User saved = userRepository.findAll().iterator().next();

        mockMvc.perform(
                get(String.format("/users/%d", saved.getId()))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.lastName").value(user.getLastName()))
                .andExpect(jsonPath("$.firstName").value(user.getFirstName()))
                .andExpect(jsonPath("$.middleName").value(user.getMiddleName()))
                .andExpect(jsonPath("$.sex").value(String.valueOf(user.getSex())))
                .andExpect(jsonPath("$.town").value(user.getTown()))
                .andExpect(jsonPath("$.email").value(user.getEmail()))
                .andExpect(jsonPath("$.birthday").value(user.getBirthday().toString()));
    }
/*
    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void getUsers() {
    }

 */

    @Test
    void followUser() throws Exception {
        User user = new User("Nikolay", "Petrov", "Ivanovich", User.MALE,
                "Moscow", "petrov@mail.ru", LocalDate.of(1986, 7, 12));
        User follower = new User("Sergey", "Vasin", "Petrovich", User.MALE,
                "Belgorod", "vasin@yandex.ru", LocalDate.of(1980, 2, 23));
        userRepository.save(user);
        userRepository.save(follower);

        ArrayList<User> usersSaved = new ArrayList<>();
        for (User u : userRepository.findAll()) {
            usersSaved.add(u);
        }

        mockMvc.perform(
                        post(String.format("/users/follow/%d:%d"
                                , usersSaved.get(0).getId(), usersSaved.get(1).getId()))
                )
                .andExpect(status().isOk())
                .andExpect(content().string(String.format("%s (id = %d) follows to %s (id = %d)"
                        , usersSaved.get(0).getLastName(), usersSaved.get(0).getId()
                        , usersSaved.get(1).getLastName(), usersSaved.get(1).getId())));
    }
/*
    @Test
    void unfollowUser() {
    }

 */
}