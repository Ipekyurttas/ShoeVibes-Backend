package com.ShoeVibes.controller;

import com.ShoeVibes.dto.LoginDto;
import com.ShoeVibes.dto.RegisterDto;
import com.ShoeVibes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDto registerDto) {
        return userService.registerUser(registerDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto) {
        return userService.loginUser(loginDto);
    }

    @PostMapping("/adminuser/{email}")
    public ResponseEntity<String> assignAdminRole(@PathVariable String email) {
        return userService.assignAdminRole(email);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(400).body("Invalid token");
        }

        String token = authorizationHeader.substring(7);

        userService.logout(token);

        return ResponseEntity.ok("Logged out successfully");
    }
}
