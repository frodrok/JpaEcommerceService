package se.fredrikandthenurses.service;

import se.fredrikandthenurses.model.Product;
import se.fredrikandthenurses.repository.OrderRepository;
import se.fredrikandthenurses.repository.ProductRepository;
import se.fredrikandthenurses.repository.UserRepository;

import java.sql.PreparedStatement;

/**
 * Created by joanne on 21/12/15.
 */
public final class ECommerceService {

    private final OrderRepository orderRepo;
    private final UserRepository userRepo;
    private final ProductRepository productRepo;

    public ECommerceService(OrderRepository orderRepo, UserRepository userRepo, ProductRepository productRepo) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
    }


    public void addProduct(Product product) {
        productRepo.saveOrUpdate(product);
    }

    public Product findByProductNumber(String productNumber) {
        return productRepo.findByProductNumber(productNumber);
    }
}