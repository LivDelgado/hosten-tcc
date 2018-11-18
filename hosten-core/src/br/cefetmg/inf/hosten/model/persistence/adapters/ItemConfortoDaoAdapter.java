package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IItemConfortoDao;
import br.cefetmg.inf.hosten.model.persistence.jdbc.ItemConfortoDao;

public final class ItemConfortoDaoAdapter implements IItemConfortoDao {

    private final IItemConfortoDao dao;
    private static IItemConfortoDao instancia;

    private ItemConfortoDaoAdapter() {
        dao = ItemConfortoDao.getInstance();
    }

    public static synchronized IItemConfortoDao getInstance() {
        if (instancia == null) {
            instancia = new ItemConfortoDaoAdapter();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(ItemConforto itemConforto) throws SQLException {
        return dao.adiciona(itemConforto);
    }

    @Override
    public ItemConforto buscaPorPk(String id) throws SQLException {
        return dao.buscaPorPk(id);
    }

    @Override
    public List<ItemConforto> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException {
        return dao.buscaPorColuna(dadoBusca, coluna);
    }

    @Override
    public List<ItemConforto> buscaTodos() throws SQLException {
        return dao.buscaTodos();
    }

    @Override
    public boolean atualiza(String pK, ItemConforto itemConfortoAtualizado) throws SQLException {
        return dao.atualiza(pK, itemConfortoAtualizado);
    }

    @Override
    public boolean deleta(String pK) throws SQLException {
        return dao.deleta(pK);
    }
}
