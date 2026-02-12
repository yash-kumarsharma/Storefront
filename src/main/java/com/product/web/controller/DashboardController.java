package com.product.web.controller;

// Make sure OrderService is imported
import com.product.service.OrderService;
import com.product.service.ProductService;
import com.product.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    // 1. Add a field for the OrderService
    private final OrderService orderService;
    private final UserService userService;
    private final ProductService productService;

    public DashboardController(OrderService orderService, UserService userService, ProductService productService) {
        this.orderService = orderService;
        this.userService = userService;
        this.productService = productService;
    }

    /**
     * Handles requests for the admin dashboard.
     */
    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("totalUsers", userService.getTotalUsersCount());
        model.addAttribute("totalProducts", productService.getTotalProductsCount());
        model.addAttribute("totalOrders", orderService.getTotalOrdersCount());
        model.addAttribute("totalRevenue", orderService.getTotalRevenue());
        return "admin/dashboard";
    }

    /**
     * Handles requests for the user dashboard.
     */
    @GetMapping("/user/dashboard")
    public String userDashboard(Model model) {
        // 3. Call the service method and add the result to the model
        long count = orderService.countOrdersForCurrentUser();
        model.addAttribute("orderCount", count);

        return "user/dashboard";
    }
}