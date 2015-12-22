package se.fredrikandthenurses.repository;

import se.fredrikandthenurses.model.Product;

import javax.persistence.EntityManagerFactory;

public class JpaProductRepository extends AbstractJpaRepository<Product> implements ProductRepository {

    public JpaProductRepository(EntityManagerFactory emf) {
        super(emf, Product.class);
    }

    @Override
    public Product findByProductNumber(String number) {
        return query("Product.FindByProductNumber", number);
    }

    @Override
    public Product findByProductName(String name) {
        return query("Product.FindByProductName", name);
    }
}
