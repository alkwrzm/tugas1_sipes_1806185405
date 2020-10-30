package apap.tugas.sipes.sipes.controller;

import apap.tugas.sipes.sipes.model.PesawatModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    private String home(){

        return "home";
    }

    @GetMapping("/pesawat/cari-pesawat-tua")
    private String cari(){


        return "cari-pesawat-tua";
    }

    @GetMapping("/pesawat/filter")
    private String filter(

    ){
        return "filter";

    }

}
