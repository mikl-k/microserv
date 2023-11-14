package com.skillbox.users.repository;

import com.skillbox.users.entity.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {
}
