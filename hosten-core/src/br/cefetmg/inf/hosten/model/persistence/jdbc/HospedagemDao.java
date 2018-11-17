package br.cefetmg.inf.hosten.model.persistence.jdbc;

import br.cefetmg.inf.hosten.model.domain.Hospedagem;
import br.cefetmg.inf.hosten.model.domain.Hospede;
import br.cefetmg.inf.util.bd.BdUtils;
import br.cefetmg.inf.util.bd.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IHospedagemDao;
import java.math.BigDecimal;
import java.util.Date;

public final class HospedagemDao implements IHospedagemDao {

    private static Connection con;
    private static HospedagemDao instancia;

    private HospedagemDao() {
        super();
        con = new ConnectionFactory().getConnection();
    }

    public static synchronized HospedagemDao getInstance() {
        if (instancia == null) {
            instancia = new HospedagemDao();
        }
        return instancia;
    }

    @Override
    public boolean adicionaHospedagem(
            Hospedagem hospedagem) throws SQLException {
        String qry = "INSERT INTO Hospedagem"
                + "(datCheckIn, datCheckOut, vlrPago, codCPF)"
                + " VALUES (?,?,?,?)";

        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setTimestamp(1, new Timestamp(hospedagem.getDatCheckin().getTime()));
        pStmt.setTimestamp(2, new Timestamp(hospedagem.getDatCheckout().getTime()));
        pStmt.setDouble(3, hospedagem.getVlrPago().doubleValue());
        pStmt.setString(4, hospedagem.getHospede().getCodCpf());

        return pStmt.executeUpdate() > 0;
    }

    @Override
    public List<Hospedagem> buscaHospedagem(Object dadoBusca, String coluna)
            throws SQLException {
        String qry = "SELECT * FROM Hospedagem "
                + "WHERE " + coluna + " "
                + "= ?";
        PreparedStatement pStmt = con.prepareStatement(qry);

        if (dadoBusca instanceof String) {
            pStmt.setString(1, dadoBusca.toString());
        } else {
            pStmt.setInt(1, Integer.parseInt(dadoBusca.toString()));
        }
        ResultSet rs = pStmt.executeQuery();

        List<Hospedagem> hospedagemEncontrados = new ArrayList<>();

        while (rs.next()) {
            Hospedagem h = new Hospedagem(
                    new Date(rs.getTimestamp(2).getTime()),
                    new Date(rs.getTimestamp(3).getTime()),
                    BigDecimal.valueOf(rs.getDouble(4)));
            h.setSeqHospedagem(rs.getInt(1));
            h.setHospede(new Hospede(rs.getString(5)));
            
            hospedagemEncontrados.add(h);
        }

        return hospedagemEncontrados;
    }

    @Override
    public List<Hospedagem> buscaTodosHospedagems() throws SQLException {
        Statement stmt = con.createStatement();

        String qry = "SELECT * FROM Hospedagem";
        ResultSet rs = stmt.executeQuery(qry);

        List<Hospedagem> hospedagemsEncontrados = new ArrayList<>();

        while (rs.next()) {
            Hospedagem h = new Hospedagem(
                    new Date(rs.getTimestamp(2).getTime()),
                    new Date(rs.getTimestamp(3).getTime()),
                    BigDecimal.valueOf(rs.getDouble(4)));
            h.setSeqHospedagem(rs.getInt(1));
            h.setHospede(new Hospede(rs.getString(5)));

            hospedagemsEncontrados.add(h);
        }

        return hospedagemsEncontrados;
    }

    @Override
    public boolean atualizaHospedagemPorPk(
            Object pK,
            Hospedagem hospedagemAtualizado) throws SQLException {
        String qry = "UPDATE Hospedagem "
                + "SET datCheckIn = ?, datCheckOut = ?, vlrPago = ?, "
                + "codCPF = ? "
                + "WHERE seqHospedagem = ?";
        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setTimestamp(1, new Timestamp(hospedagemAtualizado.getDatCheckin().getTime()));
        pStmt.setTimestamp(2, new Timestamp(hospedagemAtualizado.getDatCheckout().getTime()));
        pStmt.setDouble(3, hospedagemAtualizado.getVlrPago().doubleValue());
        pStmt.setString(4, hospedagemAtualizado.getHospede().getCodCpf());
        pStmt.setInt(5, Integer.parseInt(pK.toString()));

        return pStmt.executeUpdate() > 0;
    }

