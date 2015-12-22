package se.fredrikandthenurses.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@NamedQueries(value = {
        @NamedQuery(name = "Order.GetAll", query = "SELECT o FROM Order o")
})
public class Order extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.PERSIST)

    private User user;

    @OneToMany(mappedBy = "order")
    private Collection<OrderRow> orderRowList;

    private String orderNumber;
    private String status;

    private OrderStatus orderStatus;

    protected Order() {
    }

    public Order(String orderNumber, User user) {
        this.user = user;
        this.orderRowList = new ArrayList<>();
        this.orderNumber = orderNumber;
        orderStatus = OrderStatus.PLACED;
    }

    public User getUser() {
        return user;
    }

    public Collection<OrderRow> getOrderRowList() {
        return orderRowList;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setStatusShipped(){
        orderStatus = OrderStatus.SHIPPED;
    }

    public void setStatusPaid(){
        orderStatus = OrderStatus.PAID;
    }

    public void setStatusCanceled(){
        orderStatus = OrderStatus.CANCELLED;
    }

    public String getStatus() {
        return status;
    }

    public double getPrice(){
        double totalPrice =0;
        for (OrderRow row : orderRowList) {
            totalPrice += row.getPrice();
        }
        return totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (user != null ? !user.equals(order.user) : order.user != null) return false;
        if (orderRowList != null ? !orderRowList.equals(order.orderRowList) : order.orderRowList != null) return false;
        if (orderNumber != null ? !orderNumber.equals(order.orderNumber) : order.orderNumber != null) return false;
        return status != null ? status.equals(order.status) : order.status == null;

    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (orderRowList != null ? orderRowList.hashCode() : 0);
        result = 31 * result + (orderNumber != null ? orderNumber.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "user=" + user +
                ", orderRowList=" + orderRowList +
                ", orderNumber='" + orderNumber + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

