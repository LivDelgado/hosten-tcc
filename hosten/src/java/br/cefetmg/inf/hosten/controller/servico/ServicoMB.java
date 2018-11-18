package br.cefetmg.inf.hosten.controller.servico;

import br.cefetmg.inf.hosten.controller.context.ContextUtils;
import br.cefetmg.inf.hosten.dist.proxy.ManterServicoAreaProxy;
import br.cefetmg.inf.hosten.dist.proxy.ManterServicoProxy;
import br.cefetmg.inf.hosten.model.domain.Servico;
import br.cefetmg.inf.hosten.model.domain.ServicoArea;
import br.cefetmg.inf.hosten.model.service.IManterServico;
import br.cefetmg.inf.hosten.model.service.IManterServicoArea;
import br.cefetmg.inf.util.exception.NegocioException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.RowEditEvent;

@ViewScoped
@Named(value = "servicoMB")
public class ServicoMB implements Serializable{
    
    private IManterServico manterServico;

    private List<Servico> listaServicos;
    private Servico servico;
    
    private ServicoArea areaServico;
    private ServicoArea areaSelecionada;
    
    private short codServicoAlterar;

    public ServicoMB() {
        servico = new Servico();
        manterServico = new ManterServicoProxy();
        try {
            listaServicos = manterServico.listarTodos();
        } catch (NegocioException | SQLException e) {
            //
        }
    }

    public ServicoArea getAreaServico(Servico servico) {
        IManterServicoArea manterServicoArea = new ManterServicoAreaProxy();
        try {
            areaServico = manterServicoArea.listar(servico.getServicoArea(), "codServicoArea").get(0);
        } catch (NegocioException | SQLException ex) {
            ex.printStackTrace();
            //
        }
        return areaServico;
    }

    public void setAreaServico(ServicoArea areaServico) {
        this.areaServico = areaServico;
    }

    public List<Servico> getListaServicos() {
        return listaServicos;
    }

    public void setListaServicos(List<Servico> listaServicos) {
        this.listaServicos = listaServicos;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }


    public void onRowInit(RowEditEvent event) {
        codServicoAlterar = (short) event.getComponent().getAttributes().get("servicoEditar");
    }

    public void onRowEdit(RowEditEvent event) throws IOException {
        try {
            servico = (Servico) event.getObject();
            servico.setServicoArea(areaSelecionada);
            
            boolean testeExclusao = manterServico.alterar(codServicoAlterar, servico);
            if (testeExclusao) {
                ContextUtils.mostrarMensagem("Alteração efetuada", "Registro alterado com sucesso!", true);
            } else {
                ContextUtils.mostrarMensagem("Falha na alteração", "Falha ao alterar o registro!", true);
            }
        } catch (NegocioException | SQLException ex) {
            ContextUtils.mostrarMensagem("Falha na alteração", ex.getMessage(), true);
        }
        ContextUtils.redireciona(null);
    }

    public void onRowCancel(RowEditEvent event) {
        ContextUtils.mostrarMensagem("Edição Cancelada", ((Servico) event.getObject()).getDesServico(), false);
    }

    public String excluir(Servico servico) {
        this.servico = servico;

        try {
            boolean testeExclusao = manterServico.excluir(servico.getSeqServico());
            if (testeExclusao) {
                ContextUtils.mostrarMensagem("Exclusão efetuada", "Registro excluído com sucesso!", true);
                return "sucesso";
            } else {
                ContextUtils.mostrarMensagem("Falha na exclusão", "Falha ao excluir o registro!", true);
                return "falha";
            }
        } catch (NegocioException | SQLException ex) {
            ContextUtils.mostrarMensagem("Falha na exclusão", ex.getMessage(), true);
            return "falha";
        }

    }

    public String inserir() {
        try {
            servico.setServicoArea(areaSelecionada);
            boolean testeInsercao = manterServico.inserir(servico);

            if (testeInsercao) {
                ContextUtils.mostrarMensagem("Inserção efetuada", "Registro inserido com sucesso!", true);
                return "sucesso";
            } else {
                ContextUtils.mostrarMensagem("Falha na inserção", "Falha ao inserir o registro!", true);
                return "falha";
            }
        } catch (NegocioException | SQLException ex) {
            ContextUtils.mostrarMensagem("Falha na inserção", ex.getMessage(), true);
            return "falha";
        }
    }

    public ServicoArea getAreaSelecionada() {
        return areaSelecionada;
    }

    public void setAreaSelecionada(ServicoArea areaSelecionada) {
        this.areaSelecionada = areaSelecionada;
    }
    
    
}
