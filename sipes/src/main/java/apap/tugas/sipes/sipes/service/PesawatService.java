package apap.tugas.sipes.sipes.service;

import apap.tugas.sipes.sipes.model.PenerbanganModel;
import apap.tugas.sipes.sipes.model.PesawatModel;
import apap.tugas.sipes.sipes.model.TeknisiModel;

import java.util.List;

public interface PesawatService {

    void addPesawat(PesawatModel pesawat);

    void deletePesawat(PesawatModel pesawat);

    PesawatModel updatePesawat(PesawatModel pesawat);

    List<PesawatModel> getListPesawat();

    PesawatModel getPesawatById(Long id);

    void addPenerbangan(PenerbanganModel penerbangan);

    List<PenerbanganModel> getListPenerbangan();

    List<PesawatModel> getListPesawatTua();

    void setUsia(List<PesawatModel> pesawat);

    public String generateNomorSeri(PesawatModel pesawat);

    PesawatModel getPesawatByNomorSeri(String nomorSeri);


}
