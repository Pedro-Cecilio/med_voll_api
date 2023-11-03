package med.voll.api.domain.patient;


public record UpdatePatientDto(
    String name,
    String telephone,
    Long weight

) {

}
