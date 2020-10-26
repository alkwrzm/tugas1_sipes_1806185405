package apap.tugas.sipes.sipes.repository;

import apap.tugas.sipes.sipes.model.PesawatModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PesawatDb extends JpaRepository<PesawatModel, Long> {

    Optional<PesawatModel> findById(Long id);
}
