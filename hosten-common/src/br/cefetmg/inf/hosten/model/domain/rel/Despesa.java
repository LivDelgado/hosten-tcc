package br.cefetmg.inf.hosten.model.domain.rel;

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
@Table(name = "despesa", catalog = "hosten", schema = "public")
@Immutable
@NamedQueries({
    @NamedQuery(name = "Despesa.findAll", query = "SELECT d FROM Despesa d")
    , @NamedQuery(name = "Despesa.findById", query = "SELECT d FROM Despesa d WHERE d.id = :id")
    , @NamedQuery(name = "Despesa.findBySeqHospedagem", query = "SELECT d FROM Despesa d WHERE d.seqHospedagem = :seqHospedagem")
    , @NamedQuery(name = "Despesa.findByNroQuarto", query = "SELECT d FROM Despesa d WHERE d.nroQuarto = :nroQuarto")
    , @NamedQuery(name = "Despesa.findByNroAdultos", query = "SELECT d FROM Despesa d WHERE d.nroAdultos = :nroAdultos")
    , @NamedQuery(name = "Despesa.findByNroCriancas", query = "SELECT d FROM Despesa d WHERE d.nroCriancas = :nroCriancas")
    , @NamedQuery(name = "Despesa.findByVlrDiaria", query = "SELECT d FROM Despesa d WHERE d.vlrDiaria = :vlrDiaria")
    , @NamedQuery(name = "Despesa.findByDatCheckin", query = "SELECT d FROM Despesa d WHERE d.datCheckin = :datCheckin")
    , @NamedQuery(name = "Despesa.findByDatCheckout", query = "SELECT d FROM Despesa d WHERE d.datCheckout = :datCheckout")
    , @NamedQuery(name = "Despesa.findByVlrPago", query = "SELECT d FROM Despesa d WHERE d.vlrPago = :vlrPago")
    , @NamedQuery(name = "Despesa.findByNomHospede", query = "SELECT d FROM Despesa d WHERE d.nomHospede = :nomHospede")
    , @NamedQuery(name = "Despesa.findBySeqServico", query = "SELECT d FROM Despesa d WHERE d.seqServico = :seqServico")
    , @NamedQuery(name = "Despesa.findByQtdConsumo", query = "SELECT d FROM Despesa d WHERE d.qtdConsumo = :qtdConsumo")
    , @NamedQuery(name = "Despesa.findByDesServico", query = "SELECT d FROM Despesa d WHERE d.desServico = :desServico")
    , @NamedQuery(name = "Despesa.findByVlrUnit", query = "SELECT d FROM Despesa d WHERE d.vlrUnit = :vlrUnit")
    , @NamedQuery(
            name = "Despesa.findBySeqHospedagemAndNroQuarto",
            query = "SELECT d FROM Despesa d WHERE d.seqHospedagem = :seqHospedagem AND d.nroQuarto = :nroQuarto")
})
public class Despesa implements Serializable {

    @Id
    @Column(name = "id")
    private long id;
    
    @Column(name = "seqhospedagem")
    private Integer seqHospedagem;

    @Column(name = "nroquarto")
    private Short nroQuarto;

    @Column(name = "nroadultos")
    private Short nroAdultos;

    @Column(name = "nrocriancas")
    private Short nroCriancas;

    @Column(name = "vlrdiaria", precision = 7, scale = 2)
    private BigDecimal vlrDiaria;

    @Column(name = "datcheckin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datCheckin;

    @Column(name = "datcheckout")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datCheckout;

    @Column(name = "vlrpago", precision = 7, scale = 2)
    private BigDecimal vlrPago;

    @Column(name = "nomhospede", length = 90)
    private String nomHospede;

    @Column(name = "seqservico")
    private Short seqServico;

    @Column(name = "qtdconsumo")
    private Short qtdConsumo;

    @Column(name = "desservico", length = 40)
    private String desServico;

    @Column(name = "vlrunit", precision = 7, scale = 2)
    private BigDecimal vlrUnit;

    public Despesa() {
    }

    public Despesa(long id, Integer seqHospedagem, Short nroQuarto, Short nroAdultos, Short nroCriancas, BigDecimal vlrDiaria, Date datCheckin, Date datCheckout, BigDecimal vlrPago, String nomHospede, Short seqServico, Short qtdConsumo, String desServico, BigDecimal vlrUnit) {
        this.id = id;
        this.seqHospedagem = seqHospedagem;
        this.nroQuarto = nroQuarto;
        this.nroAdultos = nroAdultos;
        this.nroCriancas = nroCriancas;
        this.vlrDiaria = vlrDiaria;
        this.datCheckin = datCheckin;
        this.datCheckout = datCheckout;
        this.vlrPago = vlrPago;
        this.nomHospede = nomHospede;
        this.seqServico = seqServico;
        this.qtdConsumo = qtdConsumo;
        this.desServico = desServico;
        this.vlrUnit = vlrUnit;
    }

    public long getId() {
        return id;
    }

    public Integer getSeqHospedagem() {
        return seqHospedagem;
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

    public Date getDatCheckin() {
        return datCheckin;
    }

    public Date getDatCheckout() {
        return datCheckout;
    }

    public BigDecimal getVlrPago() {
        return vlrPago;
    }

    public String getNomHospede() {
        return nomHospede;
    }

    public Short getSeqServico() {
        return seqServico;
    }

    public Short getQtdConsumo() {
        return qtdConsumo;
    }

    public String getDesServico() {
        return desServico;
    }

    public BigDecimal getVlrUnit() {
        return vlrUnit;
    }
}
