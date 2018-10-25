package br.cefetmg.inf.hosten.controller.hospedagem;

import br.cefetmg.inf.hosten.controller.context.ContextUtils;
import br.cefetmg.inf.hosten.controller.sessao.Sessao;
import br.cefetmg.inf.hosten.model.domain.Servico;
import br.cefetmg.inf.hosten.model.domain.rel.Despesa;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoConsumo;
import br.cefetmg.inf.hosten.model.service.IControlarDespesas;
import br.cefetmg.inf.hosten.model.service.IManterQuarto;
import br.cefetmg.inf.hosten.model.service.impl.*;
import br.cefetmg.inf.util.exception.NegocioException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named(value = "despesaMB")
public class DespesaMB implements Serializable {

    private List<Despesa> listaDespesas;
    private int nroQuarto;
    private int seqHospedagem;

    private int qtdConsumo;
    private Servico servicoSelecionado;
    private String codUsuarioRegistro;

    public List<Despesa> getListaDespesas() {
        IControlarDespesas controlarDespesas = new ControlarDespesas();
        try {
            listaDespesas = controlarDespesas.listar(seqHospedagem, nroQuarto);
        } catch (NegocioException | SQLException ex) {
            //
        }

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

    public void resetaVariaveis() {
        nroQuarto = seqHospedagem = 0;
        listaDespesas = null;
    }

    public void exibeDespesas(int nroQuarto, int operacao) {
        try {
            setNroQuarto(nroQuarto);
            IManterQuarto manterQuarto = new ManterQuarto();
            setSeqHospedagem(manterQuarto.buscaUltimoRegistroRelacionadoAoQuarto(nroQuarto));

            if (operacao == 1) {
                ContextUtils.redireciona("./detalhes-conta.jsf");
            } else if (operacao == 2) {
                ContextUtils.redireciona("./check-out.jsf");
            }
        } catch (IOException | NegocioException | SQLException ex) {
            //
            //
        }
    }

    public String inserir() {
        codUsuarioRegistro = Sessao.getInstance().getUsuarioLogado().getCodUsuario();
        IControlarDespesas controlarDespesas = new ControlarDespesas();
        Date dataAtual = new Date();
        Timestamp datConsumo = new Timestamp(dataAtual.getTime());

        QuartoConsumo registro = new QuartoConsumo(
                seqHospedagem, nroQuarto, datConsumo,
                qtdConsumo, servicoSelecionado.getSeqServico(), 
                codUsuarioRegistro
        );
        
        try {
            boolean testeRegistro = controlarDespesas.inserir(registro);
            if (testeRegistro) {
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

    public String excluir(Despesa despesa) {
        IControlarDespesas controlarDespesas = new ControlarDespesas();
        
        //
        //
        Date dataAtual = new Date();
        Timestamp datConsumo = new Timestamp(dataAtual.getTime());
        //
        //

        QuartoConsumo registro = new QuartoConsumo(
                despesa.getSeqHospedagem(), 
                despesa.getNroQuarto(), 
//                despesa.getDatConsumo(),
                datConsumo,
//                
                despesa.getQtdConsumo(), 
                despesa.getSeqServico(), 
                null
        );
        
        try {
            boolean testeRegistro = controlarDespesas.excluir(registro);
            if (testeRegistro) {
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

    public Servico getServicoSelecionado() {
        return servicoSelecionado;
    }

    public void setServicoSelecionado(Servico servicoSelecionado) {
        this.servicoSelecionado = servicoSelecionado;
    }

    public int getQtdConsumo() {
        return qtdConsumo;
    }

    public void setQtdConsumo(int qtdConsumo) {
        this.qtdConsumo = qtdConsumo;
    }

    public String getCodUsuarioRegistro() {
        return codUsuarioRegistro;
    }

    public void setCodUsuarioRegistro(String codUsuarioRegistro) {
        this.codUsuarioRegistro = codUsuarioRegistro;
    }

}
