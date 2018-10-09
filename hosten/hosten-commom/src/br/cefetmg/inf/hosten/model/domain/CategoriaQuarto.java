package br.cefetmg.inf.hosten.model.domain;

import java.io.Serializable;
import java.util.Objects;

public class CategoriaQuarto implements Serializable {
    private String codCategoria; 
    private String nomCategoria; 
    private Double vlrDiaria; 

    public CategoriaQuarto(String codCategoria, String nomCategoria, Double vlrDiaria) {
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

    public Double getVlrDiaria() {
        return vlrDiaria;
    }

    public void setVlrDiaria(Double vlrDiaria) {
        this.vlrDiaria = vlrDiaria;
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
        final CategoriaQuarto other = (CategoriaQuarto) obj;
        if (!Objects.equals(this.codCategoria, other.codCategoria)) {
            return false;
        }
        return true;
    }
}
