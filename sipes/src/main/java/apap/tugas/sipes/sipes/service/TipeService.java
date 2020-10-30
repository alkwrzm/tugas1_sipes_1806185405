package apap.tugas.sipes.sipes.service;

import apap.tugas.sipes.sipes.model.TipeModel;

import java.util.List;

public interface TipeService {

    List<TipeModel> getListTipe();

    TipeModel getTipeById(Long id);
}
