package apap.tugas.sipes.sipes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {

    @GetMapping("/")
    private String home(){

        return "home";
    }
}
