package br.cefetmg.inf.hosten.model.persistence.jpa.dao.rel;

import br.cefetmg.inf.hosten.model.persistence.jpa.domain.rel.views.QuartoEstadoJpa;
import java.sql.SQLException;
import java.util.List;

public interface IQuartoEstadoDaoJpa {

    List<QuartoEstadoJpa> buscaTodos() throws SQLException;
}
