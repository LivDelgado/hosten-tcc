package br.cefetmg.inf.hosten.model.persistence.jpa.dao.rel;

import br.cefetmg.inf.hosten.model.persistence.jpa.domain.rel.views.DespesaJpa;
import java.sql.SQLException;
import java.util.List;

public interface IDespesaDaoJpa {

    List<DespesaJpa> busca(int seqHospedagem, int nroQuarto) throws SQLException;
}
