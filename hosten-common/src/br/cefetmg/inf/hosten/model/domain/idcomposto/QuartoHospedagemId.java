package br.cefetmg.inf.hosten.model.domain.idcomposto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class QuartoHospedagemId implements Serializable {

    @Basic(optional = false)
    @Column(name = "seqhospedagem", nullable = false)
    private int seqHospedagem;

    @Basic(optional = false)
    @Column(name = "nroquarto", nullable = false)
    private short nroQuarto;

    public QuartoHospedagemId() {
    }

    public QuartoHospedagemId(int seqhospedagem, short nroquarto) {
        this.seqHospedagem = seqhospedagem;
        this.nroQuarto = nroquarto;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) seqHospedagem;
        hash += (int) nroQuarto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuartoHospedagemId)) {
            return false;
        }
        QuartoHospedagemId other = (QuartoHospedagemId) object;
        if (this.seqHospedagem != other.seqHospedagem) {
            return false;
        }
        if (this.nroQuarto != other.nroQuarto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.cefetmg.inf.hosten.model.domain.QuartohospedagemPK[ seqhospedagem=" + seqHospedagem + ", nroquarto=" + nroQuarto + " ]";
    }
    
}
