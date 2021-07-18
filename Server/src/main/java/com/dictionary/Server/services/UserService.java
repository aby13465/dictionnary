package com.dictionary.Server.services;

import com.dictionary.Server.models.User;
import com.dictionary.Server.models.UserType;
import com.dictionary.Server.payload.request.SignupRequest;
import com.dictionary.Server.payload.response.JwtResponse;
import com.dictionary.Server.payload.response.MessageResponse;
import com.dictionary.Server.repository.UserRepository;
import com.dictionary.Server.security.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private TokenUtil tokenUtil;

    @Autowired
    public UserService(UserRepository userRepository, TokenUtil tokenUtil) {
        this.userRepository = userRepository;
        this.tokenUtil = tokenUtil;
    }

    public Object addUser(SignupRequest signupRequest) {
        User user = userRepository.findUserByEmail(signupRequest.getEmail());
        if (user == null) {
            user = new User();
            user.setType(UserType.normal);
            user.setCreationDate(LocalDate.now());
            user.setPassword(this.passwordEncoder.encode(signupRequest.getPassword()));
            user.setEmail(signupRequest.getEmail());
            return new JwtResponse(tokenUtil.generateToken(this.userRepository.save(user)));
        }
        return new MessageResponse("Email Exists.");
    }

    public Object findUser(String email, String password) {
        User user = userRepository.findUserByEmail(email);
        if (user == null)
            return new MessageResponse("User doesn't exist");
        String token = tokenUtil.generateToken(user);
        if (passwordEncoder.matches(password, user.getPassword())) {
            return new JwtResponse(token);
        }
        return new MessageResponse("Wrong Password.");
    }
}