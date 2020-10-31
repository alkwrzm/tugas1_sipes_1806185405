package apap.tugas.sipes.sipes.controller;

import apap.tugas.sipes.sipes.model.*;
import apap.tugas.sipes.sipes.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

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

    @GetMapping("/")
    private String home(){

        return "home";
    }

    @GetMapping("/pesawat/cari-pesawat-tua")
    private String cari(){


        return "cari-pesawat-tua";
    }

    @GetMapping("/filter")
    public String pesawatFilterSubmit(
            @RequestParam(name="idPenerbangan") Optional<Long> lidPenerbangan,
            @RequestParam(name="idTipe") Optional<Long> lidTipe,
            @RequestParam(name="idTeknisi") Optional<Long> lidTeknisi,
            Model model
    ){

        List<PenerbanganModel> allPenerbangan = penerbanganService.getListPenerbangan();
        List<TipeModel> allTipe = tipeService.getListTipe();
        List<TeknisiModel> allTeknisi = teknisiService.getListTeknisi();
        model.addAttribute("listPenerbangan", allPenerbangan);
        model.addAttribute("listTipe", allTipe);
        model.addAttribute("listTeknisi", allTeknisi);
        model.addAttribute("notEmpty", false);

        Long idPenerbangan = Long.valueOf(0);
        Long idTipe = Long.valueOf(0);
        Long idTeknisi = Long.valueOf(0);

        try{
            idPenerbangan = lidPenerbangan.get();
        }catch(Exception e){

        }

        try{
            idTipe = lidTipe.get();
        }catch(Exception e){

        }

        try{
            idTeknisi = lidTeknisi.get();
        }catch(Exception e){

        }

        if((idPenerbangan!=0) || (idTipe!=0) || (idTeknisi!=0)){
            List<PesawatModel> allPesawat = pesawatService.getListPesawat();
            List<PesawatModel> listPesawat = new ArrayList<PesawatModel>();

            for(PesawatModel p : allPesawat){
                listPesawat.add(p);
            }

            for(PesawatModel p : allPesawat){
                //1
                if(!(idPenerbangan == 0)){
                    PenerbanganModel targetPenerbangan = penerbanganService.getPenerbanganById(idPenerbangan);
                    List<PenerbanganModel> pPenerbangan = p.getListPenerbangan();
                    if (!pPenerbangan.contains(targetPenerbangan)){
                        listPesawat.remove(p);
                        continue;
                    }
                }


                //2
                if(!(idTipe == 0)){
                    TipeModel targetTipe = tipeService.getTipeById(idTipe);
                    TipeModel pTipe = p.getTipe();
                    if(pTipe.getId() != targetTipe.getId()){
                        listPesawat.remove(p);
                        continue;
                    }
                }


                //3
                List<TeknisiModel> teknisinya = null;
                TeknisiModel teknisiGaada = new TeknisiModel();
                if(!(idTeknisi == 0)){
                    TeknisiModel targetTeknisi = teknisiService.getTeknisiById(idTeknisi);
                    List<PesawatTeknisiModel> targetList = targetTeknisi.getListPesawatTeknisi();
                    List<PesawatModel> hasil = new ArrayList<>();
                    for (PesawatTeknisiModel a : targetList
                    ) {
                        hasil.add(a.getPesawat());
                    }

                    if(!hasil.contains(p)){
                        listPesawat.remove(p);
                    }


//                    for (PesawatTeknisiModel i: pTeknisi
//                         ) { if (i.getTeknisi()==null){
//                             teknisinya.add(teknisiGaada);
//                    }else {teknisinya.add(i.getTeknisi());
//
//                    }
//
//                    }
//                    if(!teknisinya.contains(targetTeknisi)){
//                        listPesawat.remove(p);
//                        continue;
//                    }
                }
            }
            Boolean notEmpty = true;
            if(listPesawat.size()==0) notEmpty = false;
            model.addAttribute("notEmpty", notEmpty);
            model.addAttribute("listPesawat", listPesawat);

        }

        return "filter";
    }

    @GetMapping("/bonus")
    private String bonus(
            Model model){
        List<PesawatModel> listPesawat = pesawatService.getListPesawat();
        model.addAttribute("listPesawat", listPesawat);

        return "bonus";}

}
