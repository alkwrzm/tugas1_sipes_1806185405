package apap.tugas.sipes.sipes.controller;

import apap.tugas.sipes.sipes.model.*;
import apap.tugas.sipes.sipes.service.PenerbanganService;
import apap.tugas.sipes.sipes.service.PesawatService;
import apap.tugas.sipes.sipes.service.TeknisiService;
import apap.tugas.sipes.sipes.service.TipeService;
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

    @GetMapping("/pesawat/{idPesawat}")
    public String viewDetailPesawatPath(
            @PathVariable Long idPesawat,
            Model model){
        PesawatModel pesawat = pesawatService.getPesawatById(idPesawat);
        List<PesawatTeknisiModel> listTeknisiPesawat = pesawat.getListPesawatTeknisi();
//        List<TeknisiModel> listTeknisi = null;
//        List<PesawatTeknisiModel> listTeknisiPesawat = pesawat.getListPesawatTeknisi();
//        for (PesawatTeknisiModel i: listTeknisiPesawat
//             ) { listTeknisi.add(i.getTeknisi());
//        }
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

    @GetMapping("/pesawat/{id}/tambah-penerbangan")
    public String assignpesawat_penerbangan(
            @PathVariable Long id,
            Model model
    ){
        PesawatModel pesawat = pesawatService.getPesawatById(id);
        List<PenerbanganModel> penerbanganList = penerbanganService.getListPenerbangan();
        List<PesawatTeknisiModel> listTeknisi =  pesawat.getListPesawatTeknisi();
        List<PenerbanganModel> listPenerbangan = new ArrayList<PenerbanganModel>();
        listPenerbangan.add(new PenerbanganModel());
        pesawat.setListPenerbangan(listPenerbangan);

        model.addAttribute("listKosong", listPenerbangan);
        model.addAttribute("listPenerbangan", penerbanganList);
        model.addAttribute("pesawat", pesawat);
        model.addAttribute("listTeknisi", listTeknisi);

        return "tambah-penerbangan-pesawat";
    }

    @PostMapping ("/pesawat/{id}/tambah-penerbangan")
    public String assignpesawat(
            @PathVariable Long id, @ModelAttribute PesawatModel pesawat,
            Model model
    ){
        //get id penerbangan arraylist index 0
        Long idpenerbangan = pesawat.getListPenerbangan().get(0).getId();
        PenerbanganModel penerbangan = penerbanganService.getPenerbanganById(idpenerbangan);

        PesawatModel tempat_pesawat = pesawatService.getPesawatById(id);

        tempat_pesawat.getListPenerbangan().add(penerbangan);
        pesawatService.updatePesawat(tempat_pesawat);


        List<PesawatTeknisiModel> listTeknisi =  pesawat.getListPesawatTeknisi();


        penerbangan.setPesawat(tempat_pesawat);
        penerbanganService.updatePenerbangan(penerbangan);
        List<PenerbanganModel> listPenerbangan = new ArrayList<PenerbanganModel>();
        listPenerbangan.add(new PenerbanganModel());
        pesawat.setListPenerbangan(listPenerbangan);
        List<PenerbanganModel> penerbanganList = penerbanganService.getListPenerbangan();

        pesawat = pesawatService.getPesawatById(pesawat.getId());

        model.addAttribute("listKosong", listPenerbangan);
        model.addAttribute("allPenerbangan", penerbanganList);
        model.addAttribute("pesawat", pesawat);
        model.addAttribute("listTeknisi", listTeknisi);

        return "tambah-penerbangan";
    }

    @GetMapping("/pesawat/tambah")
    public String addPesawatFormPage(Model model){
        PesawatModel pesawat = new PesawatModel();

        List<TipeModel> listTipe = tipeService.getListTipe();
        List<TeknisiModel> listTeknisi = teknisiService.getListTeknisi();
        List<PesawatTeknisiModel> listAvailableTeknisi = new ArrayList<>();

        listAvailableTeknisi.add(new PesawatTeknisiModel());
        pesawat.setListPesawatTeknisi(listAvailableTeknisi);

        model.addAttribute("pesawat", pesawat);
        model.addAttribute("listTipe", listTipe);
        model.addAttribute("listTeknisi", listTeknisi);

        return "form-add-pesawat";
    }

    @PostMapping("/pesawat/tambah")
    public String submitpesawatform(
            @ModelAttribute PesawatModel pesawat,
            Model model
    ){
        TipeModel tipe = tipeService.getTipeById(pesawat.getTipe().getId());
        pesawat.setTipe(tipe);

        //getter
        String jenis_pesawat = pesawat.getJenisPesawat().toString();
        TipeModel tipe_pesawat = pesawat.getTipe();
        int tahun = pesawat.getTanggalDibuat().getYear();

        //format year and format no seri
        String tahun_string = Integer.toString(tahun);
        String year = reverseString(tahun_string);
        String added_year = tambah_8(tahun);
        String random_string = generate_random();

        //Generate id
        String nomor_seri_fix = nomor_seri(jenis_pesawat, tipe_pesawat, year, added_year, random_string);
        pesawat.setNomorSeri(nomor_seri_fix);

       //

        return "add-pesawat";

    }
    @PostMapping(value="/pesawat/tambah", params={"tambahteknisi"})
    public String addmultipleteknisi(
            @ModelAttribute PesawatModel pesawat,
            Model model
    ){

        List<TeknisiModel> allTeknisi = teknisiService.getListTeknisi();
        model.addAttribute("pesawat", pesawat);
        List<TipeModel> allTipe = tipeService.getListTipe();
        model.addAttribute("listTipe", allTipe);
        model.addAttribute("listTeknisi", allTeknisi);

        return "form-add-pesawat";
    }

    @RequestMapping("/pesawat/delete/{idPesawat}")
    private String deletePesawatFromPage(
            @PathVariable Long idPesawat,
            Model model){
        model.addAttribute("idPesawat", idPesawat);

        PesawatModel pesawat = pesawatService.getPesawatById(idPesawat);
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
        PesawatModel pesawatModel = pesawatService.updatePesawat(pesawat);
        model.addAttribute("nomorSeri", pesawat.getNomorSeri());

        return "update-pesawat";
    }

    @RequestMapping("/pesawat/filter")
    public String cariPesawatFilter(
            @RequestParam(value = "")
            Model model

    ) {
        List<PesawatModel> listPesawat = pesawatService.getListPesawatTua();
        pesawatService.setUsia(listPesawat);
        model.addAttribute("listPesawat", listPesawat);

        return "cari-pesawat-tua";
    }

    public String nomor_seri(String jenis_pesawat, TipeModel tipe_pesawat, String year, String added_year, String random_string){
        String a = "";

        //add jenis
        if(jenis_pesawat == "Komersial"){
            a += "1";
        }else{
            a += "2";
        }
        //add tipe
        if(tipe_pesawat.getNama() =="Boeing"){
            a+="BO";
        }
        else if(tipe_pesawat.getNama() =="ATR"){
            a+="AT";
        }
        else{
            a+="BB";
        }

        //add dibalik
        a+= year;

        //add tahun tambah 8
        a+= added_year;

        //add 2 random char
        a+= random_string;

        return a;
    }


    public String reverseString(String str){
        char ch[]=str.toCharArray();
        String rev="";
        for(int i=ch.length-1;i>=0;i--){
            rev+=ch[i];
        }
        return rev;
    }

    public String tambah_8(int tahun){
        tahun +=8;
        return Integer.toString(tahun);
    }

    public String generate_random() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        StringBuilder sb = new StringBuilder(2);
        for (int i = 0; i < 2; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();

    }
}
