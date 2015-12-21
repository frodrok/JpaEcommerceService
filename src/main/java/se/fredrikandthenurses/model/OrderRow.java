package se.fredrikandthenurses.model;

import javax.persistence.Entity;

@Entity
public class OrderRow extends AbstractEntity {

    private Product product;
    private Integer amount;

    public OrderRow(Product product, Integer amount) {
        this.product = product;
        this.amount = amount;
    }

    protected OrderRow() {}
}
