package io.github.leonidius20.twitterclone.authservice;

import io.github.leonidius20.twitterclone.authservice.dto.requests.RegistrationRequest;
import io.github.leonidius20.twitterclone.authservice.dto.responses.RegistrationResponse;
import io.github.leonidius20.twitterclone.authservice.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    /*@GetMapping("/login")
    public boolean login(String username, String password) {
        return repo.findByUsernameAndPassword(username, password).isPresent();
    }

    @GetMapping("/search")
    public boolean search(String username) {
        return repo.findByUsername(username).isPresent();
    }*/

}
