package br.cefetmg.inf.hosten.model.persistence.jdbc;

import br.cefetmg.inf.hosten.model.domain.Hospedagem;
import br.cefetmg.inf.hosten.model.domain.Hospede;
import br.cefetmg.inf.util.bd.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

        String qry
                = "INSERT INTO Hospedagem"
                + "(datCheckIn, datCheckOut, vlrPago, codCPF) "
                + "VALUES (?,?,?,?)";

        PreparedStatement pStmt = con.prepareStatement(qry);

        pStmt.setDate(1, hospedagem.getDatCheckin());
        pStmt.setDate(2, hospedagem.getDatCheckout());
        pStmt.setBigDecimal(3, hospedagem.getVlrPago());
        pStmt.setString(4, hospedagem.getHospede().getCodCpf());

        return pStmt.executeUpdate() > 0;
    }

    @Override
    public Hospedagem buscaPorPk(int id) throws SQLException {

        String qry
                = "SELECT * FROM Hospedagem "
                + "WHERE seqHospedagem = ?";

        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setInt(1, id);
        ResultSet rs = pStmt.executeQuery();

        Hospedagem h = new Hospedagem(
                rs.getInt(1),
                rs.getDate(2),
                rs.getDate(3),
                rs.getBigDecimal(4));
        h.setHospede(new Hospede(rs.getString(5)));

        return h;
    }

    @Override
    public List<Hospedagem> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException {

        String qry
                = "SELECT * FROM Hospedagem "
                + "WHERE " + coluna + " = ?";

        PreparedStatement pStmt = con.prepareStatement(qry);

        switch (coluna.toLowerCase()) {

            case "seqhospedagem":
                if (dadoBusca instanceof Hospedagem) {
                    pStmt.setInt(1, ((Hospedagem) dadoBusca).getSeqHospedagem());
                } else {
                    pStmt.setInt(1, (int) dadoBusca);
                }
                break;

            case "datcheckin":
                pStmt.setDate(1, (Date) dadoBusca);
                break;

            case "datcheckout":
                pStmt.setDate(1, (Date) dadoBusca);
                break;

            case "vlrpago":
                pStmt.setBigDecimal(1, (BigDecimal) dadoBusca);
                break;

            case "codcpf":
                if (dadoBusca instanceof Hospede) {
                    pStmt.setString(1, ((Hospede) dadoBusca).getCodCpf());
                } else {
                    pStmt.setString(1, dadoBusca.toString());
                }
                break;
        }
        ResultSet rs = pStmt.executeQuery();

        List<Hospedagem> hospedagemEncontrados = new ArrayList<>();

        while (rs.next()) {
            Hospedagem h = new Hospedagem(
                    rs.getInt(1),
                    rs.getDate(2),
                    rs.getDate(3),
                    rs.getBigDecimal(4));
            h.setHospede(new Hospede(rs.getString(5)));

            hospedagemEncontrados.add(h);
        }

        return hospedagemEncontrados;
    }

    @Override
    public List<Hospedagem> buscaTodos() throws SQLException {

        String qry
                = "SELECT * FROM Hospedagem";

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(qry);

        List<Hospedagem> hospedagemsEncontrados = new ArrayList<>();

        while (rs.next()) {
            Hospedagem h = new Hospedagem(
                    rs.getInt(1),
                    rs.getDate(2),
                    rs.getDate(3),
                    rs.getBigDecimal(4));
            h.setHospede(new Hospede(rs.getString(5)));

            hospedagemsEncontrados.add(h);
        }

        return hospedagemsEncontrados;
    }

    @Override
    public boolean atualiza(int id, Hospedagem hospedagemAtualizado) throws SQLException {

        String qry
                = "UPDATE Hospedagem "
                + "SET datCheckIn = ?, datCheckOut = ?, vlrPago = ?, codCPF = ? "
                + "WHERE seqHospedagem = ?";

        PreparedStatement pStmt = con.prepareStatement(qry);

        pStmt.setDate(1, hospedagemAtualizado.getDatCheckin());
        pStmt.setDate(1, hospedagemAtualizado.getDatCheckout());
        pStmt.setBigDecimal(3, hospedagemAtualizado.getVlrPago());
        pStmt.setString(4, hospedagemAtualizado.getHospede().getCodCpf());
        pStmt.setInt(5, id);

        return pStmt.executeUpdate() > 0;
    }

    @Override
    public boolean deleta(int id) throws SQLException {

        String qry
                = "DELETE FROM Hospedagem "
                + "WHERE seqHospedagem = ?";

        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setInt(1, id);

        return pStmt.executeUpdate() > 0;
    }
}
