package se.fredrikandthenurses.model;

import javax.persistence.Entity;

@Entity
public class Product extends AbstractEntity {

    private String productNumber;
    private String productName;
    private Double productPrice;

    protected Product() {}

    public Product(String productNumber, String productName, Double productPrice) {
        this.productNumber = productNumber;
        this.productName = productName;
        this.productPrice = productPrice;
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

        if (getProductNumber() != null ? !getProductNumber().equals(product.getProductNumber()) : product.getProductNumber() != null)
            return false;
        if (getProductName() != null ? !getProductName().equals(product.getProductName()) : product.getProductName() != null)
            return false;
        return !(getProductPrice() != null ? !getProductPrice().equals(product.getProductPrice()) : product.getProductPrice() != null);

    }

    @Override
    public int hashCode() {
        int result = getProductNumber() != null ? getProductNumber().hashCode() : 0;
        result = 31 * result + (getProductName() != null ? getProductName().hashCode() : 0);
        result = 31 * result + (getProductPrice() != null ? getProductPrice().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productNumber='" + productNumber + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}
