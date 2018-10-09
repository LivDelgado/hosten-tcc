package br.cefetmg.inf.hosten.model.domain.rel;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
public class QuartoEstado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "idhospedagem")
    private int seqHospedagem;

    @Column(name = "idquarto")
    private int nroQuarto;

    @Column(name = "nroadultos")
    private int nroAdultos;

    @Column(name = "nrocriancas")
    private int nroCriancas;

    @Column(name = "vlrdiaria")
    private Double vlrDiaria;

    @Column(name = "idtocupado")
    private boolean idtOcupado;

    @Column(name = "datcheckout")
    private Timestamp datCheckOut;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeqHospedagem() {
        return seqHospedagem;
    }

    public void setSeqHospedagem(int seqHospedagem) {
        this.seqHospedagem = seqHospedagem;
    }

    public int getNroQuarto() {
        return nroQuarto;
    }

    public void setNroQuarto(int nroQuarto) {
        this.nroQuarto = nroQuarto;
    }

    public int getNroAdultos() {
        return nroAdultos;
    }

    public void setNroAdultos(int nroAdultos) {
        this.nroAdultos = nroAdultos;
    }

    public int getNroCriancas() {
        return nroCriancas;
    }

    public void setNroCriancas(int nroCriancas) {
        this.nroCriancas = nroCriancas;
    }

    public Double getVlrDiaria() {
        return vlrDiaria;
    }

    public void setVlrDiaria(Double vlrDiaria) {
        this.vlrDiaria = vlrDiaria;
    }

    public boolean isIdtOcupado() {
        return idtOcupado;
    }

    public void setIdtOcupado(boolean idtOcupado) {
        this.idtOcupado = idtOcupado;
    }

    public Timestamp getDatCheckOut() {
        return datCheckOut;
    }

    public void setDatCheckOut(Timestamp datCheckOut) {
        this.datCheckOut = datCheckOut;
    }
}
