package apap.tugas.sipes.sipes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "pesawat")
public class PesawatModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max=255)
    @Column(name="maskapai", nullable = false)
    private String maskapai;

    @NotNull
    @Size(max=255)
    @Column(name="nomorSeri", nullable = false, unique = true)
    private String nomorSeri;

    @NotNull
    @Size(max=255)
    @Column(name="tempatDibuat", nullable = false)
    private String tempatDibuat;

    @NotNull
    @Column(name="tanggalDibuat", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date tanggalDibuat;

    @NotNull
    @Column(name="jenisPesawat", nullable = false)
    private Integer jenisPesawat;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idTipe", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private TipeModel tipe;

    @OneToMany(mappedBy = "pesawatPenerbangan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PenerbanganModel> listPenerbangan;

    @OneToMany(mappedBy = "pesawat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PesawatTeknisiModel> listPesawatTeknisi;

    private Integer usia;

    private Integer jumlahTeknisi;

    public Integer getJumlahTeknisi() {
        return jumlahTeknisi;
    }

    public void setJumlahTeknisi(Integer jumlahTeknisi) {
        this.jumlahTeknisi = jumlahTeknisi;
    }

    public Integer getUsia() {
        return usia;
    }

    public void setUsia(Integer usia) {
        this.usia = usia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaskapai() {
        return maskapai;
    }

    public void setMaskapai(String maskapai) {
        this.maskapai = maskapai;
    }

    public String getNomorSeri() {
        return nomorSeri;
    }

    public void setNomorSeri(String nomorSeri) { this.nomorSeri = nomorSeri;}

    public String getTempatDibuat() {
        return tempatDibuat;
    }

    public void setTempatDibuat(String tempatDibuat) {
        this.tempatDibuat = tempatDibuat;
    }

    public Date getTanggalDibuat() {
        return tanggalDibuat;
    }

    public void setTanggalDibuat(Date tanggalDibuat) {
        this.tanggalDibuat = tanggalDibuat;
    }

    public Integer getJenisPesawat() {
        return jenisPesawat;
    }

    public void setJenisPesawat(Integer jenisPesawat) {
        this.jenisPesawat = jenisPesawat;
    }

    public TipeModel getTipe() {
        return tipe;
    }

    public void setTipe(TipeModel tipe) {
        this.tipe = tipe;
    }

    public List<PenerbanganModel> getListPenerbangan() {
        return listPenerbangan;
    }

    public void setListPenerbangan(List<PenerbanganModel> listPenerbangan) {
        this.listPenerbangan = listPenerbangan;
    }

    public List<PesawatTeknisiModel> getListPesawatTeknisi() {
        return listPesawatTeknisi;
    }

    public void setListPesawatTeknisi(List<PesawatTeknisiModel> listPesawatTeknisi) {
        this.listPesawatTeknisi = listPesawatTeknisi;
    }

    public List<TeknisiModel> getListTeknisi(){
        List<TeknisiModel> listTeknisi = null;
        for (PesawatTeknisiModel i: listPesawatTeknisi
        ) { listTeknisi.add(i.getTeknisi());
        }
        return listTeknisi;
    }
}
