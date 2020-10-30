package apap.tugas.sipes.sipes.service;


import apap.tugas.sipes.sipes.repository.PesawatDb;
import apap.tugas.sipes.sipes.repository.PesawatTeknisiDb;
import apap.tugas.sipes.sipes.repository.TeknisiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PesawatTeknisiServiceImpl implements PesawatTeknisiService{

    @Autowired
    PesawatDb pesawatDb;

    @Autowired
    TeknisiDb teknisiDb;

    @Autowired
    PesawatTeknisiDb pesawatTeknisiDb;
}
