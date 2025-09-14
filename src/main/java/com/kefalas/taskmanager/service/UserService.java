package com.kefalas.taskmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kefalas.taskmanager.models.User;
import com.kefalas.taskmanager.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        Optional<User> user = userRepository.findById(Long.valueOf(id));
        return user.orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public boolean deleteUser(int id) {
        if (userRepository.existsById((long) id)) {
            userRepository.deleteById((long) id);
            return true;
        }
        return false;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
