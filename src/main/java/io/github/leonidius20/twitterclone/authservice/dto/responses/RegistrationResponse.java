package io.github.leonidius20.twitterclone.authservice.dto.responses;

public class RegistrationResponse {

    Integer id;

    String message;

    public RegistrationResponse(int id) {
        this.id = id;
        this.message = "User registered successfully";
    }

    public RegistrationResponse(String message) {
        this.id = null;
        this.message = message;
    }

}
