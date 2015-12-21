package se.fredrikandthenurses.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Entity
public class User extends AbstractEntity {

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column
    private boolean active;

    private Collection<Order> orderList;

    protected User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.active = true;
        orderList = new HashSet<>();
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

    public Collection<Order> getOrderList() {
        return orderList;
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
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        return password != null ? password.equals(user.password) : user.password == null;

    }

    public User addOrder(Order order) {
        orderList.add(order);
        return this;
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
