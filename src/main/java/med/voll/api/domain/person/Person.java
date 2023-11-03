package med.voll.api.domain.person;


import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import med.voll.api.domain.address.Address;
import med.voll.api.domain.user.User;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected String id;

    @OneToOne(cascade = CascadeType.REMOVE)
    protected Address address;

    @OneToOne(cascade = CascadeType.REMOVE)
    protected User user;

    protected String name;
   
    @Column(unique = true)
    protected String email;

    @Column(nullable = false)
    protected String telephone;


    @Column()
    protected Boolean isActive = true;
}
