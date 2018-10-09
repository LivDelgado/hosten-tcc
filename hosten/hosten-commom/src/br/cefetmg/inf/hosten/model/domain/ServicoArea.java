package br.cefetmg.inf.hosten.model.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name="servicoarea")
public class ServicoArea implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="codservicoarea")
	private String codServicoArea;

    @Column(name="nomservicoarea")
	private String nomServicoArea;

    public ServicoArea() {
    }

    public ServicoArea(int id, String codServicoArea, String nomServicoArea) {
        this.id = id;
        this.codServicoArea = codServicoArea;
        this.nomServicoArea = nomServicoArea;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodServicoArea() {
        return codServicoArea;
    }

    public void setCodServicoArea(String codServicoArea) {
        this.codServicoArea = codServicoArea;
    }

    public String getNomServicoArea() {
        return nomServicoArea;
    }

    public void setNomServicoArea(String nomServicoArea) {
        this.nomServicoArea = nomServicoArea;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ServicoArea other = (ServicoArea) obj;
        if (!Objects.equals(this.codServicoArea, other.codServicoArea)) {
            return false;
        }
        return true;
    }
    
    
}
