package br.com.wecare.we_care_project.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @Schema(example = "José da Silva")
    @NotBlank(message = "Nome Obrigatório")
    private String name;

    @Schema(example = "12345678910")
    @NotNull(message = "Cpf Obrigatório")
    private long cpf;

    @Schema(example = "josesilva@email.com")
    @NotBlank(message = "Email Obrigatório")
    private String email;

    @NotBlank(message = "Login Obrigatório")
    private String login;

    @NotBlank(message = "Senha Obrigatória")
    private String password;
}