    public boolean atualiza(Timestamp datCheckInAntiga,
            Timestamp datCheckOutAntiga,
            Double vlrPagoAntigo, String codCPFAntigo,
            Hospedagem hospedagemAtualizado) throws SQLException {
        String qry = "SELECT * FROM Hospedagem "
                + "WHERE datCheckIn = ? AND datCheckOut = ? "
                + "AND vlrPago = ? AND codCPF = ?";

        PreparedStatement pStmt = con.prepareStatement(qry,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        pStmt.setTimestamp(1, datCheckInAntiga);
        pStmt.setTimestamp(2, datCheckOutAntiga);
        pStmt.setDouble(3, vlrPagoAntigo);
        pStmt.setString(4, codCPFAntigo);

        ResultSet rs = pStmt.executeQuery();

        if (BdUtils.contaLinhasResultSet(rs) == 1) {
            qry = "UPDATE Hospedagem "
                    + "SET datCheckIn = ?, datCheckOut = ?, vlrPago = ?, codCPF = ? "
                    + "WHERE datCheckIn = ? AND datCheckOut = ? AND vlrPago = ? "
                    + "AND codCPF = ?";
            pStmt = con.prepareStatement(qry);
            pStmt.setTimestamp(1, new Timestamp(hospedagemAtualizado.getDatCheckin().getTime()));
            pStmt.setTimestamp(2, new Timestamp(hospedagemAtualizado.getDatCheckout().getTime()));
            pStmt.setDouble(3, hospedagemAtualizado.getVlrPago().doubleValue());
            pStmt.setString(4, hospedagemAtualizado.getHospede().getCodCpf());
            pStmt.setTimestamp(5, datCheckInAntiga);
            pStmt.setTimestamp(6, datCheckOutAntiga);
            pStmt.setDouble(7, vlrPagoAntigo);
            pStmt.setString(8, codCPFAntigo);

            return pStmt.executeUpdate() > 0;
        }
        return false;
    }

    @Override
    public boolean deletaHospedagem(Object pK) throws SQLException {
        String qry = "DELETE FROM Hospedagem "
                + "WHERE seqHospedagem = ?";
        PreparedStatement pStmt = con.prepareStatement(qry);
        if (pK instanceof String) {
            pStmt.setString(1, pK.toString());
        } else {
            pStmt.setInt(1, Integer.parseInt(pK.toString()));
        }

        return pStmt.executeUpdate() > 0;
    }

    public boolean deleta(Hospedagem hospedagemAntiga) throws SQLException {
        String qry = "DELETE FROM Hospedagem "
                + "WHERE datCheckIn = ? AND datCheckOut = ? AND vlrPago = ? AND "
                + "codCPF = ?";
        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setTimestamp(1, new Timestamp(hospedagemAntiga.getDatCheckin().getTime()));
        pStmt.setTimestamp(2, new Timestamp(hospedagemAntiga.getDatCheckout().getTime()));
        pStmt.setDouble(3, hospedagemAntiga.getVlrPago().doubleValue());
        pStmt.setString(4, hospedagemAntiga.getHospede().getCodCpf());

        return pStmt.executeUpdate() > 0;
    }

    public boolean deleta(Timestamp datCheckInAntiga, Timestamp datCheckOutAntiga,
            Double vlrPagoAntigo, String codCPFAntigo) throws SQLException {
        String qry = "SELECT * FROM Hospedagem "
                + "WHERE datCheckIn = ? AND datCheckOut = ? AND vlrPago = ? AND "
                + "codCPF = ?";

        PreparedStatement pStmt = con.prepareStatement(qry, ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        pStmt.setTimestamp(1, datCheckInAntiga);
        pStmt.setTimestamp(2, datCheckOutAntiga);
        pStmt.setDouble(3, vlrPagoAntigo);
        pStmt.setString(4, codCPFAntigo);

        ResultSet rs = pStmt.executeQuery();

        if (BdUtils.contaLinhasResultSet(rs) == 1) {
            qry = "DELETE FROM Hospedagem "
                    + "WHERE datCheckIn = ? AND datCheckOut = ? AND vlrPago = ? "
                    + "AND codCPF = ?";
            pStmt = con.prepareStatement(qry);
            pStmt.setTimestamp(1, datCheckInAntiga);
            pStmt.setTimestamp(2, datCheckOutAntiga);
            pStmt.setDouble(3, vlrPagoAntigo);
            pStmt.setString(4, codCPFAntigo);

            return pStmt.executeUpdate() > 0;
        }
        return false;
    }

    public List<Hospedagem> busca(Hospedagem hospedagem) throws SQLException {
        String qry = "SELECT * FROM Hospedagem WHERE "
                + "datCheckIn=? AND datCheckOut=? AND vlrPago=? AND codCPF=?";

        PreparedStatement pStmt = con.prepareStatement(qry, ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        pStmt.setTimestamp(1, new Timestamp(hospedagem.getDatCheckin().getTime()));
        pStmt.setTimestamp(2, new Timestamp(hospedagem.getDatCheckout().getTime()));
        pStmt.setDouble(3, hospedagem.getVlrPago().doubleValue());
        pStmt.setString(4, hospedagem.getHospede().getCodCpf());

        ResultSet rs = pStmt.executeQuery();
        List<Hospedagem> hospedagemsEncontrados = new ArrayList<>();

        while (rs.next()) {
            Hospedagem h = new Hospedagem(
                    new Date(rs.getTimestamp(2).getTime()),
                    new Date(rs.getTimestamp(3).getTime()),
                    BigDecimal.valueOf(rs.getDouble(4)));
            h.setSeqHospedagem(rs.getInt(1));
            h.setHospede(new Hospede(rs.getString(5)));

            hospedagemsEncontrados.add(h);
        }

        return hospedagemsEncontrados;
    }
}
