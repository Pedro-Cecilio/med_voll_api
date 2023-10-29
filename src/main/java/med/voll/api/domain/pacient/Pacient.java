package med.voll.api.domain.pacient;

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
@Table(name = "pacient")
public class Pacient extends Person {
    private LocalDate birthOfDate;
    private Long weight;
    private String cpf;

    public Pacient(CreatePacientDto pacientDto) {
        this.name = pacientDto.name();
        this.email = pacientDto.email();
        this.telephone = pacientDto.telephone();
        this.cpf = pacientDto.cpf();
        this.birthOfDate = pacientDto.birthOfDate();
        this.weight = pacientDto.weight();
    }

    public Pacient(UpdatePacientDto pacientDto) {
        this.name = pacientDto.name();
        this.telephone = pacientDto.telephone();
        this.weight = pacientDto.weight();
    }
}
