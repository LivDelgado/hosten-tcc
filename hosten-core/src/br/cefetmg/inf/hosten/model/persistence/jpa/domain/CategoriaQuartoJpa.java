package br.cefetmg.inf.hosten.model.persistence.jpa.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categoria", catalog = "hosten", schema = "public")
@NamedQueries({
    @NamedQuery(name = "CategoriaQuarto.findAll", query = "SELECT c FROM CategoriaQuarto c")
    , @NamedQuery(name = "CategoriaQuarto.findByCodCategoria", query = "SELECT c FROM CategoriaQuarto c WHERE c.codCategoria = :codCategoria")
    , @NamedQuery(name = "CategoriaQuarto.findByNomCategoria", query = "SELECT c FROM CategoriaQuarto c WHERE c.nomCategoria = :nomCategoria")
    , @NamedQuery(name = "CategoriaQuarto.findByVlrDiaria", query = "SELECT c FROM CategoriaQuarto c WHERE c.vlrDiaria = :vlrDiaria")})
public class CategoriaQuartoJpa implements Serializable {
    
    @Id
    @Basic(optional = false)
    @Column(name = "codcategoria", nullable = false, length = 3)
    private String codCategoria;
    
    @Basic(optional = false)
    @Column(name = "nomcategoria", nullable = false, length = 40)
    private String nomCategoria;
    
    @Basic(optional = false)
    @Column(name = "vlrdiaria", nullable = false, precision = 7, scale = 2)
    private BigDecimal vlrDiaria;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "categoriaitemconforto",
            joinColumns = {
                @JoinColumn(name = "codcategoria", referencedColumnName = "codcategoria", nullable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "coditem", referencedColumnName = "coditem", nullable = false)})
    private final Set<ItemConfortoJpa> itemConfortos = new HashSet<>();
    
    @OneToMany(mappedBy = "codCategoria", cascade = CascadeType.ALL)
    private final List<QuartoJpa> quartos = new ArrayList<>();
    
    public CategoriaQuartoJpa() {
    }
    
    public CategoriaQuartoJpa(String codcategoria) {
        this.codCategoria = codcategoria;
    }
    
    public CategoriaQuartoJpa(String codCategoria, String nomCategoria, BigDecimal vlrDiaria) {
        this.codCategoria = codCategoria;
        this.nomCategoria = nomCategoria;
        this.vlrDiaria = vlrDiaria;
    }
    
    public String getCodCategoria() {
        return codCategoria;
    }
    
    public void setCodCategoria(String codCategoria) {
        this.codCategoria = codCategoria;
    }
    
    public String getNomCategoria() {
        return nomCategoria;
    }
    
    public void setNomCategoria(String nomCategoria) {
        this.nomCategoria = nomCategoria;
    }
    
    public BigDecimal getVlrDiaria() {
        return vlrDiaria;
    }
    
    public void setVlrDiaria(BigDecimal vlrDiaria) {
        this.vlrDiaria = vlrDiaria;
    }
    
    public Set<ItemConfortoJpa> getItemConfortos() {
        return itemConfortos;
    }
    
    public List<QuartoJpa> getQuartos() {
        return quartos;
    }
    
    public void addItemConforto(ItemConfortoJpa itemConforto) {
        this.itemConfortos.add(itemConforto);
        itemConforto.getCategorias().add(this);
    }
    
    public void transferItemConforto(CategoriaQuartoJpa cqNov) {
        Set<ItemConfortoJpa> itensAnt = this.itemConfortos;
        Set<ItemConfortoJpa> itensNov = cqNov.getItemConfortos();
        
        if (itensNov != null) {
            Iterator<ItemConfortoJpa> itItensNovos = itensNov.iterator();
            while (itItensNovos.hasNext()) {
                ItemConfortoJpa itemNovo = itItensNovos.next();
                
                if (itensAnt == null || !itensAnt.contains(itemNovo)) {
                    this.addItemConforto(itemNovo);
                }
            }
            if (itensAnt != null) {
                Iterator<ItemConfortoJpa> itItensAnt = itensAnt.iterator();
                while (itItensAnt.hasNext()) {
                    ItemConfortoJpa itemAnt = itItensNovos.next();
                    
                    if (!itensNov.contains(itemAnt)) {
                        this.removeItemConforto(itemAnt);
                    }
                }
            }
        }
    }
    
    public void removeItemConforto(ItemConfortoJpa itemConforto) {
        this.itemConfortos.remove(itemConforto);
        itemConforto.getCategorias().remove(this);
    }
    
    public void addQuarto(QuartoJpa quarto) {
        quarto.setCodCategoria(this);
        this.quartos.add(quarto);
    }
    
    public void removeQuarto(QuartoJpa quarto, CategoriaQuartoJpa categoriaQuartoNova) {
        if (quartos.contains(quarto)) {
            this.quartos.remove(quarto);
            categoriaQuartoNova.addQuarto(quarto);
        }
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codCategoria != null ? codCategoria.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriaQuartoJpa)) {
            return false;
        }
        CategoriaQuartoJpa other = (CategoriaQuartoJpa) object;
        if ((this.codCategoria == null && other.codCategoria != null) || (this.codCategoria != null && !this.codCategoria.equals(other.codCategoria))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "br.cefetmg.inf.hosten.model.domain.Categoria[ codcategoria=" + codCategoria + " ]";
    }
}
