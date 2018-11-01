package br.cefetmg.inf.hosten.model.persistence.jpa.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "servicoarea", catalog = "hosten", schema = "public")
@NamedQueries({
    @NamedQuery(name = "ServicoArea.findAll", query = "SELECT s FROM ServicoArea s")
    , @NamedQuery(name = "ServicoArea.findByCodServicoArea", query = "SELECT s FROM ServicoArea s WHERE s.codServicoArea = :codServicoArea")
    , @NamedQuery(name = "ServicoArea.findByNomServicoArea", query = "SELECT s FROM ServicoArea s WHERE s.nomServicoArea = :nomServicoArea")})
public class ServicoAreaJpa implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "codservicoarea", nullable = false, length = 3)
    private String codServicoArea;

    @Basic(optional = false)
    @Column(name = "nomservicoarea", nullable = false, length = 40)
    private String nomServicoArea;

    @OneToMany(mappedBy = "codServicoArea", cascade = CascadeType.ALL)
    private final List<ServicoJpa> servicos = new ArrayList<>();

    public ServicoAreaJpa() {
    }

    public ServicoAreaJpa(String codservicoarea) {
        this.codServicoArea = codservicoarea;
    }

    public ServicoAreaJpa(String codServicoArea, String nomServicoArea) {
        this.codServicoArea = codServicoArea;
        this.nomServicoArea = nomServicoArea;
    }

    public String getCodServicoArea() {
        return codServicoArea;
    }

    public void setCodServicoArea(String codServicoArea) {
        this.codServicoArea = codServicoArea;
    }

    public String getNomServicoArea() {
        return nomServicoArea;
    }

    public void setNomServicoArea(String nomServicoArea) {
        this.nomServicoArea = nomServicoArea;
    }

    public List<ServicoJpa> getServicos() {
        return servicos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codServicoArea != null ? codServicoArea.hashCode() : 0);
        return hash;
    }

    public void addServico(ServicoJpa servico) {
        servico.setCodServicoArea(this);
        this.servicos.add(servico);
    }

    public void removeServico(ServicoJpa serv, ServicoAreaJpa saNov) {
        if (servicos.contains(serv)) {
            saNov.addServico(serv);
            this.servicos.remove(serv);
        }
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServicoAreaJpa)) {
            return false;
        }
        ServicoAreaJpa other = (ServicoAreaJpa) object;
        if ((this.codServicoArea == null && other.codServicoArea != null) || (this.codServicoArea != null && !this.codServicoArea.equals(other.codServicoArea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.cefetmg.inf.hosten.model.domain.Servicoarea[ codservicoarea=" + codServicoArea + " ]";
    }

}
