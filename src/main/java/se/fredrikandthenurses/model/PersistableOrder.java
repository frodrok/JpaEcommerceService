package se.fredrikandthenurses.model;

import javax.persistence.*;
import javax.persistence.criteria.Order;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@NamedQueries(value = {
        @NamedQuery(name = "PersistableOrder.FindByUser", query = "SELECT p FROM PersistableOrder p WHERE p.user.id = ?1"),
        @NamedQuery(name = "PersistableOrder.FindByOrderNumber", query = "SELECT p FROM PersistableOrder p WHERE p.orderNumber = ?1")
})
public class PersistableOrder extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;

    @OneToMany(mappedBy = "persistableOrder", cascade = CascadeType.PERSIST)
    private Collection<OrderRow> orderRowList;

    private String orderNumber;

    @Enumerated(EnumType.STRING)
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

    public OrderStatus getStatus() {
        return orderStatus;
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

        PersistableOrder that = (PersistableOrder) o;

        return !(getOrderNumber() != null ? !getOrderNumber().equals(that.getOrderNumber()) : that.getOrderNumber() != null);

    }

    @Override
    public int hashCode() {
        return getOrderNumber() != null ? getOrderNumber().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PersistableOrder{" + "id=" + getId() + ", " +
                "user=" + user +
                ", orderRowList=" + orderRowList +
                ", orderNumber='" + orderNumber + '\'' +
                ", orderStatus=" + orderStatus +
                '}';
    }
}

