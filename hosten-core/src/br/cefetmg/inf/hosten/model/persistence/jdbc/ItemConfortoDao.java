package br.cefetmg.inf.hosten.model.persistence.jdbc;

import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import br.cefetmg.inf.util.bd.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IItemConfortoDao;

public final class ItemConfortoDao implements IItemConfortoDao {

    private static Connection con;
    private static ItemConfortoDao instancia;

    private ItemConfortoDao() {
        super();
        con = new ConnectionFactory().getConnection();
    }

    public static synchronized ItemConfortoDao getInstance() {
        if (instancia == null) {
            instancia = new ItemConfortoDao();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(ItemConforto itemConforto) throws SQLException {

        String qry
                = "INSERT INTO ItemConforto"
                + "(codItem, desItem) "
                + "VALUES (?,?)";

        PreparedStatement pStmt = con.prepareStatement(qry);

        pStmt.setString(1, itemConforto.getCodItem());
        pStmt.setString(2, itemConforto.getDesItem());

        return pStmt.executeUpdate() > 0;
    }

    @Override
    public ItemConforto buscaPorPk(String id) throws SQLException {

        String qry
                = "SELECT * FROM ItemConforto "
                + "WHERE codItem LIKE ?";

        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setString(1, id);
        ResultSet rs = pStmt.executeQuery();

        ItemConforto ic = new ItemConforto(rs.getString(1), rs.getString(2));

        return ic;
    }

    @Override
    public List<ItemConforto> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException {

        String qry
                = "SELECT * FROM ItemConforto "
                + "WHERE " + coluna + " "
                + "LIKE ?";

        PreparedStatement pStmt = con.prepareStatement(qry);

        switch (coluna.toLowerCase()) {

            case "coditem":
                if (dadoBusca instanceof ItemConforto) {
                    pStmt.setString(1, ((ItemConforto) dadoBusca).getCodItem());
                } else {
                    pStmt.setString(1, dadoBusca.toString());
                }
                break;

            case "desitem":
                pStmt.setString(1, dadoBusca.toString());
                break;
        }
        ResultSet rs = pStmt.executeQuery();

        List<ItemConforto> itemConfortoEncontrados = new ArrayList<>();

        while (rs.next()) {
            itemConfortoEncontrados.add(new ItemConforto(rs.getString(1), rs.getString(2)));
        }

        return itemConfortoEncontrados;
    }

    @Override
    public List<ItemConforto> buscaTodos() throws SQLException {

        String qry
                = "SELECT * FROM ItemConforto";

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(qry);

        List<ItemConforto> itemConfortosEncontrados = new ArrayList<>();

        while (rs.next()) {
            itemConfortosEncontrados.add(new ItemConforto(rs.getString(1), rs.getString(2)));
        }

        return itemConfortosEncontrados;
    }

    @Override
    public boolean atualiza(String pK, ItemConforto itemConfortoAtualizado) throws SQLException {

        String qry
                = "UPDATE ItemConforto "
                + "SET codItem = ?, desItem = ?"
                + "WHERE codItem = ?";

        PreparedStatement pStmt = con.prepareStatement(qry);

        pStmt.setString(1, itemConfortoAtualizado.getCodItem());
        pStmt.setString(2, itemConfortoAtualizado.getDesItem());
        pStmt.setString(3, pK);

        return pStmt.executeUpdate() > 0;
    }

    @Override
    public boolean deleta(String pK) throws SQLException {
        
        String qry 
                = "DELETE FROM ItemConforto "
                + "WHERE codItem = ?";
        
        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setString(1, pK);

        return pStmt.executeUpdate() > 0;
    }
}
