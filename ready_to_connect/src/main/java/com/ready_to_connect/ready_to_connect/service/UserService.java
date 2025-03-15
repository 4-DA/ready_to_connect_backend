package com.ready_to_connect.ready_to_connect.service;

import com.ready_to_connect.ready_to_connect.model.User;
import com.ready_to_connect.ready_to_connect.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Transactional
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        return userRepository.save(user);
    }
    
    @Transactional(readOnly = true)
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }
    
    @Transactional
    public User updateUser(UUID id, User updatedUser) {
        return userRepository.findById(id)
            .map(existingUser -> {
                existingUser.setEmail(updatedUser.getEmail());
                existingUser.setPassword(updatedUser.getPassword());
                existingUser.setFullName(updatedUser.getFullName());
                existingUser.setDateOfBirth(updatedUser.getDateOfBirth());
                existingUser.setLocation(updatedUser.getLocation());
                existingUser.setProfilePicture(updatedUser.getProfilePicture());
                existingUser.setUserType(updatedUser.getUserType());
                return userRepository.save(existingUser);
            })
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
    
    @Transactional
    public void deleteUser(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found");
        }
        userRepository.deleteById(id);
    }
}