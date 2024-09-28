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

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void removeUserById(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> updateUserInfo(Long id) {
        return userRepository.findById(id);
    }

}
