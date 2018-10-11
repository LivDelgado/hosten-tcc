package br.cefetmg.inf.hosten.model.dao;

import br.cefetmg.inf.hosten.model.domain.Servico;
import java.sql.SQLException;
import java.util.List;

public interface IServicoDAO {
    boolean adiciona(Servico servico)
            throws SQLException;

    Servico buscaPorPk(String id) throws SQLException;

    List<Servico> buscaPorColuna(Object dadoBusca, String coluna)
            throws SQLException;

    List<Servico> buscaTodos()
            throws SQLException;

    boolean atualiza(String id, Servico servicoAtualizado)
            throws SQLException;

    boolean deleta(Servico servico) throws SQLException;
}
