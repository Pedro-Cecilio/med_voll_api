package med.voll.api.domain.patient;

import java.time.LocalDate;


public record ResponsePatientDto(String id, String name, String email, String telephone, String cpf, LocalDate birthOfDate, Long weight,  Long address_id) {
    public ResponsePatientDto(Patient patient){
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getTelephone(), patient.getCpf(), patient.getBirthOfDate(), patient.getWeight(), patient.getAddress().getId());
    }
}
