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

@Entity
@Table(name = "penerbangan")
public class PenerbanganModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max=255)
    @Column(name="kodeBandaraAsal", nullable = false)
    private String kodeBandaraAsal;

    @NotNull
    @Size(max=255)
    @Column(name="kodeBandaraTujuan", nullable = false)
    private String kodeBandaraTujuan;

    @NotNull
    @Column(name="waktuBerangkat", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date waktuBerangkat;

    @NotNull
    @Size(max=255)
    @Column(name="nomorPenerbangan", nullable = false, unique = true)
    private String nomorPenerbangan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idPesawat", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PesawatModel pesawatPenerbangan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKodeBandaraAsal() {
        return kodeBandaraAsal;
    }

    public void setKodeBandaraAsal(String kodeBandaraAsal) {
        this.kodeBandaraAsal = kodeBandaraAsal;
    }

    public String getKodeBandaraTujuan() {
        return kodeBandaraTujuan;
    }

    public void setKodeBandaraTujuan(String kodeBandaraTujuan) {
        this.kodeBandaraTujuan = kodeBandaraTujuan;
    }

    public Date getWaktuBerangkat() {
        return waktuBerangkat;
    }

    public void setWaktuBerangkat(Date waktuBerangkat) {
        this.waktuBerangkat = waktuBerangkat;
    }

    public String getNomorPenerbangan() {
        return nomorPenerbangan;
    }

    public void setNomorPenerbangan(String nomorPenerbangan) {
        this.nomorPenerbangan = nomorPenerbangan;
    }

    public PesawatModel getPesawat() {
        return pesawatPenerbangan;
    }

    public void setPesawat(PesawatModel pesawat) {
        this.pesawatPenerbangan = pesawat;
    }

}
