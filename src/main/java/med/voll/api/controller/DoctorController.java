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
import med.voll.api.domain.doctor.CreateDoctorDto;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.doctor.ResponseDoctorDto;
import med.voll.api.domain.doctor.UpdateDoctorDto;
import med.voll.api.utils.Utils;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(value = "/doctors")
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;
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
    public ResponseEntity<Page<ResponseDoctorDto>> getDoctors(@PageableDefault(size = 10, sort = {"name"})Pageable pagination) {
        var doctors = this.doctorRepository.findAllByIsActiveTrue(pagination).map(ResponseDoctorDto::new);
        return ResponseEntity.status(HttpStatus.OK).body(doctors);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseDoctorDto> getDoctor(@PathVariable String id) {
        var doctor = this.doctorRepository.findById(id).get();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDoctorDto(doctor));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseDoctorDto> create(@RequestBody @Valid CreateDoctorDto createDoctor) {
        var doctor = new Doctor(createDoctor);
        var address = new Address(createDoctor.address());
        doctor.setAddress(address);
        this.doctorRepository.save(doctor);
        this.addressRepository.save(address);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDoctorDto(doctor));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody UpdateDoctorDto updateDoctor) {
        var newDoctor = new Doctor(updateDoctor);
        var doctor = this.doctorRepository.findByIdAndIsActiveTrue(id).get();
        Utils.copyNonNullProperties(newDoctor, doctor);
        this.doctorRepository.save(doctor);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDoctorDto(doctor));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        var doctor = this.doctorRepository.findByIdAndIsActiveTrue(id).get();
        doctor.setIsActive(false);
        doctor.getAddress().setIsActive(false);
        this.doctorRepository.save(doctor);
        return ResponseEntity.status(HttpStatus.OK).body("Doctor successfully deleted");
    }

    
}
