package com.kramar.Inst.network.singUp.request;

import com.kramar.Inst.core.models.user.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
@Data
@Schema(description = "SingUp")
@Validated
public class SignUpRequest {
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

    @NotBlank
    @Size(min = 2)
    @Size(max = 20)
    @Schema(description = "firstName", required = true)
    private String firstName;

    @NotBlank
    @Size(min = 2)
    @Size(max = 20)
    @Schema(description = "lastName", required = true)
    private String lastName;

    @NotBlank
    @Size(min = 5)
    @Size(max = 20)
    @Schema(description = "nickname", required = true)
    private String nickname;

    @Schema(description = "gender", required = true)
    private String gender;
}
