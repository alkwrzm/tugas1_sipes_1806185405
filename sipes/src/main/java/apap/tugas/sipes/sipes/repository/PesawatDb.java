package apap.tugas.sipes.sipes.repository;

import apap.tugas.sipes.sipes.model.PesawatModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PesawatDb extends JpaRepository<PesawatModel, Long> {

    Optional<PesawatModel> findById(Long id);

    @Query(value = "SELECT * FROM PESAWAT WHERE (extract(year from CURRENT_TIMESTAMP) - " +
            "extract(year from tanggal_dibuat)) > 9  ",nativeQuery = true)
    List<PesawatModel> findByTanggalDibuat();

    PesawatModel findPesawatModelByNomorSeri(String nomorSeri);

}
