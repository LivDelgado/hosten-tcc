package br.cefetmg.inf.hosten.model.dao.rel.views;

import br.cefetmg.inf.hosten.model.domain.rel.views.QuartoEstado;
import java.sql.SQLException;
import java.util.List;

public interface IQuartoEstadoDAO {
    List<QuartoEstado> buscaTodos() throws SQLException;
}
