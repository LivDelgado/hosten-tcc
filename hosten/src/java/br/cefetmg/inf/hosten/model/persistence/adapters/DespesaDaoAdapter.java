package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.domain.rel.Despesa;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.jdbc.rel.DespesaDao;
import java.util.Map;
import br.cefetmg.inf.hosten.model.persistence.interfaces.rel.IDespesaDao;

public class DespesaDaoAdapter implements IDespesaDao {
    
    private static IDespesaDao instancia;
    
    public static synchronized IDespesaDao getInstance() {
        if (instancia == null) {
            instancia  = DespesaDao.getInstance();
        }
        return instancia;
    }
    
    @Override
    public List<Despesa> busca(int seqHospedagem, int nroQuarto) throws SQLException {
        return instancia.busca(seqHospedagem, nroQuarto);
    }
    
    @Override
    public Map<String, Object> retornaDespesa(int seqHospedagem, int nroQuarto) 
            throws SQLException {
        return instancia.retornaDespesa(seqHospedagem, nroQuarto);
    }
}
