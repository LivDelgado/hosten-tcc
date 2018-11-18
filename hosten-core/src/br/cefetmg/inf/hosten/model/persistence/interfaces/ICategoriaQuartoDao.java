package br.cefetmg.inf.hosten.model.persistence.interfaces;

import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import java.sql.SQLException;
import java.util.List;

public interface ICategoriaQuartoDao {

    boolean adiciona(CategoriaQuarto categoriaQuarto) throws SQLException;

    CategoriaQuarto buscaPorPk(String id) throws SQLException;

    List<CategoriaQuarto> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException;

    List<CategoriaQuarto> buscaTodos() throws SQLException;

    boolean atualiza(String id, CategoriaQuarto categoriaQuartoAtualizado) throws SQLException;

    boolean deleta(String categoriaQuarto) throws SQLException;
}
