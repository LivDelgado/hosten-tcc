package br.cefetmg.inf.hosten.model.persistence.jdbc.rel;

import br.cefetmg.inf.hosten.model.domain.rel.CategoriaItemConforto;
import br.cefetmg.inf.util.bd.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import br.cefetmg.inf.hosten.model.persistence.interfaces.rel.ICategoriaItemConfortoDao;

public class CategoriaItemConfortoDao implements ICategoriaItemConfortoDao {

    private static CategoriaItemConfortoDao instancia;
    private static Connection con;

    private CategoriaItemConfortoDao() {
        con = new ConnectionFactory().getConnection();
    }

    public static synchronized CategoriaItemConfortoDao getInstance() {
        if (instancia == null) {
            instancia = new CategoriaItemConfortoDao();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(CategoriaItemConforto categoriaItemConforto) throws SQLException {

        String qry
                = "INSERT INTO CategoriaItemConforto"
                + "(codCategoria, codItem) "
                + "VALUES(?,?)";

        PreparedStatement pStmt = con.prepareStatement(qry);

        pStmt.setString(1, categoriaItemConforto.getCodCategoria());
        pStmt.setString(2, categoriaItemConforto.getCodItem());

        return pStmt.executeUpdate() > 0;
    }

    @Override
    public List<CategoriaItemConforto> busca(String cod, String coluna) throws SQLException {

        String qry
                = "SELECT * FROM CategoriaItemConforto "
                + "WHERE " + coluna + " = ?";

        PreparedStatement pStmt = con.prepareStatement(qry);

        switch (coluna.toLowerCase()) {

            case "codcategoria":
                pStmt.setString(1, cod);
                break;

            case "coditem":
                pStmt.setString(1, cod);
                break;
        }

        ResultSet rs = pStmt.executeQuery();

        List<CategoriaItemConforto> ctgIcEncontrados = new ArrayList<>();

        while (rs.next()) {
            ctgIcEncontrados.add(new CategoriaItemConforto(
                    rs.getString(1),
                    rs.getString(2)));
        }

        return ctgIcEncontrados;
    }

    @Override
    public List<ItemConforto> buscaItensConfortoRelacionados(String codCategoria) throws SQLException {

        String qry
                = "SELECT B.codItem, B.desItem "
                + "FROM CategoriaItemConforto A "
                + "JOIN ItemConforto B "
                + "ON A.codItem = B.codItem "
                + "WHERE codCategoria = ?";

        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setString(1, codCategoria);
        ResultSet rs = pStmt.executeQuery();

        List<ItemConforto> categoriaItemEncontrados = new ArrayList<>();

        while (rs.next()) {
            categoriaItemEncontrados.add(new ItemConforto(
                    rs.getString(1),
                    rs.getString(2)));
        }
        return categoriaItemEncontrados;
    }

    @Override
    public boolean deletaPorColuna(String dadoBusca, String coluna) throws SQLException {

        String qry
                = "DELETE FROM CategoriaItemConforto "
                + "WHERE " + coluna + " = ?";

        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setString(1, dadoBusca);

        return pStmt.executeUpdate() > 0;
    }
}
