package com.skillbox.users.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillbox.users.entity.User;
import com.skillbox.users.repository.UserRepository;
import com.skillbox.users.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ActiveProfiles("test")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserService userService;

    @Test
    void updateUser_whenUpdate_thenStatus200andStringReturned() throws Exception {
        User user = new User(1L, "Nikolay", "Petrov", "Ivanovich", User.MALE,
                "Moscow", "petrov@mail.ru", LocalDate.of(1986, 7, 12));

        Mockito.when(userService.getUser(1L)).thenReturn(user);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(userService.updateUser(user, 1L)).thenReturn(String.format("User '%s' updated", user.getLastName()));


        mockMvc.perform(
                        patch("/users/1")
                                .content(objectMapper.writeValueAsString(user))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().string(String.format("User '%s' updated", user.getLastName())));
    }
/*
    @Test
    void deleteUser() {
    }

    @Test
    void getUsers() {
    }

    @Test
    void unfollowUser() {
    }

 */
}