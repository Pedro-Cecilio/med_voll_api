package med.voll.api.domain.doctor;

import med.voll.api.Enums.SpecialtyEnum;

public record ResponseDoctorDto(String id, String name, String email, String telephone, String crm, SpecialtyEnum specialty, Long address_id) {
    public ResponseDoctorDto(Doctor doctor){
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getTelephone(), doctor.getCrm(), doctor.getSpecialty(), doctor.getAddress().getId());
    }
}
