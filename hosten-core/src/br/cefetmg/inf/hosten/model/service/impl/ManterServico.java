package br.cefetmg.inf.hosten.model.service.impl;

import br.cefetmg.inf.hosten.model.domain.Servico;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoConsumo;
import br.cefetmg.inf.hosten.model.persistence.adapters.QuartoConsumoDaoAdapter;
import br.cefetmg.inf.hosten.model.persistence.adapters.ServicoDaoAdapter;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.service.IManterServico;
import br.cefetmg.inf.hosten.model.persistence.interfaces.rel.IQuartoConsumoDao;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IServicoDao;
import java.util.Objects;

public class ManterServico implements IManterServico {

    IServicoDao objetoDAO;

    public ManterServico() {
        objetoDAO = ServicoDaoAdapter.getInstance();
    }

    @Override
    public boolean inserir(Servico servico)
            throws NegocioException, SQLException {
        // testa tamanho dos campos
        if (servico.getDesServico().length() > 40) {
            throw new NegocioException("A descrição do serviço ultrapassou os 40 caracteres máximos permitidos.");
        }
        if (servico.getVlrUnit().doubleValue() > 99999.99) {
            throw new NegocioException("O valor do serviço ultrapassou os R$9999999,99 máximos permitidos.");
        }

        List<Servico> servicosPesquisados = objetoDAO.buscaPorColuna(servico.getServicoArea(), "codServicoArea");

        if (!servicosPesquisados.isEmpty()) {
            for (Servico s : servicosPesquisados) {
                if (!Objects.equals(s.getSeqServico(), servico.getSeqServico())) {
                    if ((s.getDesServico()).equals(servico.getDesServico())) {
                        throw new NegocioException(
                                "Já existe um serviço na mesma área com a mesma descrição!");
                    }
                }
            }
        }

        return objetoDAO.adiciona(servico);
    }

    @Override
    public boolean alterar(short codRegistro, Servico servico) throws SQLException, NegocioException {
        // testa tamanho dos campos
        if (servico.getDesServico().length() > 40) {
            throw new NegocioException("A descrição do serviço ultrapassou os 40 caracteres máximos permitidos.");
        }
        if (servico.getVlrUnit().doubleValue() > 99999.99) {
            throw new NegocioException("O valor do serviço ultrapassou os R$9999999,99 máximos permitidos.");
        }

        List<Servico> buscaRegistroAntigo = listar(codRegistro, "seqServico");
        Servico registroAntigo = buscaRegistroAntigo.get(0);

        // lista de serviços na mesma área
        List<Servico> servicosPesquisados = objetoDAO.buscaPorColuna(servico.getServicoArea(), "codServicoArea");

        if (!servicosPesquisados.isEmpty()
                || ((registroAntigo.getDesServico().equals(servico.getDesServico()))
                && (registroAntigo.getServicoArea().equals(servico.getServicoArea())))) {
            for (Servico s : servicosPesquisados) {
                if ((s.getDesServico()).equals(servico.getDesServico()) && (s.getSeqServico() != servico.getSeqServico())) {
                    throw new NegocioException("Já existe um serviço na mesma "
                            + "área com a mesma descrição!");
                }
            }
        }

        return objetoDAO.atualiza(codRegistro, servico);
    }

    @Override
    public boolean excluir(short codRegistro) throws NegocioException, SQLException {
        // confere se o servico é usado em quartoconsumo
        IQuartoConsumoDao dao = QuartoConsumoDaoAdapter.getInstance();
        List<QuartoConsumo> listaREL = dao.buscaPorColuna(codRegistro, "seqServico");
        if (listaREL.isEmpty()) {
            return objetoDAO.deleta(codRegistro);
        } else {
            throw new NegocioException(
                    "Não é possível excluir esse serviço. "
                    + "Ele já foi consumido!");
        }
    }

    @Override
    public List<Servico> listar(Object dadoBusca, String coluna)
            throws NegocioException, SQLException {
        //
        // confere se foi digitado um dado busca e se a coluna é válida
        //
        if (dadoBusca != null) {
            return objetoDAO.buscaPorColuna(dadoBusca, coluna);
        } else {
            throw new NegocioException("Nenhum serviço buscado!");
        }
    }

    @Override
    public List<Servico> listarTodos()
            throws NegocioException, SQLException {
        return objetoDAO.buscaTodos();
    }
}
