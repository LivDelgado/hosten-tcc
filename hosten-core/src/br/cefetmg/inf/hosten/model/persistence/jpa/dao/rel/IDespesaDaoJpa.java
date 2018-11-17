package br.cefetmg.inf.hosten.model.persistence.jpa.dao.rel;

import br.cefetmg.inf.hosten.model.domain.rel.Despesa;
import java.sql.SQLException;
import java.util.List;

public interface IDespesaDaoJpa {

    List<Despesa> busca(int seqHospedagem, int nroQuarto) throws SQLException;
}
