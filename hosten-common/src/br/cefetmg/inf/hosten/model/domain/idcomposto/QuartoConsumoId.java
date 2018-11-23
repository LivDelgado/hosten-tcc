package br.cefetmg.inf.hosten.model.domain.idcomposto;

import br.cefetmg.inf.hosten.model.domain.rel.QuartoHospedagem;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class QuartoConsumoId implements Serializable {

    private QuartoHospedagem quartoHospedagem;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp datConsumo;

    public QuartoConsumoId() {
    }

    public QuartoConsumoId(QuartoHospedagem quartoHospedagem, Timestamp datConsumo) {
        this.quartoHospedagem = quartoHospedagem;
        this.datConsumo = datConsumo;
    }

    public QuartoHospedagem getQuartoHospedagem() {
        return quartoHospedagem;
    }

    public Timestamp getDatConsumo() {
        return datConsumo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) quartoHospedagem.getId().getSeqHospedagem();
        hash += (int) quartoHospedagem.getId().getNroQuarto();
        hash += (datConsumo != null ? datConsumo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id2 fields are not set
        if (!(object instanceof QuartoConsumoId)) {
            return false;
        }
        QuartoConsumoId other = (QuartoConsumoId) object;
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
        return "br.cefetmg.inf.hosten.model.domain.QuartoconsumoPK[ seqhospedagem="
                + this.quartoHospedagem.getId().getSeqHospedagem() + ", nroquarto="
                + this.quartoHospedagem.getId().getNroQuarto() + ", datconsumo=" + datConsumo + " ]";
    }
}
