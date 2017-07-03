package se.fredrikandthenurses.repository;

import se.fredrikandthenurses.model.Product;

public interface ProductRepository extends CrudRepository<Product> {

    Product findByProductNumber(String number);

    Product findByProductName(String name);

}
