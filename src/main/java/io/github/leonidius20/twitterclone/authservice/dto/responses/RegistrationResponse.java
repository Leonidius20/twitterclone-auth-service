package io.github.leonidius20.twitterclone.authservice.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class RegistrationResponse {

    String token;

    String message;

}
