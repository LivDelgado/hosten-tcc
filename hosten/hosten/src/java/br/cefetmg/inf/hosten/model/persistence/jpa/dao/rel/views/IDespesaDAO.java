package br.cefetmg.inf.hosten.model.persistence.jpa.dao.rel.views;

import br.cefetmg.inf.hosten.model.persistence.jpa.domain.rel.views.Despesa;
import java.sql.SQLException;
import java.util.List;

public interface IDespesaDAO {

    List<Despesa> busca(int seqHospedagem, int nroQuarto) throws SQLException;
}
