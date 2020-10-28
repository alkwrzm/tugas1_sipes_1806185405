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

    }

    @Override
    public void deletePenerbangan(PenerbanganModel penerbangan) {

    }

    @Override
    public PenerbanganModel updatePenerbangan(PenerbanganModel penerbangan) {
        return null;
    }

    @Override
    public List<PenerbanganModel> getListPenerbangan() {
        return null;
    }

    @Override
    public PenerbanganModel getPenerbanganById(Long Id) {
        return null;
    }
}
