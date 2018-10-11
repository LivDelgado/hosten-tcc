package br.cefetmg.inf.hosten.model.dao;

import br.cefetmg.inf.hosten.model.domain.Quarto;
import java.sql.SQLException;
import java.util.List;

public interface IQuartoDAO {

    boolean adiciona(Quarto quarto) throws SQLException;

    Quarto buscaPorPk(String id) throws SQLException;

    List<Quarto> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException;

    List<Quarto> buscaTodos() throws SQLException;

    boolean atualiza(String id, Quarto quartoAtualizado) throws SQLException;

    boolean deleta(Quarto quarto) throws SQLException;
}
