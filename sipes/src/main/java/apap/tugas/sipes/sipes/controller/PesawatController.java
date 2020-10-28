package apap.tugas.sipes.sipes.controller;

import apap.tugas.sipes.sipes.model.PesawatModel;
import apap.tugas.sipes.sipes.model.PesawatTeknisiModel;
import apap.tugas.sipes.sipes.model.TipeModel;
import apap.tugas.sipes.sipes.service.PesawatService;
import apap.tugas.sipes.sipes.service.TipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
public class PesawatController {

    @Qualifier("pesawatServiceImpl")
    @Autowired
    private PesawatService pesawatService;

    @Autowired
    private TipeService tipeService;

    @GetMapping("/pesawat")
    private String viewAllPesawat(){

        return "viewall-pesawat";
    }

    @GetMapping("/pesawat/view/{idPesawat}")
    public String viewDetailPesawatPath(
            @PathVariable Long idPesawat,
            Model model){
        PesawatModel pesawat = pesawatService.getPesawatById(idPesawat);

        List<PesawatTeknisiModel> listPesawatTeknisi = pesawat.getListPesawatTeknisi();
        model.addAttribute("pesawat", pesawat);
        model.addAttribute("listPesawatTeknisi", listPesawatTeknisi);
        return "view-pesawat";

    }

    @RequestMapping("/pesawat/viewall")
    public String listResep(Model model) {
        List<PesawatModel> listPesawat = pesawatService.getListPesawat();


        model.addAttribute("listPesawat", listPesawat);

        return "viewall-pesawat";
    }


    @GetMapping("/pesawat/add")
    private String addPesawatFormPage(Model model){
        model.addAttribute("pesawat", new PesawatModel());
        return "form-add-pesawat";
    }

    @PostMapping("/pesawat/add")
    private String addPesawatSubmit(
            @ModelAttribute PesawatModel pesawat,
            Model model){
        pesawatService.addPesawat(pesawat);
        List<TipeModel> listTipe = tipeService.getListTipe();
        model.addAttribute("listTipe", listTipe);
        model.addAttribute("id", pesawat.getId());

        return "add-pesawat";
    }

    @RequestMapping("/pesawat/delete/{idPesawat}")
    private String deletePesawatFromPage(
            @PathVariable Long idPesawat,
            Model model){
        model.addAttribute("idPesawat", idPesawat);

        PesawatModel pesawat = pesawatService.getPesawatById(idPesawat);

//        if(pesawat.getListObat().size() < 1) {
//            pesawatService.deleteResep(noResep);
//            return "delete-pesawat";
//        }else {
//            return "gagal-delete-pesawat";
//        }

        return "delete-pesawat";

    }

    @GetMapping("/pesawat/change/{idPesawat}")
    private String changePesawatFormPage(
            @PathVariable Long idPesawat,
            Model model){
        PesawatModel pesawat = pesawatService.getPesawatById(idPesawat);
        model.addAttribute("pesawat", pesawat);

        return "form-update-pesawat";
    }

    @PostMapping("/pesawat/change")
    private String changePesawatFormSubmit(
            @ModelAttribute PesawatModel pesawat,
            Model model){
        PesawatModel pesawatModel = pesawatService.updatePesawat(pesawat);
        model.addAttribute("idPesawat", pesawat.getId());

        return "update-pesawat";
    }

}
