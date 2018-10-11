package br.cefetmg.inf.hosten.model.domain;

import br.cefetmg.inf.hosten.model.domain.embeddable.QuartoConsumoId;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoConsumo;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoHospedagem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
@Table(name = "usuario", catalog = "hosten", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByCodUsuario", query = "SELECT u FROM Usuario u WHERE u.codUsuario = :codUsuario")
    , @NamedQuery(name = "Usuario.findByNomUsuario", query = "SELECT u FROM Usuario u WHERE u.nomUsuario = :nomUsuario")
    , @NamedQuery(name = "Usuario.findByDesSenha", query = "SELECT u FROM Usuario u WHERE u.desSenha = :desSenha")
    , @NamedQuery(name = "Usuario.findByDesEmail", query = "SELECT u FROM Usuario u WHERE u.desEmail = :deEmail")})
public class Usuario implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "codusuario", nullable = false, length = 4)
    private String codUsuario;
    
    @Basic(optional = false)
    @Column(name = "nomusuario", nullable = false, length = 90)
    private String nomUsuario;
    
    @Column(name = "dessenha", length = 64)
    private String desSenha;
    
    @Column(name = "desemail", length = 60, unique = true)
    private String desEmail;
    
    @OneToMany(mappedBy = "codUsuarioRegistro", cascade = CascadeType.ALL)
    private List<QuartoConsumo> quartoConsumos = new ArrayList<>();
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "codcargo", referencedColumnName = "codcargo", nullable = false)
    private Cargo codCargo;

    public Usuario() {
    }

    public Usuario(String codusuario) {
        this.codUsuario = codusuario;
    }

    public String getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(String codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getDesSenha() {
        return desSenha;
    }

    public void setDesSenha(String desSenha) {
        this.desSenha = desSenha;
    }

    public String getDesEmail() {
        return desEmail;
    }

    public void setDesEmail(String desEmail) {
        this.desEmail = desEmail;
    }

    public List<QuartoConsumo> getQuartoConsumos() {
        return quartoConsumos;
    }

    public void setQuartoConsumos(List<QuartoConsumo> quartoConsumos) {
        this.quartoConsumos = quartoConsumos;
    }

    public Cargo getCodCargo() {
        return codCargo;
    }

    public void setCodCargo(Cargo codCargo) {
        this.codCargo = codCargo;
    }
    
    public void addQuartoConsumo(QuartoHospedagem quartoHospedagem, Date datConsumo, short qtdConsumo, Servico servico) {
        QuartoConsumoId qcid = new QuartoConsumoId(
                quartoHospedagem.getId().getSeqHospedagem(), 
                quartoHospedagem.getId().getNroQuarto(), datConsumo);
        QuartoConsumo qc = new QuartoConsumo(qcid, qtdConsumo, quartoHospedagem, servico, this);
        
        this.quartoConsumos.add(qc);
        quartoHospedagem.getQuartoConsumos().add(qc);
        servico.getQuartoConsumos().add(qc);
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codUsuario != null ? codUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.codUsuario == null && other.codUsuario != null) || (this.codUsuario != null && !this.codUsuario.equals(other.codUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.cefetmg.inf.hosten.model.domain.Usuario[ codusuario=" + codUsuario + " ]";
    }
    
}
