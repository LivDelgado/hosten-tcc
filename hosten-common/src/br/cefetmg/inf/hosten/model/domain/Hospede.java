package br.cefetmg.inf.hosten.model.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "hospede", catalog = "hosten", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Hospede.findAll", query = "SELECT h FROM Hospede h")
    , @NamedQuery(name = "Hospede.findByCodCPF", query = "SELECT h FROM Hospede h WHERE h.codCPF = :codCPF")
    , @NamedQuery(name = "Hospede.findByNomHospede", query = "SELECT h FROM Hospede h WHERE h.nomHospede = :nomHospede")
    , @NamedQuery(name = "Hospede.findByDesTelefone", query = "SELECT h FROM Hospede h WHERE h.desTelefone = :desTelefone")
    , @NamedQuery(name = "Hospede.findByDesEmail", query = "SELECT h FROM Hospede h WHERE h.desEmail = :desEmail")})
public class Hospede implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "codcpf", nullable = false, length = 14)
    private String codCPF;

    @Basic(optional = false)
    @Column(name = "nomhospede", nullable = false, length = 90)
    private String nomHospede;

    @Basic(optional = false)
    @Column(name = "destelefone", nullable = false, length = 14)
    private String desTelefone;

    @Basic(optional = false)
    @Column(name = "desemail", nullable = false, length = 90)
    private String desEmail;

    @OneToMany(mappedBy = "hospede")
    private List<Hospedagem> hospedagens = new ArrayList<>();

    public Hospede() {
    }

    public Hospede(String codcpf) {
        this.codCPF = codcpf;
    }

    public Hospede(String codCPF, String nomHospede, String desTelefone, String desEmail) {
        this.codCPF = codCPF;
        this.nomHospede = nomHospede;
        this.desTelefone = desTelefone;
        this.desEmail = desEmail;
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

    public List<Hospedagem> getHospedagens() {
        return hospedagens;
    }

    public void setHospedagens(List<Hospedagem> hospedagens) {
        this.hospedagens = hospedagens;
    }

    public void addHospedagem(Hospedagem hospedagem) {
        hospedagem.setHospede(this);
        this.hospedagens.add(hospedagem);
    }

    public void removeHospedagem(Hospede hospedeNov, Hospedagem hospedagem) {
        if (hospedagens.contains(hospedagem)) {
            hospedeNov.addHospedagem(hospedagem);
            this.hospedagens.remove(hospedagem);
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codCPF != null ? codCPF.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hospede)) {
            return false;
        }
        Hospede other = (Hospede) object;
        if ((this.codCPF == null && other.codCPF != null) || (this.codCPF != null && !this.codCPF.equals(other.codCPF))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Hospede={codcpf=[" + codCPF + "], nomHospede=[" + nomHospede + ", "
                + "desTelefone=[" + desTelefone + "], desEmail=[" + desEmail + "]}";
    }

}
