package se.fredrikandthenurses.service;

import se.fredrikandthenurses.model.OrderStatus;
import se.fredrikandthenurses.model.PersistableOrder;
import se.fredrikandthenurses.model.Product;
import se.fredrikandthenurses.model.User;
import se.fredrikandthenurses.repository.OrderRepository;
import se.fredrikandthenurses.repository.ProductRepository;
import se.fredrikandthenurses.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

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

    public Product addProduct(Product product) {
        return productRepo.saveOrUpdate(product);
    }

    public Product findByProductNumber(String productNumber) {
        return productRepo.findByProductNumber(productNumber);
    }

    public Product findByProductName(String productName) {
        return productRepo.findByProductName(productName);
    }

    public User addUser(User user) {
        return userRepo.saveOrUpdate(user);
    }

    public User findUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepo.getAll();
    }

    public List<Product> getAllProducts() {
        return productRepo.getAll();
    }

    public PersistableOrder addOrder(PersistableOrder persistableOrder) {
        return orderRepo.saveOrUpdate(persistableOrder);
    }

    public List<PersistableOrder> getAllOrders() {
        return orderRepo.getAll();
    }

    public List<PersistableOrder> findOrdersByUser(User user) {
        return new ArrayList<>(orderRepo.findByUser(user));
    }

    public List<PersistableOrder> findOrdersByStatus(OrderStatus status) {
        return new ArrayList<>(orderRepo.findOrdersByStatus(status));
    }

    public List<PersistableOrder> findOrdersByMinimumPrice(double price) {
        return new ArrayList<>(orderRepo.findByMinimumPrice(price));
    }
}
