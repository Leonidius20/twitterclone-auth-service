package io.github.leonidius20.twitterclone.authservice.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RegistrationResponse {

    @JsonProperty("token")
    String token;

    @JsonProperty("message")
    String message;

}
