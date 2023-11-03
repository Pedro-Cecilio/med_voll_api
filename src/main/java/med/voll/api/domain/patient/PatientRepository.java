package med.voll.api.domain.patient;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {
    Page<Patient> findAllByIsActiveTrue(Pageable pageable);
    List<Patient> findAllByIsActiveTrue();


    Optional<Patient> findByIdAndIsActiveTrue(String id);
}

