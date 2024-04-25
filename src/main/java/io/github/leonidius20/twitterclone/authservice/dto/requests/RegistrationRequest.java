package io.github.leonidius20.twitterclone.authservice.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

    String username;

    String password;

}
