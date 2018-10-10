package br.cefetmg.inf.hosten.model.domain.rel;

import br.cefetmg.inf.hosten.model.domain.embeddable.QuartoHospedagemId;
import br.cefetmg.inf.hosten.model.domain.Hospedagem;
import br.cefetmg.inf.hosten.model.domain.Quarto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "quartohospedagem", catalog = "hosten", schema = "public")
@NamedQueries({
    @NamedQuery(name = "QuartoHospedagem.findAll", query = "SELECT q FROM QuartoHospedagem q")
    , @NamedQuery(name = "QuartoHospedagem.findBySeqhospedagem", query = "SELECT q FROM QuartoHospedagem q WHERE q.id.seqHospedagem = :seqHospedagem")
    , @NamedQuery(name = "QuartoHospedagem.findByNroquarto", query = "SELECT q FROM QuartoHospedagem q WHERE q.id.nroQuarto = :nroQuarto")
    , @NamedQuery(name = "QuartoHospedagem.findByNroadultos", query = "SELECT q FROM QuartoHospedagem q WHERE q.nroAdultos = :nroAdultos")
    , @NamedQuery(name = "QuartoHospedagem.findByNrocriancas", query = "SELECT q FROM QuartoHospedagem q WHERE q.nroCriancas = :nroCriancas")
    , @NamedQuery(name = "QuartoHospedagem.findByVlrdiaria", query = "SELECT q FROM QuartoHospedagem q WHERE q.vlrDiaria = :vlrDiaria")})
public class QuartoHospedagem implements Serializable {

    @EmbeddedId
    protected QuartoHospedagemId id;
    
    @Basic(optional = false)
    @Column(name = "nroadultos", nullable = false)
    private short nroAdultos;
    
    @Basic(optional = false)
    @Column(name = "nrocriancas", nullable = false)
    private short nroCriancas;
    
    @Basic(optional = false)
    @Column(name = "vlrdiaria", nullable = false, precision = 7, scale = 2)
    private BigDecimal vlrDiaria;
    
    @JoinColumn(name = "seqhospedagem", referencedColumnName = "seqhospedagem", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Hospedagem hospedagem;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "nroquarto", referencedColumnName = "nroquarto", nullable = false, insertable = false, updatable = false)
    private Quarto quarto;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quartohospedagem")
    private Set<QuartoConsumo> quartoConsumos;

    public QuartoHospedagem() {
    }

    public QuartoHospedagem(QuartoHospedagemId id) {
        this.id = id;
    }

    public QuartoHospedagem(QuartoHospedagemId id, short nroadultos, short nrocriancas, BigDecimal vlrdiaria) {
        this.id = id;
        this.nroAdultos = nroadultos;
        this.nroCriancas = nrocriancas;
        this.vlrDiaria = vlrdiaria;
    }

    public QuartoHospedagem(int seqhospedagem, short nroquarto) {
        this.id = new QuartoHospedagemId(seqhospedagem, nroquarto);
    }

    public QuartoHospedagemId getId() {
        return id;
    }

    public void setId(QuartoHospedagemId id) {
        this.id = id;
    }

    public short getNroAdultos() {
        return nroAdultos;
    }

    public void setNroAdultos(short nroAdultos) {
        this.nroAdultos = nroAdultos;
    }

    public short getNroCriancas() {
        return nroCriancas;
    }

    public void setNroCriancas(short nroCriancas) {
        this.nroCriancas = nroCriancas;
    }

    public BigDecimal getVlrDiaria() {
        return vlrDiaria;
    }

    public void setVlrDiaria(BigDecimal vlrDiaria) {
        this.vlrDiaria = vlrDiaria;
    }

    public Hospedagem getHospedagem() {
        return hospedagem;
    }

    public void setHospedagem(Hospedagem hospedagem) {
        this.hospedagem = hospedagem;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public Set<QuartoConsumo> getQuartoConsumos() {
        return quartoConsumos;
    }

    public void setQuartoConsumos(Set<QuartoConsumo> quartoConsumos) {
        this.quartoConsumos = quartoConsumos;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuartoHospedagem)) {
            return false;
        }
        QuartoHospedagem other = (QuartoHospedagem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.cefetmg.inf.hosten.model.domain.Quartohospedagem[ quartohospedagemPK=" + id + " ]";
    }
    
}
