package ru.volodin.SarComp.entity.authorization.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@SuppressWarnings({"unused"})
public class JwtResponse {
    protected String accessToken;
    protected String refreshToken;
}
