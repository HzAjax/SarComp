package ru.volodin.SarComp.entity.authorization.dto;

import lombok.Data;

@Data
@SuppressWarnings({"unused"})
public class JwtRefreshRequest {
    protected String refreshToken;
}
