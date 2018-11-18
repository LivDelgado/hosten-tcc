package br.cefetmg.inf.hosten.model.persistence.jdbc.rel;

import br.cefetmg.inf.hosten.model.domain.Servico;
import br.cefetmg.inf.hosten.model.domain.Usuario;
import br.cefetmg.inf.hosten.model.domain.idcomposto.QuartoConsumoId;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoConsumo;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoHospedagem;
import br.cefetmg.inf.util.bd.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.rel.IQuartoConsumoDao;
import java.sql.Date;
import java.sql.Statement;

public class QuartoConsumoDao implements IQuartoConsumoDao {

    private static QuartoConsumoDao instancia;
    private static Connection con;

    private QuartoConsumoDao() {
        con = new ConnectionFactory().getConnection();
    }

    public static synchronized QuartoConsumoDao getInstance() {
        if (instancia == null) {
            instancia = new QuartoConsumoDao();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(QuartoConsumo quartoConsumo) throws SQLException {
        String qry = "INSERT INTO "
                + "QuartoConsumo(seqHospedagem, nroQuarto, datConsumo,"
                + "qtdConsumo, seqServico, codUsuarioRegistro) "
                + "VALUES(?,?,?,?,?,?)";
        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setInt(1, quartoConsumo.getQuartoHospedagem().getId().getSeqHospedagem());
        pStmt.setInt(2, quartoConsumo.getQuartoHospedagem().getId().getNroQuarto());
        pStmt.setDate(3, quartoConsumo.getDatConsumo());
        pStmt.setInt(4, quartoConsumo.getQtdConsumo());
        pStmt.setInt(5, quartoConsumo.getServico().getSeqServico());
        pStmt.setString(6, quartoConsumo.getUsuarioRegistro().getCodUsuario());
        return pStmt.executeUpdate() > 0;
    }
    
    @Override
    public QuartoConsumo buscaPorPk(int seqHospedagem, short nroQuarto, Date datConsumo) throws SQLException {
        String qry = 
                "SELECT * "
                + "FROM QuartoConsumo "
                + "WHERE seqHospedage = ?"
                + " AND nroQuarto = ? AND datConsumo = ?";
        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setInt(1, seqHospedagem);
        pStmt.setShort(2, nroQuarto);
        pStmt.setDate(3, datConsumo);
        ResultSet rs = pStmt.executeQuery();

        QuartoConsumo qc = new QuartoConsumo(
                            new QuartoConsumoId(
                                    new QuartoHospedagem(
                                            rs.getInt(1),
                                            rs.getShort(2)),
                                    rs.getDate(3)),
                            rs.getShort(4),
                            new Servico(rs.getShort(5)),
                            new Usuario(rs.getString(6)));
        
        return qc;
    }

    @Override
    public List<QuartoConsumo> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException {

        String qry = "SELECT * "
                + "FROM QuartoConsumo "
                + "WHERE " + coluna + " = ?";

        PreparedStatement pStmt = con.prepareStatement(qry);
        
        if (dadoBusca instanceof String) {
            pStmt.setString(1, dadoBusca.toString());
        } else {
            pStmt.setInt(1, Integer.parseInt(dadoBusca.toString()));
        }

        ResultSet rs = pStmt.executeQuery();

        List<QuartoConsumo> quartoConsumoEncontrados = new ArrayList<>();

        while (rs.next()) {
            quartoConsumoEncontrados
                    .add(new QuartoConsumo(
                            new QuartoConsumoId(
                                    new QuartoHospedagem(
                                            rs.getInt(1),
                                            rs.getShort(2)),
                                    rs.getDate(3)),
                            rs.getShort(4),
                            new Servico(rs.getShort(5)),
                            new Usuario(rs.getString(6))));
        }
        return quartoConsumoEncontrados;
    }
    
    @Override
    public List<QuartoConsumo> buscaTodos() throws SQLException {
        String qry = "SELECT * FROM QuartoConsumo";

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(qry);

        List<QuartoConsumo> quartoConsumoEncontrados = new ArrayList<>();

        while (rs.next()) {
            quartoConsumoEncontrados
                    .add(new QuartoConsumo(
                            new QuartoConsumoId(
                                    new QuartoHospedagem(
                                            rs.getInt(1),
                                            rs.getShort(2)),
                                    rs.getDate(3)),
                            rs.getShort(4),
                            new Servico(rs.getShort(5)),
                            new Usuario(rs.getString(6))));
        }
        return quartoConsumoEncontrados;
    }

    @Override
    public boolean deleta(int seqHospedagem, short nroQuarto, Date datConsumo) throws SQLException {
        String qry = "DELETE FROM QuartoConsumo "
                + "WHERE seqHospedagem = ? AND nroQuarto = ? AND datConsumo = ?";
        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setInt(1, seqHospedagem);
        pStmt.setInt(2, nroQuarto);
        pStmt.setDate(3, datConsumo);
        return pStmt.executeUpdate() > 0;
    }
}
