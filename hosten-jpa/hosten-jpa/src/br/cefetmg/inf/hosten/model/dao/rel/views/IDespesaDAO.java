package br.cefetmg.inf.hosten.model.dao.rel.views;

import br.cefetmg.inf.hosten.model.domain.rel.views.Despesa;
import java.sql.SQLException;
import java.util.List;

public interface IDespesaDAO {

    List<Despesa> busca(int seqHospedagem, int nroQuarto) throws SQLException;
}
