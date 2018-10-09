package br.cefetmg.inf.hosten.model.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="quarto")
public class Quarto implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name="nroQuarto")
	private int nroQuarto;
 
    @Column(name="codCategoria")
	private String codCategoria;
 
    @Column(name="idtOcupado")
	private boolean idtOcupado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNroQuarto() {
        return nroQuarto;
    }

    public void setNroQuarto(int nroQuarto) {
        this.nroQuarto = nroQuarto;
    }

    public String getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(String codCategoria) {
        this.codCategoria = codCategoria;
    }

    public boolean isIdtOcupado() {
        return idtOcupado;
    }

    public void setIdtOcupado(boolean idtOcupado) {
        this.idtOcupado = idtOcupado;
    }
    
}
