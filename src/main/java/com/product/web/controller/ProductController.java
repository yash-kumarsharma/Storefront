package com.product.web.controller;

import com.product.model.Product;
import com.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller // We remove the class-level @RequestMapping to handle multiple base paths
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // --- USER-FACING PRODUCT LIST ---
    /**
     * Handles requests from regular users to see the product list.
     */
    @GetMapping("/user/products")
    public String listUserProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "user/products";
    }

    @GetMapping("/user/products/search")
    public String searchUserProducts(@RequestParam("q") String query, Model model) {
        model.addAttribute("products", productService.searchProducts(query));
        model.addAttribute("searchQuery", query);
        return "user/products";
    }

    // --- ADMIN-FACING PRODUCT MANAGEMENT ---
    /**
     * Handles requests from admins to see the product management view.
     */
    @GetMapping("/admin/products")
    public String listAdminProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "admin/products";
    }

    @GetMapping("/admin/products/search")
    public String searchAdminProducts(@RequestParam("q") String query, Model model) {
        model.addAttribute("products", productService.searchProducts(query));
        model.addAttribute("searchQuery", query);
        return "admin/products";
    }

    @GetMapping("/admin/products/new")
    public String newProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "admin/product-form";
    }

    @GetMapping("/admin/products/edit/{id}")
    public String editProductForm(@PathVariable("id") Long id, Model model) {
        return productService.findById(id)
                .map(product -> {
                    model.addAttribute("product", product);
                    return "admin/product-form";
                })
                .orElse("redirect:/admin/products?error=ProductNotFound");
    }

    @PostMapping("/admin/products")
    public String saveProduct(@Valid @ModelAttribute Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin/product-form";
        }
        productService.save(product);
        return "redirect:/admin/products";
    }

    @PostMapping("/admin/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return "redirect:/admin/products";
    }
}