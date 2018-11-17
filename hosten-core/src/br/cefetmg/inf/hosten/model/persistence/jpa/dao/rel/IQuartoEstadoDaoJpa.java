package br.cefetmg.inf.hosten.model.persistence.jpa.dao.rel;

import br.cefetmg.inf.hosten.model.domain.rel.QuartoEstado;
import java.sql.SQLException;
import java.util.List;

public interface IQuartoEstadoDaoJpa {

    List<QuartoEstado> buscaTodos() throws SQLException;
}
