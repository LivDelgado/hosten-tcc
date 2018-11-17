package br.cefetmg.inf.hosten.model.domain;

import br.cefetmg.inf.hosten.model.domain.rel.QuartoConsumo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "servico", catalog = "hosten", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Servico.findAll", query = "SELECT s FROM Servico s")
    , @NamedQuery(name = "Servico.findBySeqServico", query = "SELECT s FROM Servico s WHERE s.seqServico = :seqServico")
    , @NamedQuery(name = "Servico.findByDesServico", query = "SELECT s FROM Servico s WHERE s.desServico = :desServico")
    , @NamedQuery(name = "Servico.findByVlrUnit", query = "SELECT s FROM Servico s WHERE s.vlrUnit = :vlrUnit")})
public class Servico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "seqservico", nullable = false)
    private Short seqServico;

    @Basic(optional = false)
    @Column(name = "desservico", nullable = false, length = 40)
    private String desServico;

    @Basic(optional = false)
    @Column(name = "vlrunit", nullable = false, precision = 7, scale = 2)
    private BigDecimal vlrUnit;

    @OneToMany(mappedBy = "servico", cascade = CascadeType.ALL)
    private final List<QuartoConsumo> quartoConsumos = new ArrayList<>();

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "codservicoarea", nullable = false)
    private ServicoArea servicoArea;

    public Servico() {
    }

    public Servico(Short seqServico) {
        this.seqServico = seqServico;
    }

    public Servico(String desServico, BigDecimal vlrUnit) {
        this.desServico = desServico;
        this.vlrUnit = vlrUnit;
    }

    public Short getSeqServico() {
        return seqServico;
    }

    public void setSeqServico(Short seqServico) {
        this.seqServico = seqServico;
    }

    public String getDesServico() {
        return desServico;
    }

    public void setDesServico(String desServico) {
        this.desServico = desServico;
    }

    public BigDecimal getVlrUnit() {
        return vlrUnit;
    }

    public void setVlrUnit(BigDecimal vlrUnit) {
        this.vlrUnit = vlrUnit;
    }

    public List<QuartoConsumo> getQuartoConsumos() {
        return quartoConsumos;
    }

    public ServicoArea getServicoArea() {
        return servicoArea;
    }

    public void setServicoArea(ServicoArea servicoArea) {
        this.servicoArea = servicoArea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seqServico != null ? seqServico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servico)) {
            return false;
        }
        Servico other = (Servico) object;
        if ((this.seqServico == null && other.seqServico != null) || (this.seqServico != null && !this.seqServico.equals(other.seqServico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.cefetmg.inf.hosten.model.domain.Servico[ seqservico=" + seqServico + " ]";
    }

}
