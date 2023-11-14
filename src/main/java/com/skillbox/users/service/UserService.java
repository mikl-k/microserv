package com.skillbox.users.service;

import com.skillbox.users.UsersApplication;
import com.skillbox.users.entity.User;
import com.skillbox.users.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class UserService {
    private final UserRepository userRepository;
    final static Logger logger = LoggerFactory.getLogger(UsersApplication.class);
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String createUser(User user) {
        User savedUser = userRepository.save(user);
        return String.format("User '%s' added with id = %d", savedUser.getLastName(), savedUser.getId());
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    public String updateUser(User user, Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        User saved = userRepository.save(user);

        return String.format( "User '%s' saved", saved.getLastName() );
    }

    public String deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        userRepository.deleteById(id);

        return String.format( "User with id = %d deleted", id );
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public String followUser(Long id, Long followId) {
        Optional<User> user = userRepository.findById(id);
        Optional<User> followUser = userRepository.findById(followId);

        if (user.isEmpty()) {
            logger.error(String.format("User with id = %d not found", id));
            throw new ResponseStatusException(NOT_FOUND);
        }

        if (followUser.isEmpty()) {
            logger.error(String.format("User with id = %d not found", followId));
            throw new ResponseStatusException(NOT_FOUND);
        }

        user.get().follow(followUser.get());
        userRepository.save(user.get());

        return String.format("%s (id = %d) follows to %s (id = %d)"
                , user.get().getLastName()
                , user.get().getId()
                , followUser.get().getLastName()
                , followUser.get().getId() );
    }

    public String unfollowUser(Long id, Long followId) {
        Optional<User> user = userRepository.findById(id);
        Optional<User> followUser = userRepository.findById(followId);

        if (user.isEmpty()) {
            logger.error(String.format("User with id = %d not found", id));
            throw new ResponseStatusException(NOT_FOUND);
        }

        if (followUser.isEmpty()) {
            logger.error(String.format("User with id = %d not found", followId));
            throw new ResponseStatusException(NOT_FOUND);
        }

        user.get().unfollow(followUser.get());
        userRepository.save(user.get());

        return String.format("%s (id = %d) unfollows from %s (id = %d)"
                , user.get().getLastName()
                , user.get().getId()
                , followUser.get().getLastName()
                , followUser.get().getId() );
    }
}
