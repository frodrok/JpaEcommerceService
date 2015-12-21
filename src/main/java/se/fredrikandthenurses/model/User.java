package se.fredrikandthenurses.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class User extends AbstractEntity {

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    protected User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return !(getUsername() != null ? !getUsername().equals(user.getUsername()) : user.getUsername() != null);

    }

    @Override
    public int hashCode() {
        return getUsername() != null ? getUsername().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }
}
