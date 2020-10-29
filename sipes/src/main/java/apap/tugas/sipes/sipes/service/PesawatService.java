package apap.tugas.sipes.sipes.service;

import apap.tugas.sipes.sipes.model.PenerbanganModel;
import apap.tugas.sipes.sipes.model.PesawatModel;

import java.util.List;

public interface PesawatService {

    void addPesawat(PesawatModel pesawat);

    void deletePesawat(PesawatModel pesawat);

    PesawatModel updatePesawat(PesawatModel pesawat);

    List<PesawatModel> getListPesawat();

    PesawatModel getPesawatById(Long Id);

    void addPenerbangan(PenerbanganModel penerbangan);

    List<PenerbanganModel> getListPenerbangan();

    List<PesawatModel> getListPesawatTua();
}
