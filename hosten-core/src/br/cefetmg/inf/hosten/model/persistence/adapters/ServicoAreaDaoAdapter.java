package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.domain.ServicoArea;
import br.cefetmg.inf.hosten.model.persistence.jdbc.ServicoAreaDao;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IServicoAreaDao;

public class ServicoAreaDaoAdapter implements IServicoAreaDao {

    private final IServicoAreaDao dao;
    private static IServicoAreaDao instancia;

    private ServicoAreaDaoAdapter() {
        dao = ServicoAreaDao.getInstance();
    }

    public static synchronized IServicoAreaDao getInstance() {
        if (instancia == null) {
            instancia = new ServicoAreaDaoAdapter();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(ServicoArea servicoArea) throws SQLException {
        return dao.adiciona(servicoArea);
    }

    @Override
    public ServicoArea buscaPorPk(String id) throws SQLException {
        return dao.buscaPorPk(id);
    }

    @Override
    public List<ServicoArea> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException {
        return dao.buscaPorColuna(dadoBusca, coluna);
    }

    @Override
    public List<ServicoArea> buscaTodos() throws SQLException {
        return dao.buscaTodos();
    }

    @Override
    public boolean atualiza(String pK, ServicoArea servicoAreaAtualizado) throws SQLException {
        return dao.atualiza(pK, servicoAreaAtualizado);
    }

    @Override
    public boolean deleta(String pK) throws SQLException {
        return dao.deleta(pK);
    }
}
