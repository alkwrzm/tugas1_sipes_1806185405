package apap.tugas.sipes.sipes.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pesawat_teknisi")
public class PesawatTeknisiModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idPesawat", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PesawatModel pesawat;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idTeknisi", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private TeknisiModel teknisi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PesawatModel getPesawat() {
        return pesawat;
    }

    public void setPesawat(PesawatModel pesawat) {
        this.pesawat = pesawat;
    }

    public TeknisiModel getTeknisi() {
        return teknisi;
    }

    public void setTeknisi(TeknisiModel teknisi) {
        this.teknisi = teknisi;
    }
}
