package br.cefetmg.inf.hosten.model.persistence.jpa.dao;

import br.cefetmg.inf.hosten.model.persistence.jpa.domain.CategoriaQuarto;
import java.sql.SQLException;
import java.util.List;

public interface ICategoriaQuartoDAO {

    boolean adiciona(CategoriaQuarto categoriaQuarto) throws SQLException;

    CategoriaQuarto buscaPorPk(String id) throws SQLException;

    List<CategoriaQuarto> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException;

    List<CategoriaQuarto> buscaTodos() throws SQLException;

    boolean atualiza(String id, CategoriaQuarto categoriaQuartoAtualizado) throws SQLException;

    boolean deleta(CategoriaQuarto categoriaQuarto) throws SQLException;
}
