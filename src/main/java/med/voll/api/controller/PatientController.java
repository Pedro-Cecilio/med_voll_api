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

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.address.Address;
import med.voll.api.domain.address.AddressRepository;
import med.voll.api.domain.patient.CreatePatientDto;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.domain.patient.ResponsePatientDto;
import med.voll.api.domain.patient.UpdatePatientDto;
import med.voll.api.utils.Utils;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping(value = "api/patients")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;
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
    public ResponseEntity<Page<ResponsePatientDto>> getPatient(@PageableDefault(size = 10, sort = {"name"})Pageable pagination) {
        var patient = this.patientRepository.findAllByIsActiveTrue(pagination).map(ResponsePatientDto::new);
        return ResponseEntity.status(HttpStatus.OK).body(patient);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponsePatientDto> getPatient(@PathVariable String id) {
        var patient = this.patientRepository.findById(id).get();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponsePatientDto(patient));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponsePatientDto> create(@RequestBody @Valid CreatePatientDto createPatient) {
        var patient = new Patient(createPatient);
        var address = new Address(createPatient.address());
        patient.setAddress(address);
        this.patientRepository.save(patient);
        this.addressRepository.save(address);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponsePatientDto(patient));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody UpdatePatientDto updatePatient) {
        var newPatient = new Patient(updatePatient);
        var patient = this.patientRepository.findByIdAndIsActiveTrue(id).get();
        Utils.copyNonNullProperties(newPatient, patient);
        this.patientRepository.save(patient);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponsePatientDto(patient));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        var patient = this.patientRepository.findByIdAndIsActiveTrue(id).get();
        patient.setIsActive(false);
        patient.getAddress().setIsActive(false);
        this.patientRepository.save(patient);
        return ResponseEntity.status(HttpStatus.OK).body("Patient successfully deleted");
    }

    
}
