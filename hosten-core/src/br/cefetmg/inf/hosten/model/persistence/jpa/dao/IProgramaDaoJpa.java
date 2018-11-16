package br.cefetmg.inf.hosten.model.persistence.jpa.dao;

import br.cefetmg.inf.hosten.model.persistence.jpa.domain.ProgramaJpa;
import java.sql.SQLException;
import java.util.List;

public interface IProgramaDaoJpa {

    boolean adiciona(ProgramaJpa programa) throws SQLException;

    ProgramaJpa buscaPorPk(String id) throws SQLException;

    List<ProgramaJpa> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException;

    List<ProgramaJpa> buscaTodos() throws SQLException;

    boolean atualiza(String id, ProgramaJpa programaAtualizado) throws SQLException;

    boolean deleta(ProgramaJpa programa) throws SQLException;
}
