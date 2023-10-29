package med.voll.api.domain.pacient;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacientRepository extends JpaRepository<Pacient, String> {
    Page<Pacient> findAllByIsActiveTrue(Pageable pageable);
    List<Pacient> findAllByIsActiveTrue();


    Optional<Pacient> findByIdAndIsActiveTrue(String id);
}

