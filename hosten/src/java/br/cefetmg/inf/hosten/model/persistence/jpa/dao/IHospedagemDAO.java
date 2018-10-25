package br.cefetmg.inf.hosten.model.persistence.jpa.dao;

import br.cefetmg.inf.hosten.model.persistence.jpa.domain.Hospedagem;
import java.sql.SQLException;
import java.util.List;

public interface IHospedagemDAO {

    boolean adiciona(Hospedagem hospedagem) throws SQLException;

    Hospedagem buscaPorPk(Integer id) throws SQLException;

    List<Hospedagem> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException;

    List<Hospedagem> buscaTodos() throws SQLException;

    boolean atualiza(Integer id, Hospedagem hospedagemAtualizado) throws SQLException;

    boolean deleta(Hospedagem hospedagem) throws SQLException;
}
