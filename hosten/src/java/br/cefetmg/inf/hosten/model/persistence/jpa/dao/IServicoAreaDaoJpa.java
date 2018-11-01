package br.cefetmg.inf.hosten.model.persistence.jpa.dao;

import br.cefetmg.inf.hosten.model.persistence.jpa.domain.ServicoAreaJpa;
import java.sql.SQLException;
import java.util.List;

public interface IServicoAreaDaoJpa {

    boolean adiciona(ServicoAreaJpa servicoArea) throws SQLException;

    ServicoAreaJpa buscaPorPk(String id) throws SQLException;

    List<ServicoAreaJpa> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException;

    List<ServicoAreaJpa> buscaTodos() throws SQLException;

    boolean atualiza(String id, ServicoAreaJpa servicoAreaAtualizado) throws SQLException;

    boolean deleta(ServicoAreaJpa servicoArea) throws SQLException;
}
