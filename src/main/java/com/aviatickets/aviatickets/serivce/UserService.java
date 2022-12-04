package com.aviatickets.aviatickets.serivce;

import com.aviatickets.aviatickets.models.Role;
import com.aviatickets.aviatickets.models.User;
import com.aviatickets.aviatickets.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOne(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
    }

    @Transactional
    public void update(Integer id, User updatedUser) {
        User userToBeUpdated = userRepository.findById(id).get();
        updatedUser.setId(id);
        updatedUser.setRole(userToBeUpdated.getRole());
        updatedUser.setPassword(userToBeUpdated.getPassword());
        userRepository.save(updatedUser);
    }

    @Transactional
    public void changeRole(Integer id, Role role) {
        User user = userRepository.findById(id).get();
        user.setRole(role);
        userRepository.save(user);
    }

}