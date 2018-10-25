 package br.cefetmg.inf.hosten.controller.hospedagem;

import br.cefetmg.inf.hosten.model.domain.rel.QuartoEstado;
import br.cefetmg.inf.hosten.model.service.IControlarHospedagem;
import br.cefetmg.inf.hosten.proxy.ControlarHospedagemProxy;
import br.cefetmg.inf.util.exception.NegocioException;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@ViewScoped
@Named(value = "quartoEstadoMB")
public class QuartoEstadoMB implements Serializable{
    
    private List<QuartoEstado> listaQuartosEstados;
    
    public QuartoEstadoMB() {
        IControlarHospedagem controlarHospedagem = new ControlarHospedagemProxy();
        try {
            listaQuartosEstados = controlarHospedagem.listarTodos();
        } catch (NegocioException e) {
            //
        }
    }

    public List<QuartoEstado> getListaQuartosEstados() {
        for(QuartoEstado q : listaQuartosEstados)
            System.out.println("data: " + q.getDatCheckOut());
        
        return listaQuartosEstados;
    }

    public void setListaQuartosEstados(List<QuartoEstado> listaQuartosEstados) {
        this.listaQuartosEstados = listaQuartosEstados;
    }

}
