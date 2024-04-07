package com.airbnb.controller;

import com.airbnb.dto.LoginDto;
import com.airbnb.dto.PropartyUserDto;
import com.airbnb.entity.PropartyUser;
import com.airbnb.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//      http://localhost:8080/api/v1/users
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //      http://localhost:8080/api/v1/users/addUser
    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody PropartyUserDto dto){
        PropartyUser user = userService.addUser(dto);
        if(user != null){
            return  new ResponseEntity<>("sign up succesful", HttpStatus.CREATED);
        }
        return  new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //      http://localhost:8080/api/v1/users/login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        boolean status = userService.verifyLogin(loginDto);
        if(status){
            return new ResponseEntity<>("User sing in", HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
    }
}
