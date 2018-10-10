package br.cefetmg.inf.hosten.model.dao;

import br.cefetmg.inf.hosten.model.domain.Categoria;
import java.sql.SQLException;
import java.util.List;

public interface ICategoriaQuartoDAO {

    boolean adicionaCategoriaQuarto(Categoria categoriaQuarto)
            throws SQLException;

    List<Categoria> buscaCategoriaQuarto(Object dadoBusca, String coluna)
            throws SQLException;

    List<Categoria> buscaTodosCategoriaQuartos()
            throws SQLException;

    boolean atualizaCategoriaQuarto(Object pK, Categoria categoriaQuartoAtualizado)
            throws SQLException;

    boolean deletaCategoriaQuarto(Object pK) throws SQLException;

}
