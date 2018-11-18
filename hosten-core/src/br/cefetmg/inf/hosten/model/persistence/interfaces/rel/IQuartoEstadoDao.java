package br.cefetmg.inf.hosten.model.persistence.interfaces.rel;

import br.cefetmg.inf.hosten.model.domain.rel.QuartoEstado;
import java.sql.SQLException;
import java.util.List;

public interface IQuartoEstadoDao {
    
    List<QuartoEstado> buscaTodos() throws SQLException;
}
