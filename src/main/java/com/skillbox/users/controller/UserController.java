package com.skillbox.users.controller;

import com.skillbox.users.entity.User;
import com.skillbox.users.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    String createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping(path = "/{id}")
    User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PatchMapping("/{id}")
    String updateUser(@RequestBody Map<String, String> userDetails, @PathVariable Long id) {
        User user = userService.getUser(id);
        for(Map.Entry<String, String> entry: userDetails.entrySet()) {
            switch (entry.getKey()) {
                case "firstName" -> user.setFirstName(entry.getValue());
                case "lastName" -> user.setLastName(entry.getValue());
                case "middleName" -> user.setMiddleName(entry.getValue());
                case "sex" -> user.setSex((entry.getValue()).charAt(0));
                case "town" -> user.setTown(entry.getValue());
                case "email" -> user.setEmail(entry.getValue());
                case "birthday" -> user.setBirthday(LocalDate.parse(entry.getValue()));
            }
        }
        return userService.updateUser(user, id);
    }

    @DeleteMapping("/{id}")
    String deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @GetMapping
    Iterable<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/follow/{id}:{followId}")
    String followUser(@PathVariable Long id, @PathVariable Long followId) {
        return userService.followUser(id, followId);
    }

    @PostMapping("/unfollow/{id}:{followId}")
    String unfollowUser(@PathVariable Long id, @PathVariable Long followId) {
        return userService.unfollowUser(id, followId);
    }
}
