package io.github.leonidius20.twitterclone.authservice.controllers;

import io.github.leonidius20.twitterclone.authservice.dto.requests.LoginRequest;
import io.github.leonidius20.twitterclone.authservice.dto.requests.RegistrationRequest;
import io.github.leonidius20.twitterclone.authservice.dto.responses.LoginResponse;
import io.github.leonidius20.twitterclone.authservice.dto.responses.RegistrationResponse;
import io.github.leonidius20.twitterclone.authservice.entities.User;
import io.github.leonidius20.twitterclone.authservice.repositories.UsersRepository;
import io.github.leonidius20.twitterclone.authservice.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UsersRepository repo;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    @PostMapping("/register")
    public RegistrationResponse register(@RequestBody RegistrationRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .build();

        try {
            user = repo.save(user);

            String token = jwtService.generateToken(user);

            return RegistrationResponse.builder().token(token).build();
        } catch (Exception e) {
            return RegistrationResponse.builder().message("Registration failed. Username already exists or something else happened").build();
        }
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        var username = repo.findByUsername(loginRequest.getUsername());

        if (username == null) {
            return LoginResponse.builder().message("Invalid username or/and password").build();
        }

        if (passwordEncoder.matches(loginRequest.getPassword(), username.getPasswordHash())) {
            String token = jwtService.generateToken(username);
            return LoginResponse.builder().token(token).build();
        } else {
            return LoginResponse.builder().message("Invalid username or/and password").build();
        }
    }

}
