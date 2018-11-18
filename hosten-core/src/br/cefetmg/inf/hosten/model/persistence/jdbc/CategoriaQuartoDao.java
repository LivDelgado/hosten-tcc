package br.cefetmg.inf.hosten.model.persistence.jdbc;

import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import br.cefetmg.inf.hosten.model.domain.Programa;
import br.cefetmg.inf.util.bd.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.ICategoriaQuartoDao;
import java.math.BigDecimal;

public final class CategoriaQuartoDao implements ICategoriaQuartoDao {

    private static Connection con;
    private static CategoriaQuartoDao instancia;

    private CategoriaQuartoDao() {
        super();
        con = new ConnectionFactory().getConnection();
    }

    public static synchronized CategoriaQuartoDao getInstance() {
        if (instancia == null) {
            instancia = new CategoriaQuartoDao();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(CategoriaQuarto categoriaQuarto) throws SQLException {

        String qry
                = "INSERT INTO Categoria"
                + "(codCategoria, nomCategoria, vlrDiaria)"
                + " VALUES (?,?,?)";

        PreparedStatement pStmt = con.prepareStatement(qry);

        pStmt.setString(1, categoriaQuarto.getCodCategoria());
        pStmt.setString(2, categoriaQuarto.getNomCategoria());
        pStmt.setBigDecimal(3, categoriaQuarto.getVlrDiaria());

        return pStmt.executeUpdate() > 0;
    }

    @Override
    public CategoriaQuarto buscaPorPk(String id) throws SQLException {

        String qry
                = "SELECT * FROM Categoria "
                + "WHERE codCategoria LIKE ?";

        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setString(1, id);
        ResultSet rs = pStmt.executeQuery();

        CategoriaQuarto cq = new CategoriaQuarto(
                rs.getString(1),
                rs.getString(2),
                rs.getBigDecimal(3));

        return cq;
    }

    @Override
    public List<CategoriaQuarto> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException {

        String qry
                = "SELECT * FROM Categoria "
                + "WHERE " + coluna + " "
                + "LIKE ?";

        PreparedStatement pStmt = con.prepareStatement(qry);

        switch (coluna.toLowerCase()) {

            case "codcategoria":
                if (dadoBusca instanceof CategoriaQuarto) {
                    pStmt.setString(1, ((CategoriaQuarto) dadoBusca).getCodCategoria());
                } else {
                    pStmt.setString(1, dadoBusca.toString());
                }
                break;

            case "nomcategoria":
                pStmt.setString(1, dadoBusca.toString());
                break;

            case "vlrdiaria":
                pStmt.setBigDecimal(1, (BigDecimal) dadoBusca);
                break;
        }
        ResultSet rs = pStmt.executeQuery();

        List<CategoriaQuarto> categoriaQuartosEncontrados = new ArrayList<>();

        while (rs.next()) {
            categoriaQuartosEncontrados
                    .add(new CategoriaQuarto(
                            rs.getString(1),
                            rs.getString(2),
                            rs.getBigDecimal(3)));
        }

        return categoriaQuartosEncontrados;
    }

    @Override
    public List<CategoriaQuarto> buscaTodos() throws SQLException {

        String qry
                = "SELECT * FROM Categoria";

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(qry);

        List<CategoriaQuarto> categoriaQuartosEncontrados = new ArrayList<>();

        while (rs.next()) {
            categoriaQuartosEncontrados
                    .add(new CategoriaQuarto(
                            rs.getString(1),
                            rs.getString(2),
                            rs.getBigDecimal(3)));
        }

        return categoriaQuartosEncontrados;
    }

    @Override
    public boolean atualiza(String pK, CategoriaQuarto categoriaQuartoAtualizado) throws SQLException {

        String qry
                = "UPDATE Categoria "
                + "SET codCategoria = ?, nomCategoria = ?, vlrDiaria = ? "
                + "WHERE codCategoria = ?";

        PreparedStatement pStmt = con.prepareStatement(qry);

        pStmt.setString(1, categoriaQuartoAtualizado.getCodCategoria());
        pStmt.setString(2, categoriaQuartoAtualizado.getNomCategoria());
        pStmt.setDouble(3, categoriaQuartoAtualizado.getVlrDiaria().doubleValue());
        pStmt.setString(4, pK);

        return pStmt.executeUpdate() > 0;
    }

    @Override
    public boolean deleta(String pK) throws SQLException {

        String qry
                = "DELETE FROM Categoria "
                + "WHERE codCategoria = ?";

        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setString(1, pK);

        return pStmt.executeUpdate() > 0;
    }
}
