package apap.tugas.sipes.sipes.repository;

import apap.tugas.sipes.sipes.model.TipeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipeDb extends JpaRepository<TipeModel, Long> {

    Optional<TipeModel> findById(Long id);
}
