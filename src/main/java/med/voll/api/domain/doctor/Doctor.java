package med.voll.api.domain.doctor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.Enums.SpecialtyEnum;
import med.voll.api.domain.person.Person;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doctors")
public class Doctor extends Person {

    @Column(unique = true)
    private String crm;

    @Enumerated(EnumType.STRING)
    private SpecialtyEnum specialty;

    public Doctor(CreateDoctorDto doctorDto) {
        this.name = doctorDto.name();
        this.email = doctorDto.email();
        this.telephone = doctorDto.telephone();
        this.crm = doctorDto.crm();
        this.specialty = doctorDto.specialty();
    }

    public Doctor(UpdateDoctorDto doctorDto) {
        this.name = doctorDto.name();
        this.telephone = doctorDto.telephone();
    }
}
