package net.heartheat.userApp.controller;

import net.heartheat.userApp.service.UserService;
import net.heartheat.userApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

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

    @PutMapping("/update-user/{myId}")
    public boolean updateUser(
            @PathVariable Long myId, @RequestBody User newUser
    ) {
        User old = userService.updateUserInfo(myId).orElse(null);
        if(old != null) {
            old.setUsername(newUser.getUsername() != null && !newUser.getPassword().equals("")
                    ? newUser.getUsername() : old.getUsername());
            old.setPassword(newUser.getPassword() != null && !newUser.getPassword().equals("")
                    ? newUser.getPassword() : old.getPassword());
            old.setDob(newUser.getDob() != null && !newUser.getDob().equals("") ? newUser.getDob()
                    : old.getDob());
            userService.saveUser(old);
        }
        return true;
    }

}
