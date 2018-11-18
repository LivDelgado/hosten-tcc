package br.cefetmg.inf.hosten.model.persistence.jdbc.rel;

import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import br.cefetmg.inf.hosten.model.domain.idcomposto.QuartoHospedagemId;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoHospedagem;
import br.cefetmg.inf.util.bd.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.rel.IQuartoHospedagemDao;
import java.math.BigDecimal;
import java.sql.Statement;

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

        String qry
                = "INSERT INTO QuartoHospedagem"
                + "(seqHospedagem, nroQuarto, nroAdultos, nroCriancas, vlrDiaria) "
                + "VALUES(?,?,?,?,?)";

        PreparedStatement pStmt = con.prepareStatement(qry);

        pStmt.setInt(1, quartoHospedagem.getId().getSeqHospedagem());
        pStmt.setShort(2, quartoHospedagem.getId().getNroQuarto());
        pStmt.setShort(3, quartoHospedagem.getNroAdultos());
        pStmt.setShort(4, quartoHospedagem.getNroCriancas());
        pStmt.setBigDecimal(5, quartoHospedagem.getVlrDiaria());

        return pStmt.executeUpdate() > 0;
    }

    @Override
    public QuartoHospedagem buscaPorPk(int seqHospedagem, short nroQuarto) throws SQLException {

        String qry
                = "SELECT * FROM QuartoHospedagem "
                + "WHERE seqHospedagem = ? AND nroQuarto = ?";

        PreparedStatement pStmt = con.prepareStatement(qry);

        pStmt.setInt(1, seqHospedagem);
        pStmt.setShort(2, nroQuarto);

        ResultSet rs = pStmt.executeQuery();

        QuartoHospedagem qh = new QuartoHospedagem(
                new QuartoHospedagemId(
                        rs.getInt(1),
                        rs.getShort(2)),
                rs.getShort(3),
                rs.getShort(4),
                rs.getBigDecimal(5));

        return qh;
    }

    @Override
    public List<QuartoHospedagem> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException {

        String qry
                = "SELECT * "
                + "FROM QuartoHospedagem "
                + "WHERE " + coluna + " = ?";

        PreparedStatement pStmt = con.prepareStatement(qry);

        switch (coluna.toLowerCase()) {

            case "seqhospedagem":
                if (dadoBusca instanceof QuartoHospedagem) {
                    pStmt.setInt(1, ((QuartoHospedagem) dadoBusca).getId().getSeqHospedagem());
                } else {
                    pStmt.setInt(1, (int) dadoBusca);
                }
                break;

            case "nroquarto":
                if (dadoBusca instanceof QuartoHospedagem) {
                    pStmt.setShort(1, ((QuartoHospedagem) dadoBusca).getId().getNroQuarto());
                } else {
                    pStmt.setShort(1, (short) dadoBusca);
                }
                break;

            case "nroadultos":
                pStmt.setShort(1, (short) dadoBusca);
                break;

            case "nrocriancas":
                pStmt.setShort(1, (short) dadoBusca);
                break;

            case "vlrdiaria":
                pStmt.setBigDecimal(1, (BigDecimal) dadoBusca);
                break;
        }
        ResultSet rs = pStmt.executeQuery();

        List<QuartoHospedagem> quartoHospedagemEncontrados = new ArrayList<>();

        while (rs.next()) {
            quartoHospedagemEncontrados.add(new QuartoHospedagem(
                    new QuartoHospedagemId(
                            rs.getInt(1),
                            rs.getShort(2)),
                    rs.getShort(3),
                    rs.getShort(4),
                    rs.getBigDecimal(5)));
        }

        return quartoHospedagemEncontrados;
    }

    @Override
    public List<QuartoHospedagem> buscaTodos() throws SQLException {

        String qry
                = "SELECT * FROM QuartoHospedagem";

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(qry);

        List<QuartoHospedagem> quartoHospedagemEncontrados = new ArrayList<>();

        while (rs.next()) {
            quartoHospedagemEncontrados.add(new QuartoHospedagem(
                    new QuartoHospedagemId(
                            rs.getInt(1),
                            rs.getShort(2)),
                    rs.getShort(3),
                    rs.getShort(4),
                    rs.getBigDecimal(5)));
        }

        return quartoHospedagemEncontrados;
    }

    @Override
    public boolean deleta(int seqHospedagem, short nroQuarto) throws SQLException {

        String qry
                = "DELETE FROM QuartoHospedagem "
                + "WHERE seqHospedagem = ? AND "
                + "nroQuarto = ? ";

        PreparedStatement pStmt = con.prepareStatement(qry);

        pStmt.setInt(1, seqHospedagem);
        pStmt.setInt(2, nroQuarto);

        return pStmt.executeUpdate() > 0;
    }

    @Override
    public int buscaUltimoRegistro(short nroQuarto) throws SQLException {

        String qry
                = "SELECT A.seqHospedagem "
                + "FROM Hospedagem A "
                + "JOIN QuartoHospedagem B ON A.seqHospedagem = B.seqHospedagem "
                + "WHERE B.nroQuarto = ? "
                + "ORDER BY A.datCheckIn DESC "
                + "LIMIT 1";

        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setInt(1, nroQuarto);
        ResultSet rs = pStmt.executeQuery();

        if (rs.next()) {
            return rs.getInt(1);
        } else {
            return 0;
        }
    }
}
