package br.cefetmg.inf.hosten.model.persistence.jdbc;

import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.util.bd.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.ICargoDao;

public class CargoDao implements ICargoDao {

    private static CargoDao instancia;
    private static Connection con;

    public CargoDao() {
        super();
        con = new ConnectionFactory().getConnection();
    }

    public static synchronized CargoDao getInstance() {
        if (instancia == null) {
            instancia = new CargoDao();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(Cargo cargo) throws SQLException {

        String qry
                = "INSERT INTO Cargo"
                + "(codCargo, nomCargo, idtMaster)"
                + " VALUES (?,?,?)";

        PreparedStatement pStmt = con.prepareStatement(qry);

        pStmt.setString(1, cargo.getCodCargo());
        pStmt.setString(2, cargo.getNomCargo());
        pStmt.setBoolean(3, cargo.isIdtMaster());

        return pStmt.executeUpdate() > 0;
    }

    @Override
    public Cargo buscaPorPk(String id) throws SQLException {

        String qry
                = "SELECT * FROM Cargo "
                + "WHERE codCargo LIKE ?";

        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setString(1, id);
        ResultSet rs = pStmt.executeQuery();

        Cargo crg = new Cargo(
                rs.getString(1),
                rs.getString(2),
                rs.getBoolean(3));

        return crg;
    }

    @Override
    public List<Cargo> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException {

        String qry
                = "SELECT * FROM Cargo "
                + "WHERE " + coluna + " "
                + "LIKE ?";

        PreparedStatement pStmt = con.prepareStatement(qry);

        switch (coluna.toLowerCase()) {

            case "codcargo":
                if (dadoBusca instanceof Cargo) {
                    pStmt.setString(1, ((Cargo) dadoBusca).getCodCargo());
                } else {
                    pStmt.setString(1, dadoBusca.toString());
                }
                break;

            case "nomcargo":
                pStmt.setString(1, dadoBusca.toString());
                break;

            case "idtmaster":
                pStmt.setBoolean(1, (boolean) dadoBusca);
                break;
        }
        ResultSet rs = pStmt.executeQuery();

        List<Cargo> cargosEncontrados = new ArrayList<>();

        while (rs.next()) {
            cargosEncontrados.add(new Cargo(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getBoolean(3)));
        }

        return cargosEncontrados;
    }

    @Override
    public List<Cargo> buscaTodos() throws SQLException {

        String qry
                = "SELECT * FROM Cargo";

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(qry);

        List<Cargo> cargosEncontrados = new ArrayList<>();

        while (rs.next()) {
            cargosEncontrados.add(new Cargo(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getBoolean(3)));
        }

        return cargosEncontrados;
    }

    @Override
    public boolean atualiza(String pK, Cargo cargoAtualizado) throws SQLException {

        String qry
                = "UPDATE Cargo "
                + "SET codCargo = ?, nomCargo = ?, idtMaster = ? "
                + "WHERE codCargo = ?";

        PreparedStatement pStmt = con.prepareStatement(qry);

        pStmt.setString(1, cargoAtualizado.getCodCargo());
        pStmt.setString(2, cargoAtualizado.getNomCargo());
        pStmt.setBoolean(3, cargoAtualizado.isIdtMaster());
        pStmt.setString(4, pK);

        return pStmt.executeUpdate() > 0;
    }

    @Override
    public boolean deleta(String pK) throws SQLException {

        String qry
                = "DELETE FROM Cargo "
                + "WHERE codCargo = ?";

        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setString(1, pK);

        return pStmt.executeUpdate() > 0;
    }
}
