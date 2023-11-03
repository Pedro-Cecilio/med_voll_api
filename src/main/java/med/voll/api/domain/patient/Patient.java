package med.voll.api.domain.patient;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.domain.person.Person;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patients")
public class Patient extends Person {
    private LocalDate birthOfDate;
    private Long weight;
    private String cpf;

    public Patient(CreatePatientDto patientDto) {
        this.name = patientDto.name();
        this.email = patientDto.email();
        this.telephone = patientDto.telephone();
        this.cpf = patientDto.cpf();
        this.birthOfDate = patientDto.birthOfDate();
        this.weight = patientDto.weight();
    }

    public Patient(UpdatePatientDto patientDto) {
        this.name = patientDto.name();
        this.telephone = patientDto.telephone();
        this.weight = patientDto.weight();
    }
}
