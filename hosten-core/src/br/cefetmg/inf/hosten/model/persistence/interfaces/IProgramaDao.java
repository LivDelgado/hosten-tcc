package br.cefetmg.inf.hosten.model.persistence.interfaces;

import br.cefetmg.inf.hosten.model.domain.Programa;
import java.sql.SQLException;
import java.util.List;

public interface IProgramaDao {

    boolean adiciona(Programa programa) throws SQLException;

    Programa buscaPorPk(String id) throws SQLException;

    List<Programa> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException;

    List<Programa> buscaTodos() throws SQLException;

    boolean atualiza(String id, Programa programaAtualizado) throws SQLException;

    boolean deleta(String programa) throws SQLException;
}
