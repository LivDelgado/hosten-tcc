package br.cefetmg.inf.hosten.model.persistence.interfaces.rel;

import br.cefetmg.inf.hosten.model.domain.rel.Despesa;
import java.sql.SQLException;
import java.util.List;

public interface IDespesaDao {

    List<Despesa> busca(int seqHospedagem, short nroQuarto) throws SQLException;
}
