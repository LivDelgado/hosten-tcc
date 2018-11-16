package br.cefetmg.inf.hosten.model.persistence.jpa.domain.rel.views;

import java.io.Serializable;
import java.math.BigDecimal;
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
    , @NamedQuery(name = "QuartoEstado.findByNroQuarto", query = "SELECT q FROM QuartoEstado q WHERE q.nroQuarto = :nroQuarto")
    , @NamedQuery(name = "QuartoEstado.findByNroAdultos", query = "SELECT q FROM QuartoEstado q WHERE q.nroAdultos = :nroAdultos")
    , @NamedQuery(name = "QuartoEstado.findByNroCriancas", query = "SELECT q FROM QuartoEstado q WHERE q.nroCriancas = :nroCriancas")
    , @NamedQuery(name = "QuartoEstado.findByVlrDiaria", query = "SELECT q FROM QuartoEstado q WHERE q.vlrDiaria = :vlrDiaria")
    , @NamedQuery(name = "QuartoEstado.findByIdtOcupado", query = "SELECT q FROM QuartoEstado q WHERE q.idtOcupado = :idtOcupado")
    , @NamedQuery(name = "QuartoEstado.findByDatCheckout", query = "SELECT q FROM QuartoEstado q WHERE q.datCheckout = :datCheckout")})
public class QuartoEstadoJpa implements Serializable {

    @Id
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
