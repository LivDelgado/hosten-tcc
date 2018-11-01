package br.cefetmg.inf.hosten.model.persistence.interfaces.rel;

import br.cefetmg.inf.hosten.model.domain.rel.Despesa;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IDespesaDao {
    List<Despesa> busca(int seqHospedagem, int nroQuarto) throws SQLException;
    
    Map<String, Object> retornaRelatorioDespesas(int seqHospedagem, int nroQuarto) 
            throws SQLException;
}
