package br.cefetmg.inf.hosten.model.persistence.jpa.domain;

import br.cefetmg.inf.hosten.model.persistence.jpa.domain.rel.QuartoHospedagem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "quarto", catalog = "hosten", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Quarto.findAll", query = "SELECT q FROM Quarto q")
    , @NamedQuery(name = "Quarto.findByNroQuarto", query = "SELECT q FROM Quarto q WHERE q.nroQuarto = :nroQuarto")
    , @NamedQuery(name = "Quarto.findByIdtOcupado", query = "SELECT q FROM Quarto q WHERE q.idtOcupado = :idtOcupado")})
public class Quarto implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "nroquarto", nullable = false)
    private Short nroQuarto;

    @Column(name = "idtocupado")
    private Boolean idtOcupado;

    @OneToMany(mappedBy = "quarto", cascade = CascadeType.ALL)
    private final List<QuartoHospedagem> quartoHospedagens = new ArrayList<>();

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "codcategoria", referencedColumnName = "codcategoria", nullable = false)
    private CategoriaQuarto codCategoria;

    public Quarto() {
    }

    public Quarto(Short nroquarto) {
        this.nroQuarto = nroquarto;
    }

    public Quarto(Short nroQuarto, Boolean idtOcupado) {
        this.nroQuarto = nroQuarto;
        this.idtOcupado = idtOcupado;
    }

    public Short getNroQuarto() {
        return nroQuarto;
    }

    public void setNroQuarto(Short nroQuarto) {
        this.nroQuarto = nroQuarto;
    }

    public Boolean getIdtOcupado() {
        return idtOcupado;
    }

    public void setIdtOcupado(Boolean idtOcupado) {
        this.idtOcupado = idtOcupado;
    }

    public List<QuartoHospedagem> getQuartoHospedagens() {
        return quartoHospedagens;
    }

    public CategoriaQuarto getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(CategoriaQuarto codCategoria) {
        this.codCategoria = codCategoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nroQuarto != null ? nroQuarto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Quarto)) {
            return false;
        }
        Quarto other = (Quarto) object;
        if ((this.nroQuarto == null && other.nroQuarto != null) || (this.nroQuarto != null && !this.nroQuarto.equals(other.nroQuarto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.cefetmg.inf.hosten.model.domain.Quarto[ nroquarto=" + nroQuarto + " ]";
    }

}
