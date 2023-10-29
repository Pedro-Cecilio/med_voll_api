package med.voll.api.domain.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateAddressDto(
    @NotBlank
    String street,

    int number,

    String complement,

    @NotBlank
    String district,
    @NotBlank
    String city,
    @NotBlank
    String uf,
    @NotBlank
    @Pattern(regexp = "\\d{8}", message = "Cep com formato inválido")
    String cep
) {
   
}
