package com.product.service;

import com.product.model.Order;
import com.product.model.User;
import com.product.repository.OrderRepository;
// Add these new imports for Spring Security
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order save(Order order) {
        if (order.getProducts() == null) {
            order.setProducts(new HashSet<>());
        }
        return orderRepository.save(order);
    }

    public List<Order> findByUser(User user) {
        return orderRepository.findByUser(user);
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    public void cancelOrder(Long orderId) {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
        } else {
            throw new IllegalArgumentException("Order with ID " + orderId + " does not exist.");
        }
    }

    // --- PASTE THE NEW METHOD HERE ---
    public List<Order> findOrdersForCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userEmail;

        if (principal instanceof UserDetails) {
            userEmail = ((UserDetails) principal).getUsername();
        } else {
            userEmail = principal.toString();
        }

        return orderRepository.findByUser_Email(userEmail);
    }

    public long countOrdersForCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userEmail;

        if (principal instanceof UserDetails) {
            userEmail = ((UserDetails) principal).getUsername();
        } else {
            userEmail = principal.toString();
        }

        return orderRepository.countByUser_Email(userEmail);
    }

    public long getTotalOrdersCount() {
        return orderRepository.count();
    }

    public Double getTotalRevenue() {
        Double revenue = orderRepository.sumTotalRevenue();
        return revenue != null ? revenue : 0.0;
    }
}