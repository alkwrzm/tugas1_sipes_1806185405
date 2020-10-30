package apap.tugas.sipes.sipes.service;

import apap.tugas.sipes.sipes.model.PesawatTeknisiModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public interface PesawatTeknisiService {

    List<PesawatTeknisiModel> getListPesawatTeknisi();
    public PesawatTeknisiModel addPesawatTeknisi(PesawatTeknisiModel pesawatTeknisi);
}
