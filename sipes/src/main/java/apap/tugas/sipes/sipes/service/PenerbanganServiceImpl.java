package apap.tugas.sipes.sipes.service;

import apap.tugas.sipes.sipes.model.PenerbanganModel;
import apap.tugas.sipes.sipes.model.PesawatModel;
import apap.tugas.sipes.sipes.repository.PenerbanganDb;
import apap.tugas.sipes.sipes.repository.PesawatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PenerbanganServiceImpl implements PenerbanganService{

    @Autowired
    PenerbanganDb penerbanganDb;

    public PenerbanganServiceImpl(){super();}


    @Override
    public void addPenerbangan(PenerbanganModel penerbangan) {
        penerbanganDb.save(penerbangan);

    }

    @Override
    public void deletePenerbangan(PenerbanganModel penerbangan) {
        penerbanganDb.delete(penerbangan);

    }

    @Override
    public PenerbanganModel updatePenerbangan(PenerbanganModel penerbangan) {

        penerbanganDb.save(penerbangan);

        return penerbangan;
    }

    @Override
    public List<PenerbanganModel> getListPenerbangan() {
        return penerbanganDb.findAll();
    }

    @Override
    public PenerbanganModel getPenerbanganById(Long id) {
        return penerbanganDb.findById(id).get();
    }

    @Override
    public PenerbanganModel addPesawat(PesawatModel pesawatModel, PenerbanganModel penerbanganModel){
        penerbanganModel.setPesawat(pesawatModel);
        penerbanganDb.save(penerbanganModel);
        return penerbanganModel;
    }
}
