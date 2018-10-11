package br.cefetmg.inf.hosten.model.domain.rel;

import br.cefetmg.inf.hosten.model.domain.embeddable.QuartoConsumoId;
import br.cefetmg.inf.hosten.model.domain.Servico;
import br.cefetmg.inf.hosten.model.domain.Usuario;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "quartoconsumo", catalog = "hosten", schema = "public")
@NamedQueries({
    @NamedQuery(name = "QuartoConsumo.findAll", query = "SELECT q FROM QuartoConsumo q")
    , @NamedQuery(name = "QuartoConsumo.findBySeqhospedagem", query = "SELECT q FROM QuartoConsumo q WHERE q.id.seqHospedagem = :seqHospedagem")
    , @NamedQuery(name = "QuartoConsumo.findByNroquarto", query = "SELECT q FROM QuartoConsumo q WHERE q.id.nroQuarto = :nroQuarto")
    , @NamedQuery(name = "QuartoConsumo.findByDatconsumo", query = "SELECT q FROM QuartoConsumo q WHERE q.id.datConsumo = :datConsumo")
    , @NamedQuery(name = "QuartoConsumo.findByQtdconsumo", query = "SELECT q FROM QuartoConsumo q WHERE q.qtdConsumo = :qtdconsumo")})
public class QuartoConsumo implements Serializable {

    @EmbeddedId
    protected QuartoConsumoId id;

    @Basic(optional = false)
    @Column(name = "qtdconsumo", nullable = false)
    private short qtdConsumo;

    @ManyToOne(optional = false)
    @JoinColumns({
        @JoinColumn(name = "seqhospedagem", referencedColumnName = "seqhospedagem", nullable = false, insertable = false, updatable = false)
        , @JoinColumn(name = "nroquarto", referencedColumnName = "nroquarto", nullable = false, insertable = false, updatable = false)})
    private QuartoHospedagem quartoHospedagem;

    @ManyToOne(optional = false)
    @JoinColumn(name = "seqservico", referencedColumnName = "seqservico", nullable = false)
    private Servico seqServico;

    @ManyToOne
    @JoinColumn(name = "codusuarioregistro", referencedColumnName = "codusuario")
    private Usuario codUsuarioRegistro;

    public QuartoConsumo() {
    }

    public QuartoConsumo(QuartoConsumoId id) {
        this.id = id;
    }

    public QuartoConsumo(QuartoHospedagem quartoHospedagem, Date datconsumo) {
        this.id = new QuartoConsumoId(quartoHospedagem.getId().getSeqHospedagem(), quartoHospedagem.getId().getNroQuarto(), datconsumo);
    }

    public QuartoConsumo(QuartoConsumoId id, short qtdConsumo, QuartoHospedagem quartoHospedagem, Servico seqServico, Usuario codUsuarioRegistro) {
        this.id = id;
        this.qtdConsumo = qtdConsumo;
        this.quartoHospedagem = quartoHospedagem;
        this.seqServico = seqServico;
        this.codUsuarioRegistro = codUsuarioRegistro;
    }

    public QuartoConsumoId getId() {
        return id;
    }

    public void setId(QuartoConsumoId id) {
        this.id = id;
    }

    public short getQtdConsumo() {
        return qtdConsumo;
    }

    public void setQtdConsumo(short qtdConsumo) {
        this.qtdConsumo = qtdConsumo;
    }

    public QuartoHospedagem getQuartoHospedagem() {
        return quartoHospedagem;
    }

    public void setQuartoHospedagem(QuartoHospedagem quartoHospedagem) {
        this.quartoHospedagem = quartoHospedagem;
    }

    public Servico getSeqServico() {
        return seqServico;
    }

    public void setSeqServico(Servico seqServico) {
        this.seqServico = seqServico;
    }

    public Usuario getCodUsuarioRegistro() {
        return codUsuarioRegistro;
    }

    public void setCodUsuarioRegistro(Usuario codUsuarioRegistro) {
        this.codUsuarioRegistro = codUsuarioRegistro;
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
        if (!(object instanceof QuartoConsumo)) {
            return false;
        }
        QuartoConsumo other = (QuartoConsumo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.cefetmg.inf.hosten.model.domain.Quartoconsumo[ quartoconsumoPK=" + id + " ]";
    }

}
