package apap.tugas.sipes.sipes.controller;

import apap.tugas.sipes.sipes.model.*;
import apap.tugas.sipes.sipes.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Controller
public class PesawatController {

    @Qualifier("pesawatServiceImpl")
    @Autowired
    private PesawatService pesawatService;

    @Autowired
    private TipeService tipeService;

    @Autowired
    private TeknisiService teknisiService;

    @Autowired
    private PenerbanganService penerbanganService;

    @Autowired
    PesawatTeknisiService pesawatTeknisiService;

    @GetMapping("/pesawat/{idPesawat}")
    public String viewDetailPesawatPath(
            @PathVariable Long idPesawat,
            Model model){
        PesawatModel pesawat = pesawatService.getPesawatById(idPesawat);
        List<PesawatTeknisiModel> listTeknisiPesawat = pesawat.getListPesawatTeknisi();
        List <PenerbanganModel> listPenerbangan = penerbanganService.getListPenerbangan();
        List <PenerbanganModel> listPenerbanganPesawat = pesawat.getListPenerbangan();
        model.addAttribute("listPenerbangan", listPenerbangan);
        model.addAttribute("penerbanganAda", listPenerbanganPesawat);
        model.addAttribute("pesawat", pesawat);
        model.addAttribute("listTeknisi", listTeknisiPesawat);

        return "view-pesawat";

    }

    @RequestMapping("/pesawat")
    public String listPesawat(Model model) {
        List<PesawatModel> listPesawat = pesawatService.getListPesawat();


        model.addAttribute("listPesawat", listPesawat);

        return "viewall-pesawat";
    }

    @RequestMapping("/pesawat/pesawat-tua")
    public String cariPesawatTua(Model model) {
        List<PesawatModel> listPesawat = pesawatService.getListPesawatTua();
        pesawatService.setUsia(listPesawat);
        model.addAttribute("listPesawat", listPesawat);

        return "cari-pesawat-tua";
    }

    @PostMapping("/pesawat/{id}/tambah-penerbangan")
    public String addPenerbanganPesawat(
            @ModelAttribute PenerbanganModel penerbangan,
            @PathVariable(value = "id") Long id,
            @RequestParam("idPenerbangan") Long idPenerbangan,
            Model model){
        long a = 2;
        PenerbanganModel penerbanganModeltarget = penerbanganService.getPenerbanganById(idPenerbangan);

        PesawatModel pesawat = pesawatService.getPesawatById(id);
        penerbanganService.addPesawat(pesawat, penerbanganModeltarget);

        List<PesawatTeknisiModel> listPesawatTeknisi = pesawat.getListPesawatTeknisi();
        List <PenerbanganModel> listPenerbangan = penerbanganService.getListPenerbangan();
        List <PenerbanganModel> listPenerbanganPesawat = pesawat.getListPenerbangan();
        model.addAttribute("listPenerbangan", listPenerbangan);
        model.addAttribute("penerbanganAda", listPenerbanganPesawat);
        model.addAttribute("pesawat", pesawat);
        model.addAttribute("listTeknisi", listPesawatTeknisi);

        String notifikasi = "Penerbangan dengan nomor "+penerbanganModeltarget.getNomorPenerbangan()+" berhasil ditambahkan!";
        model.addAttribute("notifikasi", notifikasi);

        return "view-pesawat";
    }

    @GetMapping("/pesawat/tambah")
    public String addPesawatFormPage(Model model){

        PesawatModel pesawatModel = new PesawatModel();
        TeknisiModel teknisiModel = new TeknisiModel();
        PesawatTeknisiModel pesawatTeknisiModel = new PesawatTeknisiModel();
        List<PesawatTeknisiModel> listPesawatTeknisiModel = new ArrayList<>();

        pesawatTeknisiModel.setTeknisi(teknisiModel);
        pesawatTeknisiModel.setPesawat(pesawatModel);
        listPesawatTeknisiModel.add(pesawatTeknisiModel);
        pesawatModel.setListPesawatTeknisi(listPesawatTeknisiModel);

        List<TeknisiModel> teknisiModelList = teknisiService.getListTeknisi();
        List<TipeModel> tipeModel = tipeService.getListTipe();

        model.addAttribute("listPesawatTeknisi", listPesawatTeknisiModel);
        model.addAttribute("pesawat", pesawatModel);
        model.addAttribute("listTipe", tipeModel);
        model.addAttribute("listTeknisi", teknisiModelList);

        return "form-add-pesawat";
    }

    @PostMapping("/pesawat/tambah")
    public String submitpesawatform(
            @ModelAttribute PesawatModel pesawatModel,
            Model model
    ){
        PesawatModel pesawat = pesawatService.getPesawatByNomorSeri(pesawatModel.getNomorSeri());
        if(pesawat != null){
            model.addAttribute("pesawat", pesawatModel);
            return "home";
        }
        for (PesawatTeknisiModel i : pesawatModel.getListPesawatTeknisi()) {
            i.setPesawat(pesawatModel);
        }
        String nomorSeri = pesawatService.generateNomorSeri(pesawatModel);
        pesawatModel.setNomorSeri(nomorSeri);
        pesawatService.addPesawat(pesawatModel);

        for (PesawatTeknisiModel i : pesawatModel.getListPesawatTeknisi()) {
            pesawatTeknisiService.addPesawatTeknisi(i);
        }
        String maskapai = pesawatModel.getMaskapai();

        model.addAttribute("maskapai", maskapai);
        model.addAttribute("nomorSeri", nomorSeri);
        return "add-pesawat";
    }

    @PostMapping(value="/pesawat/tambah", params={"tambahteknisi"})
    public String addmultipleteknisi(
            @ModelAttribute PesawatModel pesawat,
            Model model
    ){
        PesawatTeknisiModel pesawatTeknisiModel = new PesawatTeknisiModel();
        if (pesawat.getListPesawatTeknisi()==null){
            pesawat.setListPesawatTeknisi(new ArrayList<PesawatTeknisiModel>());
        }
        pesawat.getListPesawatTeknisi().add(pesawatTeknisiModel);
        List<TeknisiModel> listTeknisi = teknisiService.getListTeknisi();
        List<TipeModel> allTipe = tipeService.getListTipe();

        model.addAttribute("pesawat", pesawat);
        model.addAttribute("listTipe", allTipe);
        model.addAttribute("listTeknisi", listTeknisi);

        return "form-add-pesawat";
    }

    @RequestMapping("/pesawat/hapus/{idPesawat}")
    private String deletePesawatFromPage(
            @PathVariable Long idPesawat,
            Model model){
        PesawatModel pesawat = pesawatService.getPesawatById(idPesawat);
        model.addAttribute("maskapai", pesawat.getMaskapai());
        pesawatService.deletePesawat(pesawat);

        return "delete-pesawat";

    }

    @GetMapping("/pesawat/ubah/{idPesawat}")
    private String changePesawatFormPage(
            @PathVariable Long idPesawat,
            Model model){
        PesawatModel pesawat = pesawatService.getPesawatById(idPesawat);
        model.addAttribute("pesawat", pesawat);

        return "form-update-pesawat";
    }

    @PostMapping("/pesawat/ubah")
    private String changePesawatFormSubmit(
            @ModelAttribute PesawatModel pesawat,
            Model model){


        String nomorSeri = pesawatService.generateNomorSeri(pesawat);
        pesawat.setNomorSeri(nomorSeri);
        PesawatModel pesawatModel = pesawatService.updatePesawat(pesawat);
        model.addAttribute("pesawat", pesawatModel);
        model.addAttribute("nomorSeri", nomorSeri);

        return "update-pesawat";
    }


}
