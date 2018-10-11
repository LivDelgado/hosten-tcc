package br.cefetmg.inf.hosten.model.dao.rel.impl;

import br.cefetmg.inf.hosten.model.domain.rel.QuartoConsumo;
import br.cefetmg.inf.util.bd.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import br.cefetmg.inf.hosten.model.dao.rel.IQuartoConsumoDAO;

public class QuartoConsumoDAO implements IQuartoConsumoDAO {

    private static QuartoConsumoDAO instancia;
    private static Connection con;

    private QuartoConsumoDAO() {
        con = new ConnectionFactory().getConnection();
    }

    public static synchronized QuartoConsumoDAO getInstance() {
        if (instancia == null) {
            instancia = new QuartoConsumoDAO();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(QuartoConsumo quartoConsumo) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<QuartoConsumo> busca(Object dadoBusca, String coluna) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deletaPorPk(int seqHospedagem, int nroQuarto, Timestamp datConsumo) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleta(QuartoConsumo quartoConsumo) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
