package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import med.voll.api.domain.address.Address;
import med.voll.api.domain.address.AddressRepository;
import med.voll.api.domain.address.ResponseAddressDto;
import med.voll.api.domain.address.UpdateAddressDto;
import med.voll.api.utils.Utils;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping(value = "api/address")
public class AddressController {
    @Autowired
    private AddressRepository addressRepository;

    @GetMapping
    public ResponseEntity<Page<ResponseAddressDto>> getAddress(Pageable pagination) {
        var results = this.addressRepository.findAllByIsActiveTrue(pagination).map(ResponseAddressDto::new);
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getAddressById(@PathVariable Long id) {
        var address = this.addressRepository.findByIdAndIsActiveTrue(id);
         if(address.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseAddressDto(address.get()));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody UpdateAddressDto updateAddressDto) {
        var address = this.addressRepository.findByIdAndIsActiveTrue(id);
        if(address.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address not found");
 
        }
        var newAddress = new Address(updateAddressDto);

        Utils.copyNonNullProperties(newAddress, address.get());
        this.addressRepository.save(address.get());
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseAddressDto(address.get()));

    }
}
