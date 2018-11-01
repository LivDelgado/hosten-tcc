package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import br.cefetmg.inf.hosten.model.persistence.jdbc.ItemConfortoDao;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IItemConfortoDao;

public final class ItemConfortoDaoAdapter implements IItemConfortoDao {

    private static IItemConfortoDao instancia;

    public static synchronized IItemConfortoDao getInstance() {
        if (instancia == null) {
            instancia = ItemConfortoDao.getInstance();
        }
        return instancia;
    }

    @Override
    public boolean adicionaItemConforto(
            ItemConforto itemConforto) throws SQLException {
        return instancia.adicionaItemConforto(itemConforto);
    }

    @Override
    public List<ItemConforto> buscaItemConforto(
            Object dadoBusca, 
            String coluna) throws SQLException {
        return instancia.buscaItemConforto(dadoBusca, coluna);
    }
    
    @Override
    public List<ItemConforto> buscaTodosItemConfortos() throws SQLException {
        return instancia.buscaTodosItemConfortos();
    }

    @Override
    public boolean atualizaItemConforto(
            Object pK, 
            ItemConforto itemConfortoAtualizado) throws SQLException {
        return instancia.atualizaItemConforto(pK, itemConfortoAtualizado);
    }

    @Override
    public boolean deletaItemConforto(Object pK) throws SQLException {
        return instancia.deletaItemConforto(pK);
    }
}
