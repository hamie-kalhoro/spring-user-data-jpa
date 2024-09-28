package net.heartheat.userApp.controller;

import net.heartheat.userApp.service.UserService;
import net.heartheat.userApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<User> createNewUser(@RequestBody User user) {
        User created = userService.saveUser(user);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<User>> getAll() {
        List<User> allUsers = userService.getAllUsers();
        if (!allUsers.isEmpty()) {
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("get-userbyid/{userid}")
    public ResponseEntity<?> getUserById(@PathVariable Long userid) {
        Optional<User> userById = userService.findUserById(userid);
        if(userById.isPresent()) {
            return new ResponseEntity<>(userById, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete-userbyid/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long userId) {
        userService.removeUserById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update-user/{myId}")
    public ResponseEntity<?> updateUser(
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
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
