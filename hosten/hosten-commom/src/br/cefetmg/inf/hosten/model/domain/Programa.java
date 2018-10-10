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
    , 
    @NamedQuery(name = "Programa.findByCodprograma", query = "SELECT p FROM Programa p WHERE p.codprograma = :codprograma")
    ,
    @NamedQuery(name = "Programa.findByDesprograma", query = "SELECT p FROM Programa p WHERE p.desprograma = :desprograma")})
public class Programa implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "codprograma", nullable = false, length = 3)
    private String codprograma;

    @Basic(optional = false)
    @Column(name = "desprograma", nullable = false, length = 200)
    private String desprograma;

    @JoinTable(name = "cargoprograma",
            joinColumns = {
                @JoinColumn(name = "codprograma", referencedColumnName = "codprograma", nullable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "codcargo", referencedColumnName = "codcargo", nullable = false)})
    @ManyToMany
    private Set<Cargo> cargos = new HashSet<>();

    public Programa() {
    }

    public Programa(String codprograma) {
        this.codprograma = codprograma;
    }

    public Programa(String codprograma, String desprograma) {
        this.codprograma = codprograma;
        this.desprograma = desprograma;
    }

    public String getCodprograma() {
        return codprograma;
    }

    public void setCodprograma(String codprograma) {
        this.codprograma = codprograma;
    }

    public String getDesprograma() {
        return desprograma;
    }

    public void setDesprograma(String desprograma) {
        this.desprograma = desprograma;
    }

    public Set<Cargo> getCargos() {
        return cargos;
    }

    public void setCargos(Set<Cargo> cargos) {
        this.cargos = cargos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codprograma != null ? codprograma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Programa)) {
            return false;
        }
        Programa other = (Programa) object;
        return !((this.codprograma == null && other.codprograma != null)
                || (this.codprograma != null && !this.codprograma.equals(other.codprograma)));
    }

    @Override
    public String toString() {
        return "br.cefetmg.inf.hosten.model.domain.Programa[ codprograma=" + codprograma + " ]";
    }

}
