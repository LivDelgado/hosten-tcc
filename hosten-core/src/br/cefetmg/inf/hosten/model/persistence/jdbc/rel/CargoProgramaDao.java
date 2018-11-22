package br.cefetmg.inf.hosten.model.persistence.jdbc.rel;

import br.cefetmg.inf.hosten.model.domain.rel.CargoPrograma;
import br.cefetmg.inf.util.bd.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.cefetmg.inf.hosten.model.domain.Programa;
import br.cefetmg.inf.hosten.model.persistence.interfaces.rel.ICargoProgramaDao;

public class CargoProgramaDao implements ICargoProgramaDao {

    private static CargoProgramaDao instancia;
    private static Connection con;

    private CargoProgramaDao() {
        con = new ConnectionFactory().getConnection();
    }

    public static synchronized CargoProgramaDao getInstance() {
        if (instancia == null) {
            instancia = new CargoProgramaDao();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(CargoPrograma cargoPrograma) throws SQLException {

        String qry
                = "INSERT INTO CargoPrograma"
                + "(codPrograma, codCargo) "
                + "VALUES(?,?)";

        PreparedStatement pStmt = con.prepareStatement(qry);

        pStmt.setString(1, cargoPrograma.getCodPrograma());
        pStmt.setString(2, cargoPrograma.getCodCargo());

        return pStmt.executeUpdate() > 0;
    }

    @Override
    public List<CargoPrograma> buscaPorColuna(String cod, String coluna)throws SQLException {
        
        String qry
                = "SELECT * FROM CargoPrograma "
                + "WHERE " + coluna + " = ?";

        PreparedStatement pStmt = con.prepareStatement(qry);

        switch (coluna.toLowerCase()) {

            case "codprograma":
                pStmt.setString(1, cod);
                break;

            case "codcargo":
                pStmt.setString(1, cod);
                break;
        }
        ResultSet rs = pStmt.executeQuery();

        List<CargoPrograma> cargoProgramaEncontrados = new ArrayList<>();

        while (rs.next()) {
            cargoProgramaEncontrados.add(new CargoPrograma(
                    rs.getString(1),
                    rs.getString(2)));
        }

        return cargoProgramaEncontrados;
    }

    @Override
    public List<Programa> buscaProgramasRelacionados(String codCargo) throws SQLException {

        String qry
                = "SELECT B.codPrograma, B.desPrograma "
                + "FROM CargoPrograma A "
                + "JOIN Programa B "
                + "ON A.codPrograma = B.codPrograma "
                + "WHERE A.codCargo = ?";

        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setString(1, codCargo);
        ResultSet rs = pStmt.executeQuery();

        List<Programa> programasEncontrados = new ArrayList<>();

        while (rs.next()) {
            programasEncontrados.add(new Programa(
                    rs.getString(1),
                    rs.getString(2)));
        }

        return programasEncontrados;
    }

    @Override
    public boolean deletaPorColuna(String cod, String coluna) throws SQLException {

        String qry
                = "DELETE FROM CargoPrograma "
                + "WHERE " + coluna + " = ?";

        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setString(1, cod);

        return pStmt.executeUpdate() > 0;
    }
}
