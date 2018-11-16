package br.cefetmg.inf.hosten.model.persistence.jpa.dao;

import br.cefetmg.inf.hosten.model.persistence.jpa.domain.HospedagemJpa;
import java.sql.SQLException;
import java.util.List;

public interface IHospedagemDaoJpa {

    boolean adiciona(HospedagemJpa hospedagem) throws SQLException;

    HospedagemJpa buscaPorPk(Integer id) throws SQLException;

    List<HospedagemJpa> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException;

    List<HospedagemJpa> buscaTodos() throws SQLException;

    boolean atualiza(Integer id, HospedagemJpa hospedagemAtualizado) throws SQLException;

    boolean deleta(HospedagemJpa hospedagem) throws SQLException;
}
