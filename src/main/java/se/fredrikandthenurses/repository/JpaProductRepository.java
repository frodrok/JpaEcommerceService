package se.fredrikandthenurses.repository;

import se.fredrikandthenurses.model.Product;

import javax.persistence.EntityManagerFactory;

public class JpaProductRepository extends AbstractJpaRepository<Product> implements ProductRepository {

    public JpaProductRepository(EntityManagerFactory emf, Class<Product> entityClass) {
        super(emf, entityClass);
    }

    @Override
    public Product findByProductNumber(String number) {
        return query("Product.FindByProductNumber").setParameter("productNumber", number).getSingleResult();
    }

    @Override
    public Product findByProductName(String name) {
        return query("Product.FindByProductName").setParameter("productName", name).getSingleResult();
    }
}
