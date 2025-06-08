package ru.shop.project.diplom.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shop.project.diplom.models.User;
import ru.shop.project.diplom.repositories.UserRepository;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public int getDeposit(User user) {
        return userRepository.findById(user.getId())
                .map(User::getDeposit)
                .orElse(0);
    }

    @Transactional
    public void addDeposit(User user, int amount) {
        user.setDeposit(user.getDeposit() + amount);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("User not found with login: " + login));
    }

    @Transactional
    public void updateFunds(User user) {
        userRepository.updateById(user);
    }
}
