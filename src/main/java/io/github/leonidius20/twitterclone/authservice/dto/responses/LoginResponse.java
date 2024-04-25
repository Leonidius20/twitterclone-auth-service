package io.github.leonidius20.twitterclone.authservice.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
public class LoginResponse {

    @JsonProperty("token")
    String token;

    @JsonProperty("message")
    String message;

}
