package apap.tugas.sipes.sipes.service;

import apap.tugas.sipes.sipes.model.PenerbanganModel;
import apap.tugas.sipes.sipes.model.PesawatModel;

import java.util.List;

public interface PenerbanganService {

    void addPenerbangan(PenerbanganModel penerbangan);

    void deletePenerbangan(PenerbanganModel penerbangan);

    PenerbanganModel updatePenerbangan(PenerbanganModel penerbangan);

    List<PenerbanganModel> getListPenerbangan();

    PenerbanganModel getPenerbanganById(Long id);

    PenerbanganModel addPesawat(PesawatModel pesawatModel, PenerbanganModel penerbanganModel);
}
