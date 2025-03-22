package ru.volodin.SarComp.entity.authorization.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@SuppressWarnings({"unused"})
public class JwtRequest {
    protected String login;
    protected String password;
}
