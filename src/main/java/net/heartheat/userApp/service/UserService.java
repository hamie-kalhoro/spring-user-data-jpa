package net.heartheat.userApp.service;

import net.heartheat.userApp.entity.User;
import net.heartheat.userApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Long id) {
         return userRepository.findById(id);
    }

    public void removeUserById(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> updateUserInfo(Long id) {
        return userRepository.findById(id);
    }

}
