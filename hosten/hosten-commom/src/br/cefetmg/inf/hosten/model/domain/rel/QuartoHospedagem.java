package br.cefetmg.inf.hosten.model.domain.rel;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "quartohospedagem")
public class QuartoHospedagem implements Serializable {
    @Column(name="id")
	private int id;

    @Column(name="idHospedagem")
	private int idHospedagem;

    @Column(name="idQuarto")
	private int idQuarto;

    @Column(name="nroAdultos")
	private int nroAdultos;

    @Column(name="nroCriancas")
	private int nroCriancas;

    @Column(name="vlrDiaria")
	private Double vlrDiaria;

}
