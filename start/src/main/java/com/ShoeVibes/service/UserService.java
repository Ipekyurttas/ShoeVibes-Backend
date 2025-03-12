package com.ShoeVibes.service;

import com.ShoeVibes.dto.LoginDto;
import com.ShoeVibes.dto.RegisterDto;
import com.ShoeVibes.entity.User;
import com.ShoeVibes.repository.UserRepository;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private OurUserDetailsService ourUserDetailsService;

    // Blacklist işlemi için geçici bir depolama
    private Set<String> blacklistedTokens = new HashSet<>();


    public ResponseEntity<String> registerUser(RegisterDto registerDto) {
        if (userRepository.findByEmail(registerDto.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already in use");
        }
        User user = new User();
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRole(User.Role.USER);
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully");
    }

    public ResponseEntity<String> loginUser(LoginDto loginDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getEmail(),
                            loginDto.getPassword()
                    )
            );

            User user = userRepository.findByEmail(loginDto.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            String role = user.getRole().toString();
            String jwtToken = jwtUtil.generateToken(user.getEmail(), role);

            return ResponseEntity.ok(role+ jwtToken);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
    }

    public ResponseEntity<String> assignAdminRole(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setRole(User.Role.ADMIN);

        userRepository.save(user);

        return ResponseEntity.ok("Role assigned successfully");
    }

    public void logout(String token) {
        // JWT'yi decode et ve geçersiz hale getir
        DecodedJWT decodedJWT = jwtUtil.decodeJWT(token);

        // Eğer JWT geçerliyse, blacklist'e ekle
        if (decodedJWT != null) {
            blacklistedTokens.add(token);
        }
    }

    // Token'ın geçerli olup olmadığını kontrol et
    public boolean isTokenBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }
}