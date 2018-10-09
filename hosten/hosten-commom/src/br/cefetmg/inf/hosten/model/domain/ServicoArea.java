package br.cefetmg.inf.hosten.model.domain;

import java.io.Serializable;
import java.util.Objects;

public class ServicoArea implements Serializable {

    private String codServicoArea;
    private String nomServicoArea;

    public ServicoArea(String codServicoArea, String nomServicoArea) {
        this.codServicoArea = codServicoArea;
        this.nomServicoArea = nomServicoArea;
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
