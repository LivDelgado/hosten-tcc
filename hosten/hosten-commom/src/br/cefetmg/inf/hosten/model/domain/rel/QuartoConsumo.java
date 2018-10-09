package br.cefetmg.inf.hosten.model.domain.rel;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "quartoconsumo")
public class QuartoConsumo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "seqHospedagem")
    private int seqHospedagem;

    @Column(name = "nroQuarto")
    private int nroQuarto;

    @Column(name = "datConsumo")
    private Timestamp datConsumo;

    @Column(name = "qtdConsumo")
    private int qtdConsumo;

    @Column(name = "seqServico")
    private int seqServico;

    @Column(name = "codUsuarioRegistro")
    private String codUsuarioRegistro;

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

    public Timestamp getDatConsumo() {
        return datConsumo;
    }

    public void setDatConsumo(Timestamp datConsumo) {
        this.datConsumo = datConsumo;
    }

    public int getQtdConsumo() {
        return qtdConsumo;
    }

    public void setQtdConsumo(int qtdConsumo) {
        this.qtdConsumo = qtdConsumo;
    }

    public int getSeqServico() {
        return seqServico;
    }

    public void setSeqServico(int seqServico) {
        this.seqServico = seqServico;
    }

    public String getCodUsuarioRegistro() {
        return codUsuarioRegistro;
    }

    public void setCodUsuarioRegistro(String codUsuarioRegistro) {
        this.codUsuarioRegistro = codUsuarioRegistro;
    }
}
