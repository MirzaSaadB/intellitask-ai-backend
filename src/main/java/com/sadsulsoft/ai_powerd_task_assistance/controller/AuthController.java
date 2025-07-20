package com.sadsulsoft.ai_powerd_task_assistance.controller;

import com.sadsulsoft.ai_powerd_task_assistance.dto.JwtAuthenticationResponse;
import com.sadsulsoft.ai_powerd_task_assistance.dto.LoginRequest;
import com.sadsulsoft.ai_powerd_task_assistance.dto.SignUpRequest;
import com.sadsulsoft.ai_powerd_task_assistance.entity.Role;
import com.sadsulsoft.ai_powerd_task_assistance.entity.User;
import com.sadsulsoft.ai_powerd_task_assistance.repository.UserRepository;
import com.sadsulsoft.ai_powerd_task_assistance.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/signup")
    public String signup(@RequestBody SignUpRequest signUpRequest) {
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(Role.USER); // Set default role for new users

        userRepository.save(user);

        return "User registered successfully!";
    }
    
    @PostMapping("/login")
    public JwtAuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        
        UserDetails user = userRepository.findByUsername(loginRequest.getUsername())
                                        .orElseThrow(() -> new IllegalArgumentException("Invalid username or password."));
        
        String token = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(token);
    }
}