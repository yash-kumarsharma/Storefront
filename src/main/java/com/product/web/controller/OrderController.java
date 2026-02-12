package com.product.web.controller;

import com.product.model.Order;
import com.product.model.Product;
import com.product.model.User;
import com.product.service.OrderService;
import com.product.service.ProductService;
import com.product.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;
    private final UserService userService;

    // Update the constructor to inject all needed services
    public OrderController(OrderService orderService, ProductService productService, UserService userService) {
        this.orderService = orderService;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/admin/orders")
    public String listAllOrders(Model model) {
        List<Order> orders = orderService.findAllOrders();
        model.addAttribute("orders", orders);
        return "admin/orders";
    }

    @GetMapping("/user/orders")
    public String listUserOrders(Model model) {
        List<Order> orders = orderService.findOrdersForCurrentUser();
        model.addAttribute("orders", orders);
        return "user/orders";
    }

    // --- NEW METHOD TO PLACE AN ORDER ---
    @PostMapping("/user/order/{productId}")
    public String placeOrder(@PathVariable Long productId,
                             @AuthenticationPrincipal UserDetails userDetails,
                             RedirectAttributes redirectAttributes) {

        // Find the product and the current user
        Product product = productService.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + productId));
        User user = userService.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Create and save the new order
        Order order = new Order();
        order.setUser(user);
        order.setProducts(Collections.singleton(product));
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PLACED");
        orderService.save(order);

        // Add a "flash attribute" for the success message
        redirectAttributes.addFlashAttribute("successMessage", "Order placed successfully!");
        return "redirect:/user/orders";
    }

    // --- NEW METHOD TO CANCEL AN ORDER ---
    @PostMapping("/user/orders/cancel/{orderId}")
    public String cancelOrder(@PathVariable Long orderId, RedirectAttributes redirectAttributes) {
        orderService.cancelOrder(orderId);
        redirectAttributes.addFlashAttribute("successMessage", "Order #" + orderId + " has been canceled.");
        return "redirect:/user/orders";
    }
}