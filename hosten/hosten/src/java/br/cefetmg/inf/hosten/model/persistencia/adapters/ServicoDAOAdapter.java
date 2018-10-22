package br.cefetmg.inf.hosten.model.persistencia.adapters;

import br.cefetmg.inf.hosten.model.persistencia.jdbc.*;
import br.cefetmg.inf.hosten.model.persistencia.interfaces.IServicoDAO;
import br.cefetmg.inf.hosten.model.domain.Servico;
import java.sql.SQLException;
import java.util.List;

public class ServicoDAOAdapter implements IServicoDAO {

    private static IServicoDAO instancia;

    public static synchronized IServicoDAO getInstance() {
        if (instancia == null) {
            instancia = ServicoDAO.getInstance();
        }
        return instancia;
    }

    @Override
    public boolean adicionaServico(Servico servico) throws SQLException {
        return instancia.adicionaServico(servico);
    }

    @Override
    public List<Servico> buscaServico(Object dadoBusca, String coluna) throws SQLException {
        return instancia.buscaServico(dadoBusca, coluna);
    }

    @Override
    public List<Servico> buscaTodosServicos() throws SQLException {
        return instancia.buscaTodosServicos();
    }

    @Override
    public boolean atualizaServicoPorPk(Object pK, Servico servicoAtualizado) 
            throws SQLException {
        return instancia.atualizaServicoPorPk(pK, servicoAtualizado);
    }

    @Override
    public boolean atualizaServico(
            Servico servicoAntigo, 
            Servico servicoAtualizado) 
            throws SQLException {
        return instancia.atualizaServico(servicoAntigo, servicoAtualizado);
    }

    @Override
    public boolean atualizaServicoPorAtributos(
            String desServicoAntigo, 
            String codServicoAreaAntigo,
            Servico servicoAtualizado) 
            throws SQLException {
        return instancia.atualizaServicoPorAtributos(desServicoAntigo, codServicoAreaAntigo, servicoAtualizado);
    }

    @Override
    public boolean deletaServicoPorPk(Object pK) throws SQLException {
        return instancia.deletaServicoPorPk(pK);
    }

    @Override
    public boolean deletaServico(Servico servicoAntigo) throws SQLException {
        return instancia.deletaServico(servicoAntigo);
    }

    @Override
    public boolean deletaServicoPorAtributos(
            String desServicoAntigo, 
            String codServicoAreaAntigo) throws SQLException {
        return instancia.deletaServicoPorAtributos(desServicoAntigo, codServicoAreaAntigo);
    }
}
