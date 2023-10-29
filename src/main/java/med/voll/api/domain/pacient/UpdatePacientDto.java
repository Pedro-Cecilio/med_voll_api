package med.voll.api.domain.pacient;


public record UpdatePacientDto(
    String name,
    String telephone,
    Long weight

) {

}
