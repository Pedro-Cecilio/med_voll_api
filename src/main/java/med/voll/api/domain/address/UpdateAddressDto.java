package med.voll.api.domain.address;

import jakarta.validation.constraints.Pattern;

public record UpdateAddressDto(
    String street,

    int number,

    String complement,

    String district,

    String city,

    String uf,

    @Pattern(regexp = "\\d{8}", message = "Cep com formato inválido")
    String cep
) {
    
}
