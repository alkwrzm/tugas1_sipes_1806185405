package apap.tugas.sipes.sipes.service;

import apap.tugas.sipes.sipes.model.TipeModel;
import apap.tugas.sipes.sipes.repository.TipeDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TipeServiceImpl implements TipeService{
    @Autowired
    TipeDb tipeDb;

    @Override
    public List<TipeModel> getListTipe() {
        return tipeDb.findAll();
    }

    @Override
    public TipeModel getTipeById(Long id) {
        return tipeDb.findById(id).get();
    }
}
