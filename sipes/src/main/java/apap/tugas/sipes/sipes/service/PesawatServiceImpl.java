package apap.tugas.sipes.sipes.service;

import apap.tugas.sipes.sipes.model.PenerbanganModel;
import apap.tugas.sipes.sipes.model.PesawatModel;
import apap.tugas.sipes.sipes.model.TeknisiModel;
import apap.tugas.sipes.sipes.repository.PesawatDb;
import apap.tugas.sipes.sipes.repository.TeknisiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PesawatServiceImpl implements PesawatService{
    @Autowired
    PesawatDb pesawatDb;

    @Autowired
    TeknisiDb teknisiDb;


    public PesawatServiceImpl(){super();}

    @Override
    public void addPesawat(PesawatModel pesawat) {
        pesawatDb.save(pesawat);

    }

    @Override
    public void deletePesawat(PesawatModel pesawat) {
        pesawatDb.delete(pesawat);

    }

    @Override
    public PesawatModel updatePesawat(PesawatModel pesawat) {
        return pesawatDb.save(pesawat);
    }

    @Override
    public List<PesawatModel> getListPesawat() {
        return pesawatDb.findAll();
    }

    @Override
    public PesawatModel getPesawatById(Long id) {
        return pesawatDb.findById(id).get();
    }

    @Override
    public List<PenerbanganModel> getListPenerbangan() {
        return null;
    }

    @Override
    public List<PesawatModel> getListPesawatTua() {
        return pesawatDb.findByTanggalDibuat();
    }

    @Override
    public void addPenerbangan(PenerbanganModel penerbangan) {

    }

    @Override
    public void setUsia(List<PesawatModel> pesawat) {
        for (PesawatModel i:pesawat) {
            Date date = i.getTanggalDibuat();
            String[] setring = date.toString().split("-");
            i.setUsia(2020-Integer.parseInt(setring[0]));
        }
    }

    @Override
    public PesawatModel getPesawatByNomorSeri(String nomorSeri) {
        return pesawatDb.findPesawatModelByNomorSeri(nomorSeri);
    }

    @Override
    public String generateNomorSeri(PesawatModel pesawat) {
        String a = "";
        //add jenis
        if(pesawat.getJenisPesawat() == 0){
            a += "1";
        }else if(pesawat.getJenisPesawat() == 1){
            a += "2";
        }else{
            a+="3";
        }
        //add tipe
        if(pesawat.getTipe().getNama() =="Boeing"){
            a+="BO";
        }
        else if(pesawat.getTipe().getNama() =="ATR"){
            a+="AT";
        }
        else{
            a+="BB";
        }

        //tahun dibuat lalu dibalik
        //referensi --> https://www.javatpoint.com/how-to-reverse-string-in-java
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String third_unrev = dateFormat.format(pesawat.getTanggalDibuat());
        third_unrev = third_unrev.substring(6,10);
        String tahunPesawat = "";
        char ch[] = third_unrev.toCharArray();
        for(int i = ch.length-1;i >= 0;i--){
            tahunPesawat += ch[i];
        }
        //tahun pesawat dibuat ditambah 8
        int tahun = Integer.parseInt(third_unrev) + 8;
        String jumlahTahun = Integer.toString(tahun);

        //random aplhabet
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
        String randomAlpha = sb.toString();

        //hasil
        String hasil = a + tahunPesawat + jumlahTahun + randomAlpha;
        return hasil;
    }
}
