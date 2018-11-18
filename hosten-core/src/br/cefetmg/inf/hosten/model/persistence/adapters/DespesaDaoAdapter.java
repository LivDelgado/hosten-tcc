package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.domain.rel.Despesa;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.jdbc.rel.DespesaDao;
import java.util.Map;
import br.cefetmg.inf.hosten.model.persistence.interfaces.rel.IDespesaDao;

public class DespesaDaoAdapter implements IDespesaDao {
    
    private final IDespesaDao dao;
    private static IDespesaDao instancia;

    public DespesaDaoAdapter() {
        dao = DespesaDao.getInstance();
    }
    
    public static synchronized IDespesaDao getInstance() {
        if (instancia == null) {
            instancia  = new DespesaDaoAdapter();
        }
        return instancia;
    }
    
    @Override
    public List<Despesa> busca(int seqHospedagem, short nroQuarto) throws SQLException {
        return dao.busca(seqHospedagem, nroQuarto);
    }
}
