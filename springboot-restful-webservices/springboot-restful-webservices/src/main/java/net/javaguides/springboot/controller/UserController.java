package net.javaguides.springboot.controller;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@AllArgsConstructor

@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    //build create User REST API

    @PostMapping("/createuser")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user)
    {
        UserDto savedUser =userService.createUser(user);
        return  new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


    //build get user by ID
    //http://localhost:8080/api/users/getuser/1
    @GetMapping("/getuser/{id}")
    public  ResponseEntity<User> getUserById(@PathVariable("id") Long userId)
    {
      User user =  userService.getUserById(userId);
        return  new ResponseEntity<>(user, HttpStatus.OK);
    }


    //Build REST API to get all users
    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> getAllUsers()
    {
      List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    //Build Update User REST API
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId, @RequestBody User user)
    {

        user.setId(userId);
      User updatedUser =   userService.updateUser(user);
        return  new ResponseEntity<>(user, HttpStatus.OK);
    }


    //Build Delete User REST API

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId)
    {
        userService.deleteUser(userId);
        return  new ResponseEntity<>("User Deleted Successfully", HttpStatus.OK);
    }

}
