package br.cefetmg.inf.hosten.model.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hospede")
public class Hospede implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "codcpf")
    private String codCPF;

    @Column(name = "nomhospede")
    private String nomHospede;

    @Column(name = "destelefone")
    private String desTelefone;

    @Column(name = "desemail")
    private String desEmail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodCPF() {
        return codCPF;
    }

    public void setCodCPF(String codCPF) {
        this.codCPF = codCPF;
    }

    public String getNomHospede() {
        return nomHospede;
    }

    public void setNomHospede(String nomHospede) {
        this.nomHospede = nomHospede;
    }

    public String getDesTelefone() {
        return desTelefone;
    }

    public void setDesTelefone(String desTelefone) {
        this.desTelefone = desTelefone;
    }

    public String getDesEmail() {
        return desEmail;
    }

    public void setDesEmail(String desEmail) {
        this.desEmail = desEmail;
    }
}
