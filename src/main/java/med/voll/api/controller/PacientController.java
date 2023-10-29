package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.address.Address;
import med.voll.api.domain.address.AddressRepository;
import med.voll.api.domain.pacient.CreatePacientDto;
import med.voll.api.domain.pacient.Pacient;
import med.voll.api.domain.pacient.PacientRepository;
import med.voll.api.domain.pacient.ResponsePacientDto;
import med.voll.api.domain.pacient.UpdatePacientDto;
import med.voll.api.utils.Utils;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(value = "api/Pacients")
public class PacientController {
    @Autowired
    private PacientRepository pacientRepository;
    @Autowired
    private AddressRepository addressRepository;

    /*
     * Através do Pagable do spring é possível gerenciar paginação/ordenação por
     * meio das query params:
     * size=integer --> Define quantos elementos por pagina terão uma página
     * page=integer --> Define a pagina
     * sort=atributo -> Define o atributo quer será usado como referência para
     * ordenar de forma crescente
     * sort=atributo,desc -> odena de forma decrescente a partir de um atributo
     */
    @GetMapping
    public ResponseEntity<Page<ResponsePacientDto>> getPacient(@PageableDefault(size = 10, sort = {"name"})Pageable pagination) {
        var pacient = this.pacientRepository.findAllByIsActiveTrue(pagination).map(ResponsePacientDto::new);
        return ResponseEntity.status(HttpStatus.OK).body(pacient);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponsePacientDto> getPacient(@PathVariable String id) {
        var pacient = this.pacientRepository.findById(id).get();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponsePacientDto(pacient));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponsePacientDto> create(@RequestBody @Valid CreatePacientDto createPacient) {
        var pacient = new Pacient(createPacient);
        var address = new Address(createPacient.address());
        pacient.setAddress(address);
        this.pacientRepository.save(pacient);
        this.addressRepository.save(address);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponsePacientDto(pacient));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody UpdatePacientDto updatePacient) {
        var newPacient = new Pacient(updatePacient);
        var pacient = this.pacientRepository.findByIdAndIsActiveTrue(id).get();
        Utils.copyNonNullProperties(newPacient, pacient);
        this.pacientRepository.save(pacient);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponsePacientDto(pacient));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        var pacient = this.pacientRepository.findByIdAndIsActiveTrue(id).get();
        pacient.setIsActive(false);
        pacient.getAddress().setIsActive(false);
        this.pacientRepository.save(pacient);
        return ResponseEntity.status(HttpStatus.OK).body("Pacient successfully deleted");
    }

    
}
