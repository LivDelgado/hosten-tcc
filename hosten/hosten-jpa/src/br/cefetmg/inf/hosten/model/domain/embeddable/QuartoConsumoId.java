package br.cefetmg.inf.hosten.model.domain.embeddable;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class QuartoConsumoId implements Serializable {

    @Basic(optional = false)
    @Column(name = "seqhospedagem", nullable = false)
    private int seqHospedagem;

    @Basic(optional = false)
    @Column(name = "nroquarto", nullable = false)
    private short nroQuarto;

    @Basic(optional = false)
    @Column(name = "datconsumo", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date datConsumo;

    public QuartoConsumoId() {
    }

    public QuartoConsumoId(int seqhospedagem, short nroquarto, Date datconsumo) {
        this.seqHospedagem = seqhospedagem;
        this.nroQuarto = nroquarto;
        this.datConsumo = datconsumo;
    }

    public int getSeqHospedagem() {
        return seqHospedagem;
    }

    public void setSeqHospedagem(int seqHospedagem) {
        this.seqHospedagem = seqHospedagem;
    }

    public short getNroQuarto() {
        return nroQuarto;
    }

    public void setNroQuarto(short nroQuarto) {
        this.nroQuarto = nroQuarto;
    }

    public Date getDatConsumo() {
        return datConsumo;
    }

    public void setDatConsumo(Date datConsumo) {
        this.datConsumo = datConsumo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) seqHospedagem;
        hash += (int) nroQuarto;
        hash += (datConsumo != null ? datConsumo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuartoConsumoId)) {
            return false;
        }
        QuartoConsumoId other = (QuartoConsumoId) object;
        if (this.seqHospedagem != other.seqHospedagem) {
            return false;
        }
        if (this.nroQuarto != other.nroQuarto) {
            return false;
        }
        if ((this.datConsumo == null && other.datConsumo != null) || (this.datConsumo != null && !this.datConsumo.equals(other.datConsumo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.cefetmg.inf.hosten.model.domain.QuartoconsumoPK[ seqhospedagem=" + seqHospedagem + ", nroquarto=" + nroQuarto + ", datconsumo=" + datConsumo + " ]";
    }
    
}
