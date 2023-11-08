package SkillBox.com.users.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "followers")
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

    public Followers(User follower, User publisher) {
        this.id = new FollowerKey(follower.getId(), publisher.getId());
        this.follower = follower;
        this.publisher = publisher;
    }

    public Followers() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Followers followers)) return false;
        return Objects.equals(id, followers.id) && Objects.equals(follower, followers.follower) && Objects.equals(publisher, followers.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, follower, publisher);
    }
}
