package br.cefetmg.inf.hosten.model.persistence.jpa.dao;

import br.cefetmg.inf.hosten.model.persistence.jpa.domain.ServicoJpa;
import java.sql.SQLException;
import java.util.List;

public interface IServicoDaoJpa {

    boolean adiciona(ServicoJpa servico)
            throws SQLException;

    ServicoJpa buscaPorPk(Short id) throws SQLException;

    List<ServicoJpa> buscaPorColuna(Object dadoBusca, String coluna)
            throws SQLException;

    List<ServicoJpa> buscaTodos()
            throws SQLException;

    boolean atualiza(Short id, ServicoJpa servicoAtualizado)
            throws SQLException;

    boolean deleta(ServicoJpa servico) throws SQLException;
}
