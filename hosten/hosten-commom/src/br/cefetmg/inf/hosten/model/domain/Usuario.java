package br.cefetmg.inf.hosten.model.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name = "codusuario")
    private String codUsuario;
    
    @Column(name = "nomusuario")
    private String nomUsuario;
    
    @Column(name = "codcargo")
    private String codCargo;
    
    @Column(name = "dessenha")
    private String desSenha;
    
    @Column(name = "desemail")
    private String desEmail;

    public Usuario() {
    }

    public Usuario(int id, String codUsuario, String nomUsuario, String codCargo, String desSenha, String desEmail) {
        this.id = id;
        this.codUsuario = codUsuario;
        this.nomUsuario = nomUsuario;
        this.codCargo = codCargo;
        this.desSenha = desSenha;
        this.desEmail = desEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCodCargo() {
        return codCargo;
    }

    public void setCodCargo(String codCargo) {
        this.codCargo = codCargo;
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
}
