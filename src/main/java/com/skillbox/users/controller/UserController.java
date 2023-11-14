package com.skillbox.users.controller;

import com.skillbox.users.entity.User;
import com.skillbox.users.service.UserService;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}")
    String updateUser(@RequestBody User user, @PathVariable Long id) {
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
