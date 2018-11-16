package br.cefetmg.inf.hosten.model.persistence.jpa.dao;

import br.cefetmg.inf.hosten.model.persistence.jpa.domain.QuartoJpa;
import java.sql.SQLException;
import java.util.List;

public interface IQuartoDaoJpa {

    boolean adiciona(QuartoJpa quarto) throws SQLException;

    QuartoJpa buscaPorPk(Short id) throws SQLException;

    List<QuartoJpa> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException;

    List<QuartoJpa> buscaTodos() throws SQLException;

    boolean atualiza(Short id, QuartoJpa quartoAtualizado) throws SQLException;

    boolean deleta(QuartoJpa quarto) throws SQLException;
}
