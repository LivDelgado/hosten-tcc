package br.cefetmg.inf.hosten.model.persistence.jdbc;

import br.cefetmg.inf.hosten.model.domain.Hospede;
import br.cefetmg.inf.util.bd.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IHospedeDao;

public final class HospedeDao implements IHospedeDao {

    private static Connection con;
    private static HospedeDao instancia;

    private HospedeDao() {
        super();
        con = new ConnectionFactory().getConnection();
    }

    public static synchronized HospedeDao getInstance() {
        if (instancia == null) {
            instancia = new HospedeDao();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(Hospede hospede) throws SQLException {

        String qry
                = "INSERT INTO Hospede"
                + "(codCPF, nomHospede, desTelefone, desEmail)"
                + " VALUES (?,?,?,?)";

        PreparedStatement pStmt = con.prepareStatement(qry);

        pStmt.setString(1, hospede.getCodCpf());
        pStmt.setString(2, hospede.getNomHospede());
        pStmt.setString(3, hospede.getDesTelefone());
        pStmt.setString(4, hospede.getDesEmail());

        return pStmt.executeUpdate() > 0;
    }

    @Override
    public Hospede buscaPorPk(String id) throws SQLException {

        String qry
                = "SELECT * FROM Hospede "
                + "WHERE codCpf LIKE ?";

        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setString(1, id);
        ResultSet rs = pStmt.executeQuery();

        Hospede h = new Hospede(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4));
        return h;
    }

    @Override
    public List<Hospede> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException {

        String qry
                = "SELECT * FROM Hospede "
                + "WHERE " + coluna + " "
                + "LIKE ?";

        PreparedStatement pStmt = con.prepareStatement(qry);

        switch (coluna.toLowerCase()) {

            case "codcpf":
                pStmt.setString(1, dadoBusca.toString());
                break;

            case "nomhospede":
                pStmt.setString(1, dadoBusca.toString());
                break;

            case "destelefone":
                pStmt.setString(1, dadoBusca.toString());
                break;

            case "desemail":
                pStmt.setString(1, dadoBusca.toString());
                break;
        }
        ResultSet rs = pStmt.executeQuery();

        List<Hospede> hospedesEncontrados = new ArrayList<>();

        while (rs.next()) {
            hospedesEncontrados.add(new Hospede(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)));
        }

        return hospedesEncontrados;
    }

    @Override
    public List<Hospede> buscaTodos() throws SQLException {

        String qry
                = "SELECT * FROM Hospede";

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(qry);

        List<Hospede> hospedesEncontrados = new ArrayList<>();

        while (rs.next()) {
            hospedesEncontrados.add(new Hospede(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)));
        }

        return hospedesEncontrados;
    }

    @Override
    public boolean atualiza(String pK, Hospede hospedeAtualizado) throws SQLException {

        String qry
                = "UPDATE Hospede "
                + "SET codCPF = ?, nomHospede = ?, desTelefone = ?, desEmail = ? "
                + "WHERE codCPF = ?";

        PreparedStatement pStmt = con.prepareStatement(qry);

        pStmt.setString(1, hospedeAtualizado.getCodCpf());
        pStmt.setString(2, hospedeAtualizado.getNomHospede());
        pStmt.setString(3, hospedeAtualizado.getDesTelefone());
        pStmt.setString(4, hospedeAtualizado.getDesEmail());
        pStmt.setString(5, pK);

        return pStmt.executeUpdate() > 0;
    }

    @Override
    public boolean deleta(String pK) throws SQLException {

        String qry
                = "DELETE FROM Hospede "
                + "WHERE codCPF = ?";

        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setString(1, pK);

        return pStmt.executeUpdate() > 0;
    }
}
