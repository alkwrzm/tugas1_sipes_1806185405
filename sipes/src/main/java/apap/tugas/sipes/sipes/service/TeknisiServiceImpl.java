package apap.tugas.sipes.sipes.service;

import apap.tugas.sipes.sipes.repository.TeknisiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TeknisiServiceImpl implements TeknisiService{

    @Autowired
    TeknisiDb teknisiDb;

    public TeknisiServiceImpl(){super();}
}
