package med.voll.api.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import med.voll.api.Enums.TypeUsersEnum;

public record CreateUserDto(@NotBlank String login, @NotBlank @Size(min = 5) String password, @NotNull TypeUsersEnum typeUser) {
    
}
