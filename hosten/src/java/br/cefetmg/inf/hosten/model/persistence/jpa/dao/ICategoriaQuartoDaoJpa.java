package br.cefetmg.inf.hosten.model.persistence.jpa.dao;

import br.cefetmg.inf.hosten.model.persistence.jpa.domain.CategoriaQuartoJpa;
import java.sql.SQLException;
import java.util.List;

public interface ICategoriaQuartoDaoJpa {

    boolean adiciona(CategoriaQuartoJpa categoriaQuarto) throws SQLException;

    CategoriaQuartoJpa buscaPorPk(String id) throws SQLException;

    List<CategoriaQuartoJpa> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException;

    List<CategoriaQuartoJpa> buscaTodos() throws SQLException;

    boolean atualiza(String id, CategoriaQuartoJpa categoriaQuartoAtualizado) throws SQLException;

    boolean deleta(CategoriaQuartoJpa categoriaQuarto) throws SQLException;
}
