package med.voll.api.domain.address;

public record ResponseAddressDto(
    Long id,
    String street,
    int number,
    String complement,
    String district,
    String city,
    String uf,
    String cep
) {
    public ResponseAddressDto(Address address) {
        this(address.getId(), address.getStreet(), address.getNumber(), address.getComplement(), address.getDistrict(), address.getCity(), address.getUf(), address.getCep());
    }
}
