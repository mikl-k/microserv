package com.skillbox.users.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FollowerKey implements Serializable {
    @Column(name = "follower_id")
    Long followerId;

    @Column(name = "publisher_id")
    Long publisherId;

    public FollowerKey(Long followerId, Long publisherId) {
        this.followerId = followerId;
        this.publisherId = publisherId;
    }

    public FollowerKey() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FollowerKey that)) return false;
        return Objects.equals(followerId, that.followerId) && Objects.equals(publisherId, that.publisherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(followerId, publisherId);
    }
}
