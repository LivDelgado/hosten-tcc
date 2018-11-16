package br.cefetmg.inf.hosten.model.persistence.jpa.dao.rel;

import br.cefetmg.inf.hosten.model.persistence.jpa.domain.rel.QuartoConsumoJpa;
import br.cefetmg.inf.hosten.model.persistence.jpa.domain.rel.QuartoHospedagemJpa;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface IQuartoConsumoDaoJpa {

    boolean adiciona(QuartoConsumoJpa quartoConsumo) throws SQLException;

    QuartoConsumoJpa buscaPorPk(QuartoHospedagemJpa qh, Date datConsumo) throws SQLException;

    List<QuartoConsumoJpa> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException;

    List<QuartoConsumoJpa> buscaTodos() throws SQLException;

    boolean deleta(QuartoConsumoJpa quartoConsumo) throws SQLException;
}
