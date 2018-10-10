package br.cefetmg.inf.hosten.controller.hospedagem;

import br.cefetmg.inf.hosten.controller.context.ContextUtils;
import br.cefetmg.inf.hosten.model.domain.rel.Despesa;
import br.cefetmg.inf.hosten.model.service.IControlarDespesas;
import br.cefetmg.inf.hosten.model.service.IManterQuarto;
import br.cefetmg.inf.hosten.proxy.ControlarDespesasProxy;
import br.cefetmg.inf.hosten.proxy.ManterQuartoProxy;
import br.cefetmg.inf.util.exception.NegocioException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named("despesaMB")
public class DespesaMB implements Serializable {
    
    private List<Despesa> listaDespesas;
    
    private int nroQuarto;
    private int seqHospedagem;
    
    public DespesaMB() {
        IControlarDespesas controlarDespesas = new ControlarDespesasProxy();
        try {
            listaDespesas = controlarDespesas.listar(seqHospedagem, nroQuarto);
        } catch (NegocioException | SQLException ex) {
            //
        }
    }

    public List<Despesa> getListaDespesas() {
        return listaDespesas;
    }

    public void setListaDespesas(List<Despesa> listaDespesas) {
        this.listaDespesas = listaDespesas;
    }

    public int getNroQuarto() {
        return nroQuarto;
    }

    public void setNroQuarto(int nroQuarto) {
        this.nroQuarto = nroQuarto;
    }

    public int getSeqHospedagem() {
        return seqHospedagem;
    }

    public void setSeqHospedagem(int seqHospedagem) {
        this.seqHospedagem = seqHospedagem;
    }
    
    public void resetaVariaveis () {
        nroQuarto = seqHospedagem = 0;
        listaDespesas = null;
    }
    
    public void exibeDespesas(int nroQuarto, int operacao) {
        try {
            setNroQuarto(nroQuarto);
            IManterQuarto manterQuarto = new ManterQuartoProxy();
            seqHospedagem = manterQuarto.buscaUltimoRegistroRelacionadoAoQuarto(nroQuarto);
            
            if (operacao == 1)
                ContextUtils.redireciona("./detalhes-conta.jsf");
            else if (operacao == 2)
                ContextUtils.redireciona("./check-out.jsf");
        } catch (IOException | NegocioException | SQLException ex) {
            //
            //
        }
    }
}

