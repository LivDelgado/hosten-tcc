package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.domain.Servico;
import br.cefetmg.inf.hosten.model.persistence.jdbc.ServicoDao;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IServicoDao;

public class ServicoDaoAdapter implements IServicoDao {

    private static IServicoDao instancia;

    public static synchronized IServicoDao getInstance() {
        if (instancia == null) {
            instancia = ServicoDao.getInstance();
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
