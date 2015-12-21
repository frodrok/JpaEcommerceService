package se.fredrikandthenurses.model;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Order extends AbstractEntity {

    private User user;

    private List<OrderRow> orderRowList;

    private String orderNumber;
    private String status;

    protected Order() {
    }

    public Order(String orderNumber, User user, List<OrderRow> orderRowList) {
        this.user = user;
        this.orderRowList = orderRowList;
        this.orderNumber = orderNumber;
        this.status = "placed";
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

    public void setStatusShipped(){
        this.status = "shipped";
    }

    public void setStatusPaid(){
        this.status = "paid";
    }

    public void setStatusCanceled(){
        this.status = "canceled";
    }

    public String getStatus() {
        return status;
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

