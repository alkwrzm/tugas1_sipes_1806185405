package apap.tugas.sipes.sipes.controller;


import apap.tugas.sipes.sipes.model.PenerbanganModel;
import apap.tugas.sipes.sipes.model.PesawatModel;
import apap.tugas.sipes.sipes.service.PenerbanganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class PenerbanganController {

    @Qualifier("penerbanganServiceImpl")
    @Autowired
    private PenerbanganService penerbanganService;

    @GetMapping("/penerbangan/view/{idPenerbangan}")
    public String viewDetailPenerbanganPath(
            @PathVariable Long idPenerbangan,
            Model model){
        PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(idPenerbangan);
        model.addAttribute("penerbangan", penerbangan);
        if(penerbangan. == null){
            String noSeri = null;
        }
        String noSeri = penerbangan.getPesawat().getNomorSeri();
        model.addAttribute("noSeriPesawat", noSeri);
        return "view-penerbangan";

    }

    @RequestMapping("/penerbangan")
    public String listResep(Model model) {
        List<PenerbanganModel> listPenerbangan = penerbanganService.getListPenerbangan();
        model.addAttribute("listPenerbangan", listPenerbangan);

        return "viewall-penerbangan";
    }

    @GetMapping("/penerbangan/add")
    private String addPenerbanganFormPage(Model model){
        model.addAttribute("penerbangan", new PenerbanganModel());
        return "form-add-penerbangan";
    }

    @PostMapping("/penerbangan/add")
    private String addPenerbanganSubmit(
            @ModelAttribute PenerbanganModel penerbangan,
            Model model){
        penerbanganService.addPenerbangan(penerbangan);
        model.addAttribute("idPenerbangan", penerbangan.getId());

        return "add-penerbangan";
    }

    @RequestMapping("/penerbangan/delete/{idPenerbangan}")
    private String deletePenerbanganFromPage(
            @PathVariable Long idPenerbangan,
            Model model){
        model.addAttribute("idPenerbangan", idPenerbangan);

        PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(idPenerbangan);
        penerbanganService.deletePenerbangan(penerbangan);

//        if(resep.getListObat().size() < 1) {
//            resepService.deleteResep(noResep);
//            return "delete-resep";
//        }else {
//            return "gagal-delete-resep";
//        }

        return "delete-penerbangan";

    }

    @GetMapping("/penerbangan/change/{idPenerbangan}")
    private String changePenerbanganFormPage(
            @PathVariable Long idPenerbangan,
            Model model){
        PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(idPenerbangan);
        model.addAttribute("penerbangan", penerbangan);

        return "form-update-penerbangan";
    }

    @PostMapping("/penerbangan/change")
    private String changePenerbanganFormSubmit(
            @ModelAttribute PenerbanganModel penerbangan,
            Model model){
        PenerbanganModel penerbanganModel = penerbanganService.updatePenerbangan(penerbangan);
        model.addAttribute("idPenerbangan", penerbangan.getId());

        return "update-penerbangan";
    }

}
