package se.fredrikandthenurses.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries(value = {
        @NamedQuery(name = "Product.FindByProductNumber", query = "SELECT p from Product p where p.productNumber = :productNumber"),
        @NamedQuery(name = "Product.FindByProductName", query = "SELECT p from Product p where p.productName = :productName")
        })
public class Product extends AbstractEntity {

    private String productNumber;
    private String productName;
    private Double productPrice;
    private boolean available;

    protected Product() {}

    public Product(String productNumber, String productName, Double productPrice) {
        this.productNumber = productNumber;
        this.productName = productName;
        this.productPrice = productPrice;
        this.available = true;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public String getProductName() {
        return productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (available != product.available) return false;
        if (productNumber != null ? !productNumber.equals(product.productNumber) : product.productNumber != null)
            return false;
        if (productName != null ? !productName.equals(product.productName) : product.productName != null) return false;
        return productPrice != null ? productPrice.equals(product.productPrice) : product.productPrice == null;

    }

    @Override
    public int hashCode() {
        int result = productNumber != null ? productNumber.hashCode() : 0;
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (productPrice != null ? productPrice.hashCode() : 0);
        result = 31 * result + (available ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productNumber='" + productNumber + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", available=" + available +
                '}';
    }
}
