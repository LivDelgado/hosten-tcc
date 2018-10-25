package br.cefetmg.inf.hosten.model.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "itemconforto", catalog = "hosten", schema = "public")
@NamedQueries({
    @NamedQuery(name = "ItemConforto.findAll", query = "SELECT i FROM ItemConforto i")
    , @NamedQuery(name = "ItemConforto.findByCodItem", query = "SELECT i FROM ItemConforto i WHERE i.codItem = :codItem")
    , @NamedQuery(name = "ItemConforto.findByDesItem", query = "SELECT i FROM ItemConforto i WHERE i.desItem = :desItem")})
public class ItemConforto implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "coditem", nullable = false, length = 3)
    private String codItem;

    @Basic(optional = false)
    @Column(name = "desitem", nullable = false, length = 40)
    private String desItem;

    @ManyToMany(mappedBy = "itemConfortos")
    private final Set<CategoriaQuarto> categorias = new HashSet<>();

    public ItemConforto() {
    }

    public ItemConforto(String coditem) {
        this.codItem = coditem;
    }

    public ItemConforto(String codItem, String desItem) {
        this.codItem = codItem;
        this.desItem = desItem;
    }

    public String getCodItem() {
        return codItem;
    }

    public void setCodItem(String codItem) {
        this.codItem = codItem;
    }

    public String getDesItem() {
        return desItem;
    }

    public void setDesItem(String desItem) {
        this.desItem = desItem;
    }

    public Set<CategoriaQuarto> getCategorias() {
        return categorias;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codItem != null ? codItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemConforto)) {
            return false;
        }
        ItemConforto other = (ItemConforto) object;
        if ((this.codItem == null && other.codItem != null) || (this.codItem != null && !this.codItem.equals(other.codItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.cefetmg.inf.hosten.model.domain.Itemconforto[ coditem=" + codItem + " ]";
    }
    
}
