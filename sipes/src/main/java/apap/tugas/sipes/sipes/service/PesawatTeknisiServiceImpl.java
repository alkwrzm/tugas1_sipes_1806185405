package apap.tugas.sipes.sipes.service;


import apap.tugas.sipes.sipes.model.PesawatTeknisiModel;
import apap.tugas.sipes.sipes.repository.PesawatDb;
import apap.tugas.sipes.sipes.repository.PesawatTeknisiDb;
import apap.tugas.sipes.sipes.repository.TeknisiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PesawatTeknisiServiceImpl implements PesawatTeknisiService{

    @Autowired
    PesawatDb pesawatDb;

    @Autowired
    TeknisiDb teknisiDb;

    @Autowired
    PesawatTeknisiDb pesawatTeknisiDb;

    @Override
    public List<PesawatTeknisiModel> getListPesawatTeknisi() {
        return pesawatTeknisiDb.findAll();
    }

    @Override
    public PesawatTeknisiModel addPesawatTeknisi(PesawatTeknisiModel pesawatTeknisi) {
        return pesawatTeknisiDb.save(pesawatTeknisi);
    }
}
