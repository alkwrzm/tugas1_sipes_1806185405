package apap.tugas.sipes.sipes.controller;

import apap.tugas.sipes.sipes.model.PesawatModel;
import apap.tugas.sipes.sipes.service.PesawatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private PesawatService pesawatService;

    @GetMapping("/")
    private String home(){

        return "home";
    }

    @GetMapping("/pesawat/cari-pesawat-tua")
    private String cari(){


        return "cari-pesawat-tua";
    }

    @GetMapping("/pesawat/filter?idPenerbangan={idPenerbangan}&idTipe={idTipe}&idTeknisi={idTeknisi}")
    private String filter(

    ){
        return "filter";

    }
    @GetMapping("/bonus")
    private String bonus(
            Model model){
        List<PesawatModel> listPesawat = pesawatService.getListPesawat();
        model.addAttribute("listPesawat", listPesawat);

        return "bonus";}

}
