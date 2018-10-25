package br.cefetmg.inf.hosten.model.domain.rel;

import br.cefetmg.inf.hosten.model.domain.embeddable.QuartoHospedagemId;
import br.cefetmg.inf.hosten.model.domain.Hospedagem;
import br.cefetmg.inf.hosten.model.domain.Quarto;
import br.cefetmg.inf.hosten.model.domain.Servico;
import br.cefetmg.inf.hosten.model.domain.Usuario;
import br.cefetmg.inf.hosten.model.domain.embeddable.QuartoConsumoId;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "quartohospedagem", catalog = "hosten", schema = "public")
@NamedQueries({
    @NamedQuery(name = "QuartoHospedagem.findAll", query = "SELECT q FROM QuartoHospedagem q")
    , @NamedQuery(name = "QuartoHospedagem.findBySeqHospedagem", 
            query = "SELECT q FROM QuartoHospedagem q WHERE q.id.seqHospedagem = :seqHospedagem")
    , @NamedQuery(name = "QuartoHospedagem.findByNroQuarto", 
            query = "SELECT q FROM QuartoHospedagem q WHERE q.id.nroQuarto = :nroQuarto")
    , @NamedQuery(name = "QuartoHospedagem.findByNroAdultos", 
            query = "SELECT q FROM QuartoHospedagem q WHERE q.nroAdultos = :nroAdultos")
    , @NamedQuery(name = "QuartoHospedagem.findByNroCriancas", 
            query = "SELECT q FROM QuartoHospedagem q WHERE q.nroCriancas = :nroCriancas")
    , @NamedQuery(name = "QuartoHospedagem.findByVlrDiaria", 
            query = "SELECT q FROM QuartoHospedagem q WHERE q.vlrDiaria = :vlrDiaria")
    , @NamedQuery(name = "QuartoHospedagem.fetchUltimoRegistroQuarto",
            query = "SELECT qh FROM QuartoHospedagem qh "
                    + "JOIN qh.hospedagem h "
                    + "WHERE qh.id.nroQuarto = :nroQuarto "
                    + "ORDER BY h.datCheckin DESC")})
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

    @ManyToOne(optional = false)
    @JoinColumn(name = "seqhospedagem", referencedColumnName = "seqhospedagem", nullable = false)
    @MapsId("seqHospedagem")
    private Hospedagem hospedagem;

    @ManyToOne(optional = false)
    @JoinColumn(name = "nroquarto", referencedColumnName = "nroquarto", nullable = false)
    @MapsId("nroQuarto")
    private Quarto quarto;

    @OneToMany(mappedBy = "quartoHospedagem", cascade = CascadeType.ALL)
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
    
    public void addQuartoConsumo(Usuario usuario, Date datConsumo, short qtdConsumo, Servico servico) {
        QuartoConsumoId qcid = new QuartoConsumoId(this, datConsumo);
        QuartoConsumo qc = new QuartoConsumo(qcid, qtdConsumo, servico, usuario);

        this.quartoConsumos.add(qc);
        usuario.getQuartoConsumos().add(qc);
        servico.getQuartoConsumos().add(qc);
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
