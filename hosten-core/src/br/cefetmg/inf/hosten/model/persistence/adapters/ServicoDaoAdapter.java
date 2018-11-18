package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.domain.Servico;
import br.cefetmg.inf.hosten.model.persistence.jdbc.ServicoDao;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IServicoDao;

public class ServicoDaoAdapter implements IServicoDao {

    private final IServicoDao dao;
    private static IServicoDao instancia;

    private ServicoDaoAdapter() {
        dao = ServicoDao.getInstance();
    }

    public static synchronized IServicoDao getInstance() {
        if (instancia == null) {
            instancia = new ServicoDaoAdapter();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(Servico servico) throws SQLException {
        return dao.adiciona(servico);
    }

    @Override
    public Servico buscaPorPk(short id) throws SQLException {
        return dao.buscaPorPk(id);
    }

    @Override
    public List<Servico> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException {
        return dao.buscaPorColuna(dadoBusca, coluna);
    }

    @Override
    public List<Servico> buscaTodos() throws SQLException {
        return dao.buscaTodos();
    }

    @Override
    public boolean atualiza(short pK, Servico servicoAtualizado) throws SQLException {
        return dao.atualiza(pK, servicoAtualizado);
    }

    @Override
    public boolean deleta(short pK) throws SQLException {
        return dao.deleta(pK);
    }
}
