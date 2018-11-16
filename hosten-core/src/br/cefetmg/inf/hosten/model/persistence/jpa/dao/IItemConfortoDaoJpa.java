package br.cefetmg.inf.hosten.model.persistence.jpa.dao;

import br.cefetmg.inf.hosten.model.persistence.jpa.domain.ItemConfortoJpa;
import java.sql.SQLException;
import java.util.List;

public interface IItemConfortoDaoJpa {

    boolean adiciona(ItemConfortoJpa itemConforto) throws SQLException;

    ItemConfortoJpa buscaPorPk(String id) throws SQLException;

    List<ItemConfortoJpa> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException;

    List<ItemConfortoJpa> buscaTodos() throws SQLException;

    boolean atualiza(String id, ItemConfortoJpa itemConfortoAtualizado) throws SQLException;

    boolean deleta(ItemConfortoJpa itemConforto) throws SQLException;
}
