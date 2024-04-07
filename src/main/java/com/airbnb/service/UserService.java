package com.airbnb.service;

import com.airbnb.dto.LoginDto;
import com.airbnb.dto.PropartyUserDto;
import com.airbnb.entity.PropartyUser;
import com.airbnb.repository.PropartyUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private PropartyUserRepository userRepository;
    private JWTService jwtService;

    public UserService(PropartyUserRepository userRepository, JWTService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }


    public PropartyUser addUser(PropartyUserDto dto){
        PropartyUser user = new PropartyUser();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUserName(dto.getUserName());
        user.setEmail(dto.getEmail());
        user.setUserRole(dto.getUserRole());
        user.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt(10)));
        userRepository.save(user);
        return  user;
    }

    public String verifyLogin(LoginDto loginDto) {
        Optional<PropartyUser> opUser = userRepository.findByUserName(loginDto.getUserName());
        if(opUser.isPresent()){
            PropartyUser user = opUser.get();
            if (BCrypt.checkpw(loginDto.getPassword(), user.getPassword())){
               return jwtService.generateToken(user);
            }
        }
        return null;
    }
}
