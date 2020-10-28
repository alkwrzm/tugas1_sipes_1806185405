package apap.tugas.sipes.sipes.repository;

import apap.tugas.sipes.sipes.model.TipeModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipeDb extends JpaRepository<TipeModel, Long> {

    Optional<TipeModel> findById(Long id);

    @Query(value = "SELECT * FROM TIPE",nativeQuery = true)
    List<TipeModel> findAll();
}
