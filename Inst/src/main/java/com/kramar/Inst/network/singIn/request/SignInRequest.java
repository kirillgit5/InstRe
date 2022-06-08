package com.kramar.Inst.network.singIn.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class SignInRequest {
    @NotBlank
    @Size(min = 5)
    @Size(max = 40)
    @Schema(description = "login", required = true)
    private String login;

    @NotBlank
    @Size(min = 9)
    @Size(max = 40)
    @Schema(description = "password", required = true)
    private String password;
}
