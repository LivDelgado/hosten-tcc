package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.persistence.interfaces.IItemConfortoDAO;
import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import br.cefetmg.inf.hosten.model.persistence.jdbc.ItemConfortoDAO;
import java.sql.SQLException;
import java.util.List;

public final class ItemConfortoDAOAdapter implements IItemConfortoDAO {

    private static IItemConfortoDAO instancia;

    public static synchronized IItemConfortoDAO getInstance() {
        if (instancia == null) {
            instancia = ItemConfortoDAO.getInstance();
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
