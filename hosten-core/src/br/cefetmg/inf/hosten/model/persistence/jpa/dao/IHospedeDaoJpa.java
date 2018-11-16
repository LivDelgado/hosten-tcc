package br.cefetmg.inf.hosten.model.persistence.jpa.dao;

import br.cefetmg.inf.hosten.model.persistence.jpa.domain.HospedeJpa;
import java.sql.SQLException;
import java.util.List;

public interface IHospedeDaoJpa {

    boolean adiciona(HospedeJpa hospede) throws SQLException;

    HospedeJpa buscaPorPk(String id) throws SQLException;

    List<HospedeJpa> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException;

    List<HospedeJpa> buscaTodos() throws SQLException;

    boolean atualiza(String id, HospedeJpa hospedeAtualizado) throws SQLException;

    boolean deleta(HospedeJpa hospede) throws SQLException;
}
