package br.cefetmg.inf.hosten.model.dao.rel.impl;

import br.cefetmg.inf.hosten.model.domain.rel.QuartoHospedagem;
import br.cefetmg.inf.util.bd.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.dao.rel.IQuartoHospedagemDAO;
import br.cefetmg.inf.hosten.model.domain.rel.views.QuartoEstado;

public class QuartoHospedagemDAO implements IQuartoHospedagemDAO {

    private static QuartoHospedagemDAO instancia;
    private static Connection con;

    private QuartoHospedagemDAO() {
        con = new ConnectionFactory().getConnection();
    }

    public static synchronized QuartoHospedagemDAO getInstance() {
        if (instancia == null) {
            instancia = new QuartoHospedagemDAO();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(QuartoHospedagem quartoHospedagem) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<QuartoHospedagem> busca(Object dadoBusca, String coluna) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<QuartoEstado> buscaTodos() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deletaPorPk(int seqHospedagem, int nroQuarto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleta(QuartoHospedagem quartoHospedagem) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
