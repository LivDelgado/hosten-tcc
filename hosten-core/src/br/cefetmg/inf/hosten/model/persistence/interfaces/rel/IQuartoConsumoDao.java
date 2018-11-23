package br.cefetmg.inf.hosten.model.persistence.interfaces.rel;

import br.cefetmg.inf.hosten.model.domain.rel.QuartoConsumo;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.List;

public interface IQuartoConsumoDao {

    boolean adiciona(QuartoConsumo quartoConsumo) throws SQLException;

    QuartoConsumo buscaPorPk(int seqHospedagem, short nroQuarto, Timestamp datConsumo) throws SQLException;

    List<QuartoConsumo> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException;

    List<QuartoConsumo> buscaTodos() throws SQLException;

    boolean deleta(int seqHospedagem, short nroQuarto, Timestamp datConsumo) throws SQLException;
}
