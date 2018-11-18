package br.cefetmg.inf.hosten.model.persistence.interfaces;

import br.cefetmg.inf.hosten.model.domain.Servico;
import java.sql.SQLException;
import java.util.List;

public interface IServicoDao {

    boolean adiciona(Servico servico)
            throws SQLException;

    Servico buscaPorPk(short id) throws SQLException;

    List<Servico> buscaPorColuna(Object dadoBusca, String coluna)
            throws SQLException;

    List<Servico> buscaTodos()
            throws SQLException;

    boolean atualiza(short id, Servico servicoAtualizado)
            throws SQLException;

    boolean deleta(short id) throws SQLException;
}
