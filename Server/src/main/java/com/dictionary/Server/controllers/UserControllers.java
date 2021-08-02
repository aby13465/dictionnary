package com.dictionary.Server.controllers;

import com.dictionary.Server.payload.request.SigninRequest;

import com.dictionary.Server.payload.request.SignupRequest;
import com.dictionary.Server.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
public class UserControllers {

    private final UserService userService;

    public UserControllers(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@Valid @RequestBody SignupRequest signupRequest) {
        return new ResponseEntity<>(userService.addUser(signupRequest),HttpStatus.CREATED);
    }

    @PostMapping("/find")
    public ResponseEntity<?> findUser(@Valid @RequestBody SigninRequest signinRequest) {
        return new ResponseEntity<>(userService.findUser(signinRequest.getEmail(),signinRequest.getPassword()), HttpStatus.OK);
    }
}
