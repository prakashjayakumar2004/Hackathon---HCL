package com.example.user_service.Service;

import com.example.user_service.Entity.User;
import com.example.user_service.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public User addUser(User user){

        User existingUser = userRepo.findByEmail(user.getEmail());
        if(existingUser!=null){
            throw new RuntimeException("user already exist");
        }
        return userRepo.save(user);
    }

    public User login(String email, String password) {
        User user=userRepo.findByEmail(email);
        if(user == null){
            throw new RuntimeException("user not found");
        }
        if(!user.getPassword().equals(password)){
            throw new RuntimeException("password does not match");
        }
        return user;
    }

    public User getUserById(Long id){
        return userRepo.findById(id).orElseThrow(()->new RuntimeException("User not found"));
    }

    public List<User> getAllUser(){
        return userRepo.findAll();
    }

    public User update(Long id, User updatedUser) {
        User user = getUserById(id);

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());

        return userRepo.save(user);
    }

    // DELETE BY ID
    public String deleteById(Long id) {
        userRepo.deleteById(id);
        return "User deleted successfully";
    }
}

