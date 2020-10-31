package apap.tugas.sipes.sipes.repository;

import apap.tugas.sipes.sipes.model.PesawatModel;
import apap.tugas.sipes.sipes.model.PesawatTeknisiModel;
import apap.tugas.sipes.sipes.model.TeknisiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PesawatTeknisiDb extends JpaRepository<PesawatTeknisiModel, Long> {

    List<PesawatTeknisiModel> findAllByPesawatAndTeknisi(PesawatModel pesawat, TeknisiModel teknisi);
}
