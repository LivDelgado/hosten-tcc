package br.cefetmg.inf.hosten.model.persistence.jdbc.rel;

import br.cefetmg.inf.hosten.model.domain.rel.QuartoHospedagem;
import br.cefetmg.inf.util.bd.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoEstado;
import java.sql.Statement;
import br.cefetmg.inf.hosten.model.persistence.interfaces.rel.IQuartoHospedagemDao;

public class QuartoHospedagemDao implements IQuartoHospedagemDao {

    private static QuartoHospedagemDao instancia;
    private static Connection con;

    private QuartoHospedagemDao() {
        con = new ConnectionFactory().getConnection();
    }

    public static synchronized QuartoHospedagemDao getInstance() {
        if (instancia == null) {
            instancia = new QuartoHospedagemDao();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(QuartoHospedagem quartoHospedagem) throws SQLException {
        String qry = "INSERT INTO "
                + "QuartoHospedagem("
                + "seqHospedagem, "
                + "nroQuarto, "
                + "nroAdultos, nroCriancas, "
                + "vlrDiaria) "
                + "VALUES(?,?,?,?,?)";
        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setInt(1, quartoHospedagem.getSeqHospedagem());
        pStmt.setInt(2, quartoHospedagem.getNroQuarto());
        pStmt.setInt(3, quartoHospedagem.getNroAdultos());
        pStmt.setInt(4, quartoHospedagem.getNroCriancas());
        pStmt.setDouble(5, quartoHospedagem.getVlrDiaria());
        return pStmt.executeUpdate() > 0;
    }

    @Override
    public List<QuartoHospedagem> busca(Object dadoBusca, String coluna)
            throws SQLException {
        String qry = "SELECT * "
                + "FROM QuartoHospedagem "
                + "WHERE " + coluna + " = ?";

        PreparedStatement pStmt = con.prepareStatement(qry);
        if (dadoBusca instanceof String) {
            pStmt.setString(1, dadoBusca.toString());
        } else {
            pStmt.setInt(1, Integer.parseInt(dadoBusca.toString()));
        }

        ResultSet rs = pStmt.executeQuery();

        List<QuartoHospedagem> quartoHospedagemEncontrados = new ArrayList<>();

        int i = 0;
        while (rs.next()) {
            quartoHospedagemEncontrados
                    .add(new QuartoHospedagem(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getInt(3),
                            rs.getInt(4),
                            rs.getDouble(5)));
            i++;
        }
        return quartoHospedagemEncontrados;
    }

    @Override
    public List<QuartoEstado> buscaTodos() throws SQLException {
        String qry
                = "SELECT \n"
                + "	A.seqHospedagem, \n"
                + "	B.nroQuarto, \n"
                + "	A.nroAdultos, \n"
                + "	A.nroCriancas,\n"
                + "	A.vlrDiaria, \n"
                + "	B.idtOcupado, \n"
                + "	A.datCheckOut\n"
                + "FROM \n"
                + "Quarto B\n"
                + "	LEFT JOIN \n"
                + "	(\n"
                + "		SELECT D.seqhospedagem, nroquarto, nroadultos, nrocriancas, vlrdiaria, datcheckin, datcheckout, vlrpago, codcpf\n"
                + "		FROM QuartoHospedagem D\n"
                + "		JOIN Hospedagem E on D.seqhospedagem = E.seqhospedagem\n"
                + "		WHERE datcheckout = NULL OR datcheckout > CURRENT_DATE\n"
                + "	) A ON A.nroQuarto = B.nroQuarto\n"
                + "ORDER BY nroquarto";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(qry);

        List<QuartoEstado> quartoEstadoEncontrados = new ArrayList<>();

        while (rs.next()) {
            quartoEstadoEncontrados
                    .add(new QuartoEstado(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getInt(3),
                            rs.getInt(4),
                            rs.getDouble(5),
                            rs.getBoolean(6),
                            rs.getTimestamp(7)));
        }
        return quartoEstadoEncontrados;
    }

    @Override
    public boolean deletaPorPk(int seqHospedagem, int nroQuarto) throws SQLException {
        String qry = "DELETE FROM QuartoHospedagem "
                + "WHERE seqHospedagem = ? AND "
                + "nroQuarto = ? ";
        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setInt(1, seqHospedagem);
        pStmt.setInt(2, nroQuarto);
        return pStmt.executeUpdate() > 0;
    }

    @Override
    public boolean deleta(QuartoHospedagem quartoHospedagem) throws SQLException {
        String qry = "DELETE FROM QuartoHospedagem "
                + "WHERE seqHospedagem = ? AND "
                + "nroQuarto = ? ";
        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setInt(1, quartoHospedagem.getSeqHospedagem());
        pStmt.setInt(2, quartoHospedagem.getNroQuarto());
        return pStmt.executeUpdate() > 0;
    }

}
