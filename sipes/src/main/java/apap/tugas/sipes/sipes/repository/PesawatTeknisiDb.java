package apap.tugas.sipes.sipes.repository;

import apap.tugas.sipes.sipes.model.PesawatTeknisiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PesawatTeknisiDb extends JpaRepository<PesawatTeknisiModel, Long> {
}
