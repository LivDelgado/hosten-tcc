package br.cefetmg.inf.hosten.model.domain.rel;

import br.cefetmg.inf.hosten.model.domain.Servico;
import br.cefetmg.inf.hosten.model.domain.Usuario;
import br.cefetmg.inf.hosten.model.domain.idcomposto.QuartoConsumoId;
import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "quartoconsumo", catalog = "hosten", schema = "public")
@IdClass(br.cefetmg.inf.hosten.model.domain.idcomposto.QuartoConsumoId.class)
@NamedQueries({
    @NamedQuery(name = "QuartoConsumo.findAll", query = "SELECT q FROM QuartoConsumo q")
    , @NamedQuery(name = "QuartoConsumo.findBySeqHospedagem",
            query = "SELECT q FROM QuartoConsumo q WHERE q.quartoHospedagem.id.seqHospedagem = :seqHospedagem")
    , @NamedQuery(name = "QuartoConsumo.findByNroQuarto",
            query = "SELECT q FROM QuartoConsumo q WHERE q.quartoHospedagem.id.nroQuarto = :nroQuarto")
    , @NamedQuery(name = "QuartoConsumo.findByDatConsumo",
            query = "SELECT q FROM QuartoConsumo q WHERE q.datConsumo = :datConsumo")
    , @NamedQuery(name = "QuartoConsumo.findByQtdConsumo",
            query = "SELECT q FROM QuartoConsumo q WHERE q.qtdConsumo = :qtdconsumo")})
public class QuartoConsumo implements Serializable {

    @Id
    @ManyToOne(optional = false)
    @JoinColumns({
        @JoinColumn(name = "seqhospedagem", referencedColumnName = "seqhospedagem",
                nullable = false, insertable = false, updatable = false)
        , @JoinColumn(name = "nroquarto", referencedColumnName = "nroquarto",
                nullable = false, insertable = false, updatable = false)})
    private QuartoHospedagem quartoHospedagem;

    @Id
    @Basic(optional = false)
    @Column(name = "datconsumo", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date datConsumo;

    @Basic(optional = false)
    @Column(name = "qtdconsumo", nullable = false)
    private short qtdConsumo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "seqservico", referencedColumnName = "seqservico", nullable = false)
    private Servico servico;

    @ManyToOne
    @JoinColumn(name = "codusuarioregistro", referencedColumnName = "codusuario")
    private Usuario usuarioRegistro;

    public QuartoConsumo() {
    }

    public QuartoConsumo(QuartoConsumoId id, short qtdConsumo, Servico seqServico, Usuario codUsuarioRegistro) {
        quartoHospedagem = id.getQuartoHospedagem();
        datConsumo = id.getDatConsumo();
        this.qtdConsumo = qtdConsumo;
        this.servico = seqServico;
        this.usuarioRegistro = codUsuarioRegistro;
    }

    public QuartoHospedagem getQuartoHospedagem() {
        return quartoHospedagem;
    }

    public Date getDatConsumo() {
        return datConsumo;
    }

    public short getQtdConsumo() {
        return qtdConsumo;
    }

    public void setQtdConsumo(short qtdConsumo) {
        this.qtdConsumo = qtdConsumo;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Usuario getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(Usuario usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (quartoHospedagem != null ? quartoHospedagem.hashCode() : 0);
        hash += (datConsumo != null ? datConsumo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuartoConsumo)) {
            return false;
        }
        QuartoConsumo other = (QuartoConsumo) object;
        if (this.quartoHospedagem.getId().getSeqHospedagem() != other.quartoHospedagem.getId().getSeqHospedagem()) {
            return false;
        }
        if (this.quartoHospedagem.getId().getNroQuarto() != other.quartoHospedagem.getId().getNroQuarto()) {
            return false;
        }
        if ((this.datConsumo == null && other.datConsumo != null)
                || (this.datConsumo != null && !this.datConsumo.equals(other.datConsumo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "QuartoConsumo = {quartoHospedagem.id.seqHospedagem=[" + this.quartoHospedagem.getId().getSeqHospedagem() + "], "
                + "quartoHospedagem.id.nroQuarto=[" + this.quartoHospedagem.getId().getNroQuarto() + ", "
                + "datConsumo=[" + this.datConsumo.toString() + "]}";
    }
}
