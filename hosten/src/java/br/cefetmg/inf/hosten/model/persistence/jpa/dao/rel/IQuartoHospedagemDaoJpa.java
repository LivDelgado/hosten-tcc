package br.cefetmg.inf.hosten.model.persistence.jpa.dao.rel;

import br.cefetmg.inf.hosten.model.persistence.jpa.domain.rel.QuartoHospedagemJpa;
import java.sql.SQLException;
import java.util.List;

public interface IQuartoHospedagemDaoJpa {

    boolean adiciona(QuartoHospedagemJpa quartoHospedagem) throws SQLException;

    QuartoHospedagemJpa buscaPorPk(int seqHospedagem, short nroQuarto) throws SQLException;

    List<QuartoHospedagemJpa> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException;

    List<QuartoHospedagemJpa> buscaTodos() throws SQLException;

    boolean deleta(QuartoHospedagemJpa quartoHospedagem) throws SQLException;

    int buscaUltimoRegistro(int nroQuarto) throws SQLException;
}
