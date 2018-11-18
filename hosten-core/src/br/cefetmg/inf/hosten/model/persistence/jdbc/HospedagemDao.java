package br.cefetmg.inf.hosten.model.persistence.jdbc;

import br.cefetmg.inf.hosten.model.domain.Hospedagem;
import br.cefetmg.inf.hosten.model.domain.Hospede;
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
import java.sql.Date;

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
    public boolean adiciona(Hospedagem hospedagem) throws SQLException {
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
    public Hospedagem buscaPorPk(int id) throws SQLException {
        String qry = "SELECT * FROM Hospedagem "
                + "WHERE seqHospedagem "
                + "= ?";
        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setInt(1, id);
        ResultSet rs = pStmt.executeQuery();

        Hospedagem h = new Hospedagem(
                rs.getInt(1),
                new Date(rs.getTimestamp(2).getTime()),
                new Date(rs.getTimestamp(3).getTime()),
                BigDecimal.valueOf(rs.getDouble(4)),
                new Hospede(rs.getString(5)));

        return h;
    }

    @Override
    public List<Hospedagem> buscaPorColuna(Object dadoBusca, String coluna)
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
    public List<Hospedagem> buscaTodos() throws SQLException {
        Statement stmt = con.createStatement();

        String qry = "SELECT * FROM Hospedagem";
        ResultSet rs = stmt.executeQuery(qry);

        List<Hospedagem> hospedagemsEncontrados = new ArrayList<>();

        while (rs.next()) {
            Hospedagem h = new Hospedagem(
                    new Date(rs.getDate(2).getTime()),
                    new Date(rs.getDate(3).getTime()),
                    rs.getBigDecimal(4));
            h.setSeqHospedagem(rs.getInt(1));
            h.setHospede(new Hospede(rs.getString(5)));

            hospedagemsEncontrados.add(h);
        }

        return hospedagemsEncontrados;
    }

    @Override
    public boolean atualiza(int pK, Hospedagem hospedagemAtualizado) throws SQLException {
        String qry = "UPDATE Hospedagem "
                + "SET datCheckIn = ?, datCheckOut = ?, vlrPago = ?, "
                + "codCPF = ? "
                + "WHERE seqHospedagem = ?";
        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setDate(1, new Date(hospedagemAtualizado.getDatCheckin().getTime()));
        pStmt.setDate(2, new Date(hospedagemAtualizado.getDatCheckout().getTime()));
        pStmt.setBigDecimal(3, hospedagemAtualizado.getVlrPago());
        pStmt.setString(4, hospedagemAtualizado.getHospede().getCodCpf());
        pStmt.setInt(5, pK);

        return pStmt.executeUpdate() > 0;
    }

    @Override
    public boolean deleta(int pK) throws SQLException {
        String qry = "DELETE FROM Hospedagem "
                + "WHERE seqHospedagem = ?";
        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setInt(1, pK);

        return pStmt.executeUpdate() > 0;
    }
}
