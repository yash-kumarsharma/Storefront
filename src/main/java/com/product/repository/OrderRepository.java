package com.product.repository;

import com.product.model.Order;
import com.product.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // This method you already have is good.
    List<Order> findByUser(User user);

    /**
     * This is the method your OrderService needs.
     * Spring Data JPA reads this method name and automatically builds a query
     * that finds Orders by looking at the email field of its associated User.
     */
    List<Order> findByUser_Email(String email); // <-- ADD THIS LINE

    long countByUser_Email(String email);

    @org.springframework.data.jpa.repository.Query("SELECT SUM(p.price) FROM Order o JOIN o.products p")
    Double sumTotalRevenue();
}