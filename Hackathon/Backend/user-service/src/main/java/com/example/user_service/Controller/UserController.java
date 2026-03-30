package com.example.user_service.Controller;


import com.example.user_service.Entity.User;
import com.example.user_service.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PostMapping("/login")
    public User login(@RequestParam String email, @RequestParam String password){
        return userService.login(email,password);
    }


    @GetMapping("/read")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/read/{id}")
    public User getUserById(@PathVariable Long id){
        return  userService.getUserById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public User update(@PathVariable Long id,
                       @RequestBody User user) {
        return userService.update(id, user);
    }

    // DELETE BY ID
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return userService.deleteById(id);
    }
}

