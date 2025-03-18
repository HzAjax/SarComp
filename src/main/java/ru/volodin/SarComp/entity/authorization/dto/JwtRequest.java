package ru.volodin.SarComp.entity.authorization.dto;

import lombok.Data;

@Data
@SuppressWarnings({"unused"})
public class JwtRequest {
    protected String login;
    protected String password;
}
