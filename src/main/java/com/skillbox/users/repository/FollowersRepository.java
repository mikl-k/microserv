package com.skillbox.users.repository;

import com.skillbox.users.entity.Followers;
import org.springframework.data.repository.CrudRepository;

public interface FollowersRepository extends CrudRepository<Followers, Long> {
}
