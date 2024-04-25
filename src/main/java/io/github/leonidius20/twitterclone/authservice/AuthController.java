package io.github.leonidius20.twitterclone.authservice;

import io.github.leonidius20.twitterclone.authservice.dto.requests.RegistrationRequest;
import io.github.leonidius20.twitterclone.authservice.dto.responses.RegistrationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UsersRepository repo;

    @PostMapping("/register")
    public RegistrationResponse register(@RequestBody RegistrationRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                // todo: hash the password
                .passwordHash(request.getPassword())
                .build();

        try {
            user = repo.save(user);
            return new RegistrationResponse(user.getId());
        } catch (Exception e) {
            return new RegistrationResponse("Registration failed. Username already exists or something else happened: " + e.getMessage());
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
