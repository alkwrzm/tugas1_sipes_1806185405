package apap.tugas.sipes.sipes.repository;

import apap.tugas.sipes.sipes.model.TeknisiModel;
import apap.tugas.sipes.sipes.model.TipeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeknisiDb extends JpaRepository<TeknisiModel, Long> {

    Optional<TeknisiModel> findById(Long id);

    @Query(value = "SELECT * FROM TEKNISI",nativeQuery = true)
    List<TeknisiModel> findAll();
}
