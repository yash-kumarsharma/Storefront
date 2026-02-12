package com.product.service;

import com.product.model.Role;
import com.product.model.User;
import com.product.repository.RoleRepository;
import com.product.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service for managing users and user-role assignments.
 */
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Return all users.
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Find user by id.
     */
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Find user by email.
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Create a new user. Password will be encoded. If no roles provided, assigns
     * ROLE_USER by default.
     */
    public User createUser(User user) {
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        // Check if email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered. Please use a different email or login.");
        }

        // encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // ensure roles
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            Role defaultRole = roleRepository.findByName("ROLE_USER")
                    .orElseGet(() -> roleRepository.save(new Role(null, "ROLE_USER")));
            Set<Role> roles = new HashSet<>();
            roles.add(defaultRole);
            user.setRoles(roles);
        }

        return userRepository.save(user);
    }

    /**
     * Update an existing user. If password is provided (non-empty), it will be
     * encoded and updated.
     * Otherwise the existing password is preserved.
     */
    public User updateUser(User updated) {
        User existing = userRepository.findById(updated.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + updated.getId()));

        // update fields
        if (updated.getEmail() != null)
            existing.setEmail(updated.getEmail());
        if (updated.getFullName() != null)
            existing.setFullName(updated.getFullName());

        if (updated.getPassword() != null && !updated.getPassword().isBlank()) {
            existing.setPassword(passwordEncoder.encode(updated.getPassword()));
        }

        if (updated.getRoles() != null && !updated.getRoles().isEmpty()) {
            existing.setRoles(updated.getRoles());
        }

        return userRepository.save(existing);
    }

    /**
     * Delete user by id.
     */
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Adds a role to a user (creates role if it doesn't exist).
     */
    public User addRoleToUser(String userEmail, String roleName) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userEmail));

        Role role = roleRepository.findByName(roleName)
                .orElseGet(() -> roleRepository.save(new Role(null, roleName)));

        Set<Role> roles = user.getRoles();
        if (roles == null) {
            roles = new HashSet<>();
        }
        roles.add(role);
        user.setRoles(roles);

        return userRepository.save(user);
    }

    /**
     * Removes a role from a user.
     */
    public User removeRoleFromUser(String userEmail, String roleName) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userEmail));

        user.getRoles().removeIf(r -> r.getName().equals(roleName));
        return userRepository.save(user);
    }

    public long getTotalUsersCount() {
        return userRepository.count();
    }
}
