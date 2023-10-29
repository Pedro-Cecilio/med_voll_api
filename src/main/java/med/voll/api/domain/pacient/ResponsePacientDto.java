package med.voll.api.domain.pacient;

import java.time.LocalDate;


public record ResponsePacientDto(String id, String name, String email, String telephone, String cpf, LocalDate birthOfDate, Long weight,  Long address_id) {
    public ResponsePacientDto(Pacient pacient){
        this(pacient.getId(), pacient.getName(), pacient.getEmail(), pacient.getTelephone(), pacient.getCpf(), pacient.getBirthOfDate(), pacient.getWeight(), pacient.getAddress().getId());
    }
}
