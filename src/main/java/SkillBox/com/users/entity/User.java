package SkillBox.com.users.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@SQLDelete(sql = "UPDATE users_scheme.users SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class User {
    public static final char MALE = 'M';
    public static final char FEMALE = 'F';

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "sex", length=1, columnDefinition="CHAR")
    private char sex;
    @Column(name = "town")
    private String town;
    @Column(name = "email")
    private String email;
    @Column(name = "birthday")
    private LocalDate birthday;
    @Column(name = "deleted")
    private boolean deleted = Boolean.FALSE;

    @OneToMany(mappedBy = "follower",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    private Set<Followers> followUsers = new HashSet<>();


    public User() {
    }

    public User(String first_name, String last_name, String middle_name, char sex, String town, String email, LocalDate birthday) {
        this.firstName = first_name;
        this.lastName = last_name;
        this.middleName = middle_name;
        this.sex = sex;
        this.town = town;
        this.email = email;
        this.birthday = birthday;
    }

    public boolean follow(User user) {
        return followUsers.add(new Followers(this, user));
    }

    public boolean unfollow(User user) {
        return followUsers.remove(new Followers(this, user));
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public char getSex() {
        return sex;
    }

    public String getTown() {
        return town;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Set<Followers> getFollowUsers() {
        return followUsers;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", middle_name='" + middleName + '\'' +
                ", sex='" + sex + '\'' +
                ", town='" + town + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", followUsers=" + followUsers +
                '}';
    }
}
