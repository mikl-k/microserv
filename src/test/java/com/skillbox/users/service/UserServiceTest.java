package com.skillbox.users.service;

import com.skillbox.users.entity.User;
import com.skillbox.users.repository.UserRepository;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

class UserServiceTest {

    @Test
    void createUserSuccess() {
        //given
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        User user = new User("Nikolay", "Petrov", "Ivanovich", User.MALE,
                "Moscow", "petrov@mail.ru", LocalDate.of(1986, 7, 12));
        User savedUser = new User(1L, "Nikolay", "Petrov", "Ivanovich", User.MALE,
                "Moscow", "petrov@mail.ru", LocalDate.of(1986, 7, 12));
        Mockito.when(userRepository.save(user)).thenReturn(savedUser);
        UserService userService = new UserService(userRepository);

        //when
        String result = userService.createUser(user);

        //then
        Assertions.assertEquals("User 'Petrov' added with id = 1", result);
    }

    @Test
    void createUserError() {
        //given
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        User user = new User("Nikolay", "Petrov", "Ivanovich", User.MALE,
                "Moscow", "petrov@mail.ru", LocalDate.of(1986, 7, 12));
        Mockito.when(userRepository.save(user)).thenThrow(PersistenceException.class);
        UserService userService = new UserService(userRepository);

        //when
        Executable executable = () -> userService.createUser(user);

        //then
        Assertions.assertThrows(PersistenceException.class, executable);
    }

    @Test
    void getUserSuccess() {
        //given
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        User user = new User("Nikolay", "Petrov", "Ivanovich", User.MALE,
                "Moscow", "petrov@mail.ru", LocalDate.of(1986, 7, 12));
        User savedUser = new User(1L, "Nikolay", "Petrov", "Ivanovich", User.MALE,
                "Moscow", "petrov@mail.ru", LocalDate.of(1986, 7, 12));

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(savedUser));
        UserService userService = new UserService(userRepository);
        //when
        User result = userService.getUser(1L);
        //then
        Assertions.assertEquals(result, savedUser);
    }

    @Test
    void getUserError() {
        //given
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        User user = new User("Nikolay", "Petrov", "Ivanovich", User.MALE,
                "Moscow", "petrov@mail.ru", LocalDate.of(1986, 7, 12));
        Mockito.when(userRepository.save(user)).thenThrow(new ResponseStatusException(NOT_FOUND));
        UserService userService = new UserService(userRepository);
        //when
        Executable executable = () -> userService.getUser(1L);
        //then
        Assertions.assertThrows(ResponseStatusException.class, executable);
    }

    @Test
    void updateUserSuccess() {
        //given
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        User user = new User("Nikolay", "Petrov", "Ivanovich", User.MALE,
                "Moscow", "petrov@mail.ru", LocalDate.of(1986, 7, 12));
        User savedUser = new User(1L, "Nikolay", "Petrov", "Ivanovich", User.MALE,
                "Moscow", "petrov@mail.ru", LocalDate.of(1986, 7, 12));
        Mockito.when(userRepository.existsById(1L)).thenReturn(true);
        Mockito.when(userRepository.save(user)).thenReturn(savedUser);
        UserService userService = new UserService(userRepository);

        //when
        String result = userService.updateUser(user, 1L);

        //then
        Assertions.assertEquals("User 'Petrov' updated", result);
    }

    @Test
    void updateUserError() {
        //given
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        User user = new User("Nikolay", "Petrov", "Ivanovich", User.MALE,
                "Moscow", "petrov@mail.ru", LocalDate.of(1986, 7, 12));
        UserService userService = new UserService(userRepository);

        //when
        Executable executable = () -> userService.updateUser(user, 1L);

        //then
        Assertions.assertThrows(ResponseStatusException.class, executable);
    }

    @Test
    void deleteUserSuccess() {
        //given
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        User user = new User("Nikolay", "Petrov", "Ivanovich", User.MALE,
                "Moscow", "petrov@mail.ru", LocalDate.of(1986, 7, 12));
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        UserService userService = new UserService(userRepository);

        //when
        String result = userService.deleteUser(1L);

        //then
        Assertions.assertEquals("User with id = 1 deleted", result);
    }

    @Test
    void deleteUserError() {
        //given
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        UserService userService = new UserService(userRepository);

        //when
        Executable executable = () -> userService.deleteUser( 1L);

        //then
        Assertions.assertThrows(ResponseStatusException.class, executable);
    }


    @Test
    void followUserSuccess() {
        //given
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        User user = new User(1L, "Nikolay", "Petrov", "Ivanovich", User.MALE,
                "Moscow", "petrov@mail.ru", LocalDate.of(1986, 7, 12));
        User follow = new User(2L, "Sergey", "Vasin", "Petrovich", User.MALE,
                "Belgorod", "vasin@yandex.ru", LocalDate.of(1980, 2, 23));
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.findById(2L)).thenReturn(Optional.of(follow));
        UserService userService = new UserService(userRepository);

        //when
        String result = userService.followUser(1L, 2L);

        //then
        Assertions.assertEquals("Petrov (id = 1) follows to Vasin (id = 2)", result);
    }

    @Test
    void followUserError() {
        //given
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());
        UserService userService = new UserService(userRepository);

        //when
        Executable executable = () -> userService.followUser(1L, 2L);

        //then
        Assertions.assertThrows(ResponseStatusException.class, executable);
    }

    @Test
    void followUserFollowError() {
        //given
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        User user = new User(1L, "Nikolay", "Petrov", "Ivanovich", User.MALE,
                "Moscow", "petrov@mail.ru", LocalDate.of(1986, 7, 12));
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.findById(2L)).thenReturn(Optional.empty());
        UserService userService = new UserService(userRepository);

        //when
        Executable executable = () -> userService.followUser(1L, 2L);

        //then
        Assertions.assertThrows(ResponseStatusException.class, executable);
    }

    @Test
    void unfollowUser() {
        //given
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        User user = new User(1L, "Nikolay", "Petrov", "Ivanovich", User.MALE,
                "Moscow", "petrov@mail.ru", LocalDate.of(1986, 7, 12));
        User follow = new User(2L, "Sergey", "Vasin", "Petrovich", User.MALE,
                "Belgorod", "vasin@yandex.ru", LocalDate.of(1980, 2, 23));
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.findById(2L)).thenReturn(Optional.of(follow));

        UserService userService = new UserService(userRepository);

        //when
        String result = userService.unfollowUser(1L, 2L);

        //then
        Assertions.assertEquals("Petrov (id = 1) unfollows from Vasin (id = 2)", result);
    }

    @Test
    void unfollowUserError() {
        //given
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());
        UserService userService = new UserService(userRepository);

        //when
        Executable executable = () -> userService.unfollowUser(1L, 2L);

        //then
        Assertions.assertThrows(ResponseStatusException.class, executable);
    }

    @Test
    void unfollowUserFollowError() {
        //given
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        User user = new User(1L, "Nikolay", "Petrov", "Ivanovich", User.MALE,
                "Moscow", "petrov@mail.ru", LocalDate.of(1986, 7, 12));
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.findById(2L)).thenReturn(Optional.empty());
        UserService userService = new UserService(userRepository);

        //when
        Executable executable = () -> userService.unfollowUser(1L, 2L);

        //then
        Assertions.assertThrows(ResponseStatusException.class, executable);
    }

}