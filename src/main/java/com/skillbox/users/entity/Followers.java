package com.skillbox.users.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Objects;

@Entity
@Table(name = "followers")
@SQLDelete(sql = "UPDATE users_scheme.followers SET deleted = true WHERE follower_id = ? and publisher_id = ?")
@Where(clause = "deleted=false")
public class Followers {
    @EmbeddedId
    FollowerKey id;

    @ManyToOne
    @MapsId("followerId")
    @JoinColumn(name = "follower_id")
    User follower;

    @ManyToOne
    @MapsId("publisherId")
    @JoinColumn(name = "publisher_id")
    User publisher;

    @JoinColumn(name = "deleted")
    boolean deleted;

    public Followers(User follower, User publisher) {
        this.id = new FollowerKey(follower.getId(), publisher.getId());
        this.follower = follower;
        this.publisher = publisher;
        this.deleted = false;
    }

    public Followers() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Followers followers)) return false;
        return Objects.equals(id, followers.id) && Objects.equals(follower, followers.follower)
            && Objects.equals(publisher, followers.publisher) && deleted == followers.deleted;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, follower, publisher, deleted);
    }

    public Followers setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }
}
