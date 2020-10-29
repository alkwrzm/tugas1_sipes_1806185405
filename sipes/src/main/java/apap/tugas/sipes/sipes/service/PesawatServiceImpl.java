package apap.tugas.sipes.sipes.service;

import apap.tugas.sipes.sipes.model.PenerbanganModel;
import apap.tugas.sipes.sipes.model.PesawatModel;
import apap.tugas.sipes.sipes.repository.PesawatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PesawatServiceImpl implements PesawatService{
    @Autowired
    PesawatDb pesawatDb;

    public PesawatServiceImpl(){super();}

    @Override
    public void addPesawat(PesawatModel pesawat) {
        pesawatDb.save(pesawat);

    }

    @Override
    public void deletePesawat(PesawatModel pesawat) {

    }

    @Override
    public PesawatModel updatePesawat(PesawatModel pesawat) {
        return null;
    }

    @Override
    public List<PesawatModel> getListPesawat() {
        return null;
    }

    @Override
    public PesawatModel getPesawatById(Long Id) {
        return null;
    }

    @Override
    public List<PenerbanganModel> getListPenerbangan() {
        return null;
    }

    @Override
    public List<PesawatModel> getListPesawatTua() {
        return pesawatDb.findByTanggalDibuat();
    }

    @Override
    public void addPenerbangan(PenerbanganModel penerbangan) {

    }
}
