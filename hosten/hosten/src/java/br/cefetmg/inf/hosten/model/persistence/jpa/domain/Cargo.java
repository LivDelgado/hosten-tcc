package br.cefetmg.inf.hosten.model.persistence.jpa.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cargo", catalog = "hosten", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Cargo.findAll", query = "SELECT c FROM Cargo c")
    , @NamedQuery(name = "Cargo.findByCodCargo", query = "SELECT c FROM Cargo c WHERE c.codCargo = :codCargo")
    , @NamedQuery(name = "Cargo.findByNomCargo", query = "SELECT c FROM Cargo c WHERE c.nomCargo = :nomCargo")
    , @NamedQuery(name = "Cargo.findByIdtMaster", query = "SELECT c FROM Cargo c WHERE c.idtMaster = :idtMaster")})
public class Cargo implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "codcargo", nullable = false, length = 3)
    private String codCargo;

    @Basic(optional = false)
    @Column(name = "nomcargo", nullable = false, length = 40)
    private String nomCargo;

    @Basic(optional = false)
    @Column(name = "idtmaster", nullable = false)
    private boolean idtMaster;

    @ManyToMany(mappedBy = "cargos", cascade = CascadeType.ALL)
    private Set<Programa> programas = new HashSet<>();

    @OneToMany(mappedBy = "codCargo", cascade = CascadeType.ALL)
    private List<Usuario> usuarios = new ArrayList<>();

    public Cargo() {
    }

    public Cargo(String codcargo) {
        this.codCargo = codcargo;
    }

    public Cargo(String codcargo, String nomcargo, boolean idtmaster) {
        this.codCargo = codcargo;
        this.nomCargo = nomcargo;
        this.idtMaster = idtmaster;
    }

    public String getCodCargo() {
        return codCargo;
    }

    public void setCodCargo(String codCargo) {
        this.codCargo = codCargo;
    }

    public String getNomCargo() {
        return nomCargo;
    }

    public void setNomCargo(String nomCargo) {
        this.nomCargo = nomCargo;
    }

    public boolean isIdtMaster() {
        return idtMaster;
    }

    public void setIdtMaster(boolean idtMaster) {
        this.idtMaster = idtMaster;
    }

    public Set<Programa> getProgramas() {
        return programas;
    }

    public void setProgramas(Set<Programa> programas) {
        this.programas = programas;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    public void addUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
        usuario.setCodCargo(this);
    }
    
    public void removeUsuario(Usuario usuario, Cargo cargoNovo) {
        this.usuarios.remove(usuario);
        usuario.setCodCargo(cargoNovo);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codCargo != null ? codCargo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cargo)) {
            return false;
        }
        Cargo other = (Cargo) object;
        return !((this.codCargo == null && other.codCargo != null)
                || (this.codCargo != null && !this.codCargo.equals(other.codCargo)));
    }

    @Override
    public String toString() {
        return "Cargo = { codcargo=[" + codCargo + "], nomcargo=[" + nomCargo + "],[idtmaster=" + idtMaster + "]}";
    }
}
