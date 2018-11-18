package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.ICategoriaQuartoDao;
import br.cefetmg.inf.hosten.model.persistence.jdbc.CategoriaQuartoDao;

public final class CategoriaQuartoDaoAdapter implements ICategoriaQuartoDao{

    private final ICategoriaQuartoDao dao;
    private static ICategoriaQuartoDao instancia;

    private CategoriaQuartoDaoAdapter() {
        dao = CategoriaQuartoDao.getInstance();
    }

    public static synchronized ICategoriaQuartoDao getInstance() {
        if (instancia == null) {
            instancia = new CategoriaQuartoDaoAdapter();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(CategoriaQuarto categoriaQuarto) throws SQLException {
        return dao.adiciona(categoriaQuarto);
    }

    @Override
    public CategoriaQuarto buscaPorPk(String id) throws SQLException {
        return dao.buscaPorPk(id);
    }

    @Override
    public List<CategoriaQuarto> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException {
        return dao.buscaPorColuna(dadoBusca, coluna);
    }

    @Override
    public List<CategoriaQuarto> buscaTodos() throws SQLException {
        return dao.buscaTodos();
    }

    @Override
    public boolean atualiza(String pK, CategoriaQuarto categoriaQuartoAtualizado) throws SQLException {
        return dao.atualiza(pK, categoriaQuartoAtualizado);
    }

    @Override
    public boolean deleta(String pK) throws SQLException {
        return dao.deleta(pK);
    }
}
