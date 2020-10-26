package apap.tugas.sipes.sipes.controller;

import apap.tugas.sipes.sipes.model.PesawatModel;
import apap.tugas.sipes.sipes.service.PesawatService;
import apap.tugas.sipes.sipes.service.TipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PesawatController {

    @Qualifier("pesawatServiceImpl")
    @Autowired
    private PesawatService pesawatService;

    @Autowired
    private TipeService tipeService;

    @GetMapping("/")
    private String home(){

        return "view-all-pesawat";
    }

    @GetMapping("/resep/view/{idPesawat}")
    public String viewDetailResepPath(
            @PathVariable Long idPesawat,
            Model model){
        PesawatModel pesawat = pesawatService.getPesawatById(idPesawat);

        List<PesawatModel> listPesawat = pesawat.getListPesawat();
        model.addAttribute("resep", resep);
        model.addAttribute("listObat", listObat);
        return "view-resep";

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
        resepService.addResep(resep);
        model.addAttribute("nomorResep", resep.getNoResep());

        return "add-pesawat";
    }

}
