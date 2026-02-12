package com.product.util;

import com.product.model.Product;
import com.product.model.Role;
import com.product.model.User;
import com.product.repository.ProductRepository;
import com.product.repository.RoleRepository;
import com.product.repository.UserRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataInitializer {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProductRepository productRepository;

    public DataInitializer(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, ProductRepository productRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.productRepository = productRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        Role adminRole = roleRepository.findByName("ROLE_ADMIN").orElseGet(() -> roleRepository.save(new Role(null, "ROLE_ADMIN")));
        Role userRole = roleRepository.findByName("ROLE_USER").orElseGet(() -> roleRepository.save(new Role(null, "ROLE_USER")));

        if (userRepository.findByEmail("yash@gmail.com").isEmpty()) {
            User admin = new User();
            admin.setEmail("yash@gmail.com");
            admin.setFullName("Admin User");
            admin.setPassword(passwordEncoder.encode("yash1234"));
            admin.setRoles(Set.of(adminRole));
            userRepository.save(admin);
        }

        if (productRepository.count() == 0) {
            productRepository.save(new Product(null, "Keyboard", "Mechanical keyboard", 49.99, 100));
            productRepository.save(new Product(null, "Mouse", "Wireless mouse", 29.99, 200));
            productRepository.save(new Product(null, "Monitor", "24\" monitor", 149.99, 50));
        }
    }
}
