package se.fredrikandthenurses.model;

import javax.persistence.*;
import javax.persistence.criteria.Order;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@NamedQueries(value = {
        @NamedQuery(name = "PersistableOrder.FindByUser", query = "SELECT o FROM PersistableOrder o WHERE o.user.id = ?1"),
        @NamedQuery(name = "PersistableOrder.FindByOrderNumber", query = "SELECT o FROM PersistableOrder o WHERE o.orderNumber = ?1")
})
public class PersistableOrder extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;

    @OneToMany(mappedBy = "persistableOrder")
    private Collection<OrderRow> orderRowList;

    private String orderNumber;
    private String status;

    private OrderStatus orderStatus;

    protected PersistableOrder() {
    }

    public PersistableOrder(String orderNumber, User user) {
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

    public PersistableOrder addOrderRow(OrderRow row){
        this.orderRowList.add(row);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersistableOrder persistableOrder = (PersistableOrder) o;

        if (user != null ? !user.equals(persistableOrder.user) : persistableOrder.user != null) return false;
        if (orderRowList != null ? !orderRowList.equals(persistableOrder.orderRowList) : persistableOrder.orderRowList != null) return false;
        if (orderNumber != null ? !orderNumber.equals(persistableOrder.orderNumber) : persistableOrder.orderNumber != null) return false;
        return status != null ? status.equals(persistableOrder.status) : persistableOrder.status == null;

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
        return "PersistableOrder{" + getId() +
                "user=" + user +
                ", orderRowList=" + orderRowList +
                ", orderNumber='" + orderNumber + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

