package se.fredrikandthenurses.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@NamedQueries(value ={
        @NamedQuery(name = "User.GetAll", query = "SELECT u FROM User u"),
        @NamedQuery(name = "User.FindByUsername", query = "SELECT u FROM User u WHERE u.username = ?1"),
        @NamedQuery(name= "User.GetAllWithOrders", query = "SELECT u FROM User u JOIN FETCH u.persistableOrderList")})


public class User extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column
    private boolean active;
    @OneToMany(mappedBy = "user")
    private Collection<PersistableOrder> persistableOrderList;

    protected User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.active = true;
        persistableOrderList = new HashSet<>();
    }

    public String getUsername() {
        return username;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Collection<PersistableOrder> getPersistableOrderList() {
        return new HashSet<>(persistableOrderList);
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        if (active != user.active) return false;
        if (username != null ? !username.equalsIgnoreCase(user.username) : user.username != null) return false;
        return password != null ? password.equals(user.password) : user.password == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (active ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                '}';
    }
}
