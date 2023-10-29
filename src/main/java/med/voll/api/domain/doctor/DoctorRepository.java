package med.voll.api.domain.doctor;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String> {
    Page<Doctor> findAllByIsActiveTrue(Pageable pageable);
    List<Doctor> findAllByIsActiveTrue();


    Optional<Doctor> findByIdAndIsActiveTrue(String id);
}

