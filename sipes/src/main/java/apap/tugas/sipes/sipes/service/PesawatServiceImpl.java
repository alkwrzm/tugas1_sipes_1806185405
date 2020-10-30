package apap.tugas.sipes.sipes.service;

import apap.tugas.sipes.sipes.model.PenerbanganModel;
import apap.tugas.sipes.sipes.model.PesawatModel;
import apap.tugas.sipes.sipes.model.TeknisiModel;
import apap.tugas.sipes.sipes.repository.PesawatDb;
import apap.tugas.sipes.sipes.repository.TeknisiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PesawatServiceImpl implements PesawatService{
    @Autowired
    PesawatDb pesawatDb;

    @Autowired
    TeknisiDb teknisiDb;


    public PesawatServiceImpl(){super();}

    @Override
    public void addPesawat(PesawatModel pesawat) {
        pesawatDb.save(pesawat);

    }

    @Override
    public void deletePesawat(PesawatModel pesawat) {
        pesawatDb.delete(pesawat);

    }

    @Override
    public PesawatModel updatePesawat(PesawatModel pesawat) {
        return null;
    }

    @Override
    public List<PesawatModel> getListPesawat() {
        return pesawatDb.findAll();
    }

    @Override
    public PesawatModel getPesawatById(Long id) {
        return pesawatDb.findById(id).get();
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

    @Override
    public void setUsia(List<PesawatModel> pesawat) {
        for (PesawatModel i:pesawat) {
            Date date = i.getTanggalDibuat();
            String[] setring = date.toString().split("-");
            i.setUsia(2020-Integer.parseInt(setring[0]));
        }
    }
}
