package br.cefetmg.inf.hosten.model.persistence.jpa.dao;

import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import java.sql.SQLException;
import java.util.List;

public interface IItemConfortoDaoJpa {

    boolean adiciona(ItemConforto itemConforto) throws SQLException;

    ItemConforto buscaPorPk(String id) throws SQLException;

    List<ItemConforto> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException;

    List<ItemConforto> buscaTodos() throws SQLException;

    boolean atualiza(String id, ItemConforto itemConfortoAtualizado) throws SQLException;

    boolean deleta(ItemConforto itemConforto) throws SQLException;
}
