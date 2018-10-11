package br.cefetmg.inf.hosten.model.dao.rel.views.impl;

import br.cefetmg.inf.hosten.model.dao.rel.views.IDespesaDAO;
import br.cefetmg.inf.hosten.model.domain.rel.views.Despesa;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DespesaDAO implements IDespesaDAO {
    
    private static DespesaDAO instancia;
    
    private DespesaDAO() {
    }
    
    public static synchronized DespesaDAO getInstance() {
        if (instancia == null) {
            instancia  = new DespesaDAO();
        }
        return instancia;
    }

    @Override
    public List<Despesa> busca(int seqHospedagem, int nroQuarto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, Object> retornaRelatorioDespesas(int seqHospedagem, int nroQuarto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
