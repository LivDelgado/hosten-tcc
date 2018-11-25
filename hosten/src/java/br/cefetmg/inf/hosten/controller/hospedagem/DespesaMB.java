package br.cefetmg.inf.hosten.controller.hospedagem;

import br.cefetmg.inf.hosten.controller.context.ContextUtils;
import br.cefetmg.inf.hosten.controller.sessao.Sessao;
import br.cefetmg.inf.hosten.dist.proxy.ControlarDespesasProxy;
import br.cefetmg.inf.hosten.dist.proxy.ManterQuartoProxy;
import br.cefetmg.inf.hosten.model.domain.Servico;
import br.cefetmg.inf.hosten.model.domain.Usuario;
import br.cefetmg.inf.hosten.model.domain.idcomposto.QuartoConsumoId;
import br.cefetmg.inf.hosten.model.domain.idcomposto.QuartoHospedagemId;
import br.cefetmg.inf.hosten.model.domain.rel.Despesa;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoConsumo;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoHospedagem;
import br.cefetmg.inf.hosten.model.service.IControlarDespesas;
import br.cefetmg.inf.hosten.model.service.IManterQuarto;
import br.cefetmg.inf.util.exception.NegocioException;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named(value = "despesaMB")
public class DespesaMB implements Serializable {

    private List<Despesa> listaDespesas;
    private short nroQuarto;
    private int seqHospedagem;

    private short qtdConsumo;
    private Servico servicoSelecionado;
    private String codUsuarioRegistro;

    public List<Despesa> getListaDespesas() {
        IControlarDespesas controlarDespesas = new ControlarDespesasProxy();
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

    public short getNroQuarto() {
        return nroQuarto;
    }

    public void setNroQuarto(short nroQuarto) {
        this.nroQuarto = nroQuarto;
    }

    public int getSeqHospedagem() {
        return seqHospedagem;
    }

    public void setSeqHospedagem(int seqHospedagem) {
        this.seqHospedagem = seqHospedagem;
    }

    public void resetaVariaveis() {
        nroQuarto = 0;
        seqHospedagem = 0;
        listaDespesas = null;
    }

    public void exibeDespesas(short nroQuarto, int operacao) {
        try {
            setNroQuarto(nroQuarto);
            IManterQuarto manterQuarto = new ManterQuartoProxy();
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
        IControlarDespesas controlarDespesas = new ControlarDespesasProxy();
        
        Timestamp datConsumo = new Timestamp(System.currentTimeMillis());

        QuartoConsumo registro = new QuartoConsumo(
                new QuartoConsumoId(
                        new QuartoHospedagem(
                                seqHospedagem,
                                nroQuarto),
                        datConsumo),
                qtdConsumo,
                servicoSelecionado,
                new Usuario(codUsuarioRegistro));

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
        IControlarDespesas controlarDespesas = new ControlarDespesasProxy();

        QuartoConsumo registro = new QuartoConsumo(
                new QuartoConsumoId(
                        new QuartoHospedagem(
                                despesa.getSeqHospedagem(), 
                                despesa.getNroQuarto()), 
                        despesa.getDatConsumo()
                ),
                despesa.getQtdConsumo(),
                new Servico(despesa.getSeqServico()),
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

    public short getQtdConsumo() {
        return qtdConsumo;
    }

    public void setQtdConsumo(short qtdConsumo) {
        this.qtdConsumo = qtdConsumo;
    }

    public String getCodUsuarioRegistro() {
        return codUsuarioRegistro;
    }

    public void setCodUsuarioRegistro(String codUsuarioRegistro) {
        this.codUsuarioRegistro = codUsuarioRegistro;
    }

}
