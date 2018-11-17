package br.cefetmg.inf.hosten.model.domain.rel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "quartoestado", catalog = "hosten", schema = "public")
@Immutable
@NamedQueries({
    @NamedQuery(name = "QuartoEstado.findAll", query = "SELECT q FROM QuartoEstado q")
    , @NamedQuery(name = "QuartoEstado.findBySeqHospedagem", query = "SELECT q FROM QuartoEstado q WHERE q.seqHospedagem = :seqHospedagem")
    , @NamedQuery(name = "QuartoEstado.findByNroQuarto", query = "SELECT q FROM QuartoEstado q WHERE q.nroQuarto = :nroQuarto")
    , @NamedQuery(name = "QuartoEstado.findByNroAdultos", query = "SELECT q FROM QuartoEstado q WHERE q.nroAdultos = :nroAdultos")
    , @NamedQuery(name = "QuartoEstado.findByNroCriancas", query = "SELECT q FROM QuartoEstado q WHERE q.nroCriancas = :nroCriancas")
    , @NamedQuery(name = "QuartoEstado.findByVlrDiaria", query = "SELECT q FROM QuartoEstado q WHERE q.vlrDiaria = :vlrDiaria")
    , @NamedQuery(name = "QuartoEstado.findByIdtOcupado", query = "SELECT q FROM QuartoEstado q WHERE q.idtOcupado = :idtOcupado")
    , @NamedQuery(name = "QuartoEstado.findByDatCheckout", query = "SELECT q FROM QuartoEstado q WHERE q.datCheckout = :datCheckout")})
public class QuartoEstado implements Serializable {

    @Id
    @Column(name = "id")
    private long id;
    
    @Column(name = "seqhospedagem")
    private Integer seqhospedagem;
    
    @Column(name = "nroquarto")
    private Short nroQuarto;

    @Column(name = "nroadultos")
    private Short nroAdultos;

    @Column(name = "nrocriancas")
    private Short nroCriancas;

    @Column(name = "vlrdiaria", precision = 7, scale = 2)
    private BigDecimal vlrDiaria;

    @Column(name = "idtocupado")
    private Boolean idtOcupado;

    @Column(name = "datcheckout")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datCheckout;

    public QuartoEstado() {
    }

    public QuartoEstado(Integer seqhospedagem, Short nroQuarto, Short nroAdultos, Short nroCriancas, BigDecimal vlrDiaria, Boolean idtOcupado, Date datCheckout) {
        this.seqhospedagem = seqhospedagem;
        this.nroQuarto = nroQuarto;
        this.nroAdultos = nroAdultos;
        this.nroCriancas = nroCriancas;
        this.vlrDiaria = vlrDiaria;
        this.idtOcupado = idtOcupado;
        this.datCheckout = datCheckout;
    }

    public long getId() {
        return id;
    }

    public Integer getSeqhospedagem() {
        return seqhospedagem;
    }

    public Short getNroQuarto() {
        return nroQuarto;
    }

    public Short getNroAdultos() {
        return nroAdultos;
    }

    public Short getNroCriancas() {
        return nroCriancas;
    }

    public BigDecimal getVlrDiaria() {
        return vlrDiaria;
    }

    public Boolean getIdtOcupado() {
        return idtOcupado;
    }

    public Date getDatCheckout() {
        return datCheckout;
    }
}
