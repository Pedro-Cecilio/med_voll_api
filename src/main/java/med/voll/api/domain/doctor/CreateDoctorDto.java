package med.voll.api.domain.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.Enums.SpecialtyEnum;
import med.voll.api.domain.address.CreateAddressDto;

public record CreateDoctorDto(
    @NotBlank
    String name,
    
    @NotBlank
    @Email
    String email,

    @NotBlank
    String telephone,

    @NotBlank
    @Pattern(regexp = "\\d{4,6}", message = "Formáto do CRM é inválido")
    String crm,

    @NotNull
    SpecialtyEnum specialty,

    @NotNull
    @Valid
    CreateAddressDto address
) {
    
}
