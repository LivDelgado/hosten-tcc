package br.cefetmg.inf.hosten.model.persistence.interfaces.rel;

import br.cefetmg.inf.hosten.model.domain.rel.QuartoConsumo;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoHospedagem;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface IQuartoConsumoDao {

    boolean adiciona(QuartoConsumo quartoConsumo) throws SQLException;

    QuartoConsumo buscaPorPk(QuartoHospedagem qh, Date datConsumo) throws SQLException;

    List<QuartoConsumo> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException;

    List<QuartoConsumo> buscaTodos() throws SQLException;

    boolean deleta(int seqHospedagem, int nroQuarto, Timestamp datConsumo) throws SQLException;
}
