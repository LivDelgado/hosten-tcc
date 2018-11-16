package br.cefetmg.inf.hosten.model.persistence.interfaces;

import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import java.sql.SQLException;
import java.util.List;

public interface IItemConfortoDao {

    boolean adicionaItemConforto(ItemConforto itemConforto)
            throws SQLException;

    List<ItemConforto> buscaItemConforto(Object dadoBusca, String coluna)
            throws SQLException;

    List<ItemConforto> buscaTodosItemConfortos()
            throws SQLException;

    boolean atualizaItemConforto(Object pK, ItemConforto itemConfortoAtualizado)
            throws SQLException;

    boolean deletaItemConforto(Object pK) throws SQLException;
}
