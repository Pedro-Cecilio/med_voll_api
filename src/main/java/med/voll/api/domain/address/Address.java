package med.voll.api.domain.address;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private int number;
    private String complement;
    private String district;
    private String city;
    private String uf;
    private String cep;
    private Boolean isActive = true;

    public Address(CreateAddressDto addressDto) {
        this.street = addressDto.street();
        this.number = addressDto.number();
        this.complement = addressDto.complement();
        this.district = addressDto.district();
        this.city = addressDto.city();
        this.uf = addressDto.uf();
        this.cep = addressDto.cep();
    }
    public Address(UpdateAddressDto addressDto) {
        this.street = addressDto.street();
        this.number = addressDto.number();
        this.complement = addressDto.complement();
        this.district = addressDto.district();
        this.city = addressDto.city();
        this.uf = addressDto.uf();
        this.cep = addressDto.cep();
    }
    
}
