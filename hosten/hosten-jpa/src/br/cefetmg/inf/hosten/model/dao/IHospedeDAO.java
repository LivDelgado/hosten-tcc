package br.cefetmg.inf.hosten.model.dao;

import br.cefetmg.inf.hosten.model.domain.Hospedagem;
import br.cefetmg.inf.hosten.model.domain.Hospede;
import java.sql.SQLException;
import java.util.List;

public interface IHospedeDAO {

    boolean adiciona(Hospede hospede) throws SQLException;

    Hospede buscaPorPk(String id) throws SQLException;

    List<Hospede> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException;

    List<Hospede> buscaTodos() throws SQLException;

    boolean atualiza(String id, Hospede hospedeAtualizado) throws SQLException;

    boolean deleta(Hospede hospede) throws SQLException;

    boolean adicionaHospedagemAoHospede(Hospede hospede, Hospedagem hospedagem) throws SQLException;
}
