package med.voll.api.domain.patient;

import java.time.LocalDate;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.address.CreateAddressDto;

public record CreatePatientDto(
    @NotBlank
    String name,
    
    @NotBlank
    @Email
    String email,

    @NotBlank
    String telephone,

    @NotNull
    LocalDate birthOfDate,

    @NotNull
    Long weight,

    @NotBlank
    @Pattern(regexp = "\\d{11}", message = "Formáto do CPF é inválido")
    String cpf,

    @NotNull
    @Valid
    CreateAddressDto address
) {
    
}
