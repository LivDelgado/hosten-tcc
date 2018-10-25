package br.cefetmg.inf.hosten.model.persistence.jpa.dao.rel;

import br.cefetmg.inf.hosten.model.persistence.jpa.domain.rel.QuartoHospedagem;
import java.sql.SQLException;
import java.util.List;

public interface IQuartoHospedagemDAO {

    boolean adiciona(QuartoHospedagem quartoHospedagem) throws SQLException;

    QuartoHospedagem buscaPorPk(int seqHospedagem, short nroQuarto) throws SQLException;

    List<QuartoHospedagem> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException;

    List<QuartoHospedagem> buscaTodos() throws SQLException;

    boolean deleta(QuartoHospedagem quartoHospedagem) throws SQLException;

    int buscaUltimoRegistro(int nroQuarto) throws SQLException;
}
