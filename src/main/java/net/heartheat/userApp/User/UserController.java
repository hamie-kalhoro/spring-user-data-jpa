package net.heartheat.userApp.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public boolean createNewUser(@RequestBody User user) {
        userService.saveUser(user);
        return true;
    }

    @GetMapping("/get-all")
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/delete-userbyid/{userId}")
    public boolean deleteUserById(@PathVariable Long userId) {
        userService.removeUserById(userId);
        return true;
    }

}
