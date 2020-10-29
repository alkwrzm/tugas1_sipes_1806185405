package apap.tugas.sipes.sipes.repository;

import apap.tugas.sipes.sipes.model.TeknisiModel;
import apap.tugas.sipes.sipes.model.TipeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeknisiDb extends JpaRepository<TeknisiModel, Long> {

}
