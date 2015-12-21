package se.fredrikandthenurses.model;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Order extends AbstractEntity {

    private User user;

    private List<OrderRow> orderRowList;

    private String orderNumber;
    private boolean active;

    protected Order() {
    }

    public Order(String orderNumber, User user, List<OrderRow> orderRowList) {
        this.user = user;
        this.orderRowList = orderRowList;
        this.orderNumber = orderNumber;
        this.active = true;
    }

    public User getUser() {
        return user;
    }

    public List<OrderRow> getOrderRowList() {
        return orderRowList;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (isActive() != order.isActive()) return false;
        if (getUser() != null ? !getUser().equals(order.getUser()) : order.getUser() != null) return false;
        if (getOrderRowList() != null ? !getOrderRowList().equals(order.getOrderRowList()) : order.getOrderRowList() != null)
            return false;
        return !(getOrderNumber() != null ? !getOrderNumber().equals(order.getOrderNumber()) : order.getOrderNumber() != null);

    }

    @Override
    public int hashCode() {
        int result = getUser() != null ? getUser().hashCode() : 0;
        result = 31 * result + (getOrderRowList() != null ? getOrderRowList().hashCode() : 0);
        result = 31 * result + (getOrderNumber() != null ? getOrderNumber().hashCode() : 0);
        result = 31 * result + (isActive() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + getId() +
                "user=" + user +
                ", orderRowList=" + orderRowList +
                ", orderNumber='" + orderNumber + '\'' +
                ", active=" + active +
                '}';
    }
}

