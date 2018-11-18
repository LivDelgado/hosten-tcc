package br.cefetmg.inf.hosten.model.persistence.interfaces.rel;

import br.cefetmg.inf.hosten.model.domain.rel.QuartoHospedagem;
import java.sql.SQLException;
import java.util.List;

public interface IQuartoHospedagemDao {

    boolean adiciona(QuartoHospedagem quartoHospedagem) throws SQLException;

    QuartoHospedagem buscaPorPk(int seqHospedagem, short nroQuarto) throws SQLException;

    List<QuartoHospedagem> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException;

    List<QuartoHospedagem> buscaTodos() throws SQLException;

    boolean deleta(int seqHospedagem, int nroQuarto) throws SQLException;

    int buscaUltimoRegistro(short nroQuarto) throws SQLException;
}
