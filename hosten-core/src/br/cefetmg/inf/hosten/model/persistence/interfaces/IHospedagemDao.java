package br.cefetmg.inf.hosten.model.persistence.interfaces;

import br.cefetmg.inf.hosten.model.domain.Hospedagem;
import java.sql.SQLException;
import java.util.List;

public interface IHospedagemDao {

    boolean adiciona(Hospedagem hospedagem) throws SQLException;

    Hospedagem buscaPorPk(int id) throws SQLException;

    List<Hospedagem> buscaHospedagem(Hospedagem hospedagem) throws SQLException;

    List<Hospedagem> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException;

    List<Hospedagem> buscaTodos() throws SQLException;

    boolean atualiza(int id, Hospedagem hospedagemAtualizado) throws SQLException;

    boolean deleta(int hospedagem) throws SQLException;
}
