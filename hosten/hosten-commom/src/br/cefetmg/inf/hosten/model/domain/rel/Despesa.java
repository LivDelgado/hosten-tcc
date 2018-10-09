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
public class Despesa implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
	private int id;

    @Column(name="idospedagem")
	private int idHospedagem;

    @Column(name="idquarto")
	private int idQuarto;

    @Column(name="nroAdultos")
	private int nroAdultos;

    @Column(name="nroCriancas")
	private int nroCriancas;

    @Column(name="vlrDiaria")
	private Double vlrDiaria;

    @Column(name="datCheckIn")
	private Timestamp datCheckIn;

    @Column(name="datCheckOut")
	private Timestamp datCheckOut;

    @Column(name="vlrPago")
	private Double vlrPago;

    @Column(name="nomeHospede")
	private String nomeHospede;

    @Column(name="idservico")
	private int idServico;

    @Column(name="qtdConsumo")
	private int qtdConsumo;

    @Column(name="desServico")
	private String desServico;

    @Column(name="vlrUnit")
	private Double vlrUnit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdHospedagem() {
        return idHospedagem;
    }

    public void setIdHospedagem(int idHospedagem) {
        this.idHospedagem = idHospedagem;
    }

    public int getIdQuarto() {
        return idQuarto;
    }

    public void setIdQuarto(int idQuarto) {
        this.idQuarto = idQuarto;
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

    public Timestamp getDatCheckIn() {
        return datCheckIn;
    }

    public void setDatCheckIn(Timestamp datCheckIn) {
        this.datCheckIn = datCheckIn;
    }

    public Timestamp getDatCheckOut() {
        return datCheckOut;
    }

    public void setDatCheckOut(Timestamp datCheckOut) {
        this.datCheckOut = datCheckOut;
    }

    public Double getVlrPago() {
        return vlrPago;
    }

    public void setVlrPago(Double vlrPago) {
        this.vlrPago = vlrPago;
    }

    public String getNomeHospede() {
        return nomeHospede;
    }

    public void setNomeHospede(String nomeHospede) {
        this.nomeHospede = nomeHospede;
    }

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public int getQtdConsumo() {
        return qtdConsumo;
    }

    public void setQtdConsumo(int qtdConsumo) {
        this.qtdConsumo = qtdConsumo;
    }

    public String getDesServico() {
        return desServico;
    }

    public void setDesServico(String desServico) {
        this.desServico = desServico;
    }

    public Double getVlrUnit() {
        return vlrUnit;
    }

    public void setVlrUnit(Double vlrUnit) {
        this.vlrUnit = vlrUnit;
    }
}
