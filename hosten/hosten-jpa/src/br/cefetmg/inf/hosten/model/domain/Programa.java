package br.cefetmg.inf.hosten.model.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "programa", catalog = "hosten", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Programa.findAll", query = "SELECT p FROM Programa p")
    , @NamedQuery(name = "Programa.findByCodPrograma", query = "SELECT p FROM Programa p WHERE p.codPrograma = :codPrograma")
    , @NamedQuery(name = "Programa.findByDesPrograma", query = "SELECT p FROM Programa p WHERE p.desPrograma = :desPrograma")})
public class Programa implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "codprograma", nullable = false, length = 3)
    private String codPrograma;

    @Basic(optional = false)
    @Column(name = "desprograma", nullable = false, length = 200)
    private String desPrograma;

    @ManyToMany
    @JoinTable(name = "cargoprograma",
            joinColumns = {
                @JoinColumn(name = "codprograma", referencedColumnName = "codprograma", nullable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "codcargo", referencedColumnName = "codcargo", nullable = false)})
    private final Set<Cargo> cargos = new HashSet<>();

    public Programa() {
    }

    public Programa(String codprograma) {
        this.codPrograma = codprograma;
    }

    public Programa(String codprograma, String desprograma) {
        this.codPrograma = codprograma;
        this.desPrograma = desprograma;
    }

    public String getCodPrograma() {
        return codPrograma;
    }

    public void setCodPrograma(String codPrograma) {
        this.codPrograma = codPrograma;
    }

    public String getDesPrograma() {
        return desPrograma;
    }

    public void setDesPrograma(String desPrograma) {
        this.desPrograma = desPrograma;
    }

    public Set<Cargo> getCargos() {
        return cargos;
    }
    
    public void addCargo(Cargo cargo) {
        this.cargos.add(cargo);
        cargo.getProgramas().add(this);
    }
    
    public void removeCargo(Cargo cargo) {
        if (cargos.contains(cargo)) {
            this.cargos.remove(cargo);
            cargo.getProgramas().remove(this);
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPrograma != null ? codPrograma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Programa)) {
            return false;
        }
        Programa other = (Programa) object;
        return !((this.codPrograma == null && other.codPrograma != null)
                || (this.codPrograma != null && !this.codPrograma.equals(other.codPrograma)));
    }

    @Override
    public String toString() {
        return "br.cefetmg.inf.hosten.model.domain.Programa[ codprograma=" + codPrograma + " ]";
    }

}
