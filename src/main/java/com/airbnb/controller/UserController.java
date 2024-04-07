package com.airbnb.controller;

import com.airbnb.dto.JWTResponse;
import com.airbnb.dto.LoginDto;
import com.airbnb.dto.PropartyUserDto;
import com.airbnb.entity.PropartyUser;
import com.airbnb.service.UserService;
import com.auth0.jwt.JWT;
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
    public ResponseEntity<? > login(@RequestBody LoginDto loginDto) {
        String status = userService.verifyLogin(loginDto);
        String jwtToken = userService.verifyLogin(loginDto);

        if (jwtToken != null) {
            JWTResponse jwtResponse = new JWTResponse();
            jwtResponse.setToken(jwtToken);
            return new ResponseEntity<>(jwtResponse,HttpStatus.OK);
            }
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }

}
