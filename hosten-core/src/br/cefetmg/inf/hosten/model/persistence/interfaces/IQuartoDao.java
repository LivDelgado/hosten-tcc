package br.cefetmg.inf.hosten.model.persistence.interfaces;

import br.cefetmg.inf.hosten.model.domain.Quarto;
import java.sql.SQLException;
import java.util.List;

public interface IQuartoDao {

    boolean adiciona(Quarto quarto) throws SQLException;

    Quarto buscaPorPk(Short id) throws SQLException;

    List<Quarto> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException;

    List<Quarto> buscaTodos() throws SQLException;

    boolean atualiza(Short id, Quarto quartoAtualizado) throws SQLException;

    boolean deleta(Short id) throws SQLException;
}
