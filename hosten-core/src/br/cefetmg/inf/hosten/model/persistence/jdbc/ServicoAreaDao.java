package br.cefetmg.inf.hosten.model.persistence.jdbc;

import br.cefetmg.inf.hosten.model.domain.ServicoArea;
import br.cefetmg.inf.util.bd.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IServicoAreaDao;

public class ServicoAreaDao implements IServicoAreaDao {

    private static Connection con;
    private static ServicoAreaDao instancia;

    private ServicoAreaDao() {
        super();
        con = new ConnectionFactory().getConnection();
    }

    public static synchronized ServicoAreaDao getInstance() {
        if (instancia == null) {
            instancia = new ServicoAreaDao();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(ServicoArea servicoArea)
            throws SQLException {
        String qry = "INSERT INTO ServicoArea"
                + "(codServicoArea, nomServicoArea)"
                + " VALUES (?,?)";

        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setString(1, servicoArea.getCodServicoArea());
        pStmt.setString(2, servicoArea.getNomServicoArea());

        return pStmt.executeUpdate() > 0;
    }
    
    @Override
    public ServicoArea buscaPorPk(String id) throws SQLException {
        String qry = "SELECT * FROM ServicoArea "
                + "WHERE codServicoArea "
                + "LIKE ?";
        PreparedStatement pStmt = con.prepareStatement(qry);
        
        pStmt.setString(1, id);
        
        ResultSet rs = pStmt.executeQuery();
    
        ServicoArea sa = new ServicoArea(
                            rs.getString(1),
                            rs.getString(2));
        

        return sa;
    }

    @Override
    public List<ServicoArea> buscaPorColuna(Object dadoBusca, String coluna)
            throws SQLException {
        String qry = "SELECT * FROM ServicoArea "
                + "WHERE " + coluna + " "
                + "LIKE ?";
        PreparedStatement pStmt = con.prepareStatement(qry);

        if (dadoBusca instanceof String) {
            pStmt.setString(1, dadoBusca.toString());
        } else {
            pStmt.setInt(1, Integer.parseInt(dadoBusca.toString()));
        }

        ResultSet rs = pStmt.executeQuery();

        List<ServicoArea> servicoAreaEncontrados = new ArrayList<>();

        while (rs.next()) {
            servicoAreaEncontrados
                    .add(new ServicoArea(
                            rs.getString(1),
                            rs.getString(2)));
        }

        return servicoAreaEncontrados;
    }

    @Override
    public List<ServicoArea> buscaTodos() throws SQLException {
        
        String qry = "SELECT * FROM ServicoArea";
        
        Statement stmt = con.createStatement();
        
        ResultSet rs = stmt.executeQuery(qry);

        List<ServicoArea> servicoAreasEncontrados = new ArrayList<>();

        while (rs.next()) {
            servicoAreasEncontrados
                    .add(new ServicoArea(
                            rs.getString(1),
                            rs.getString(2)));
        }

        return servicoAreasEncontrados;
    }

    @Override
    public boolean atualiza(String pK, ServicoArea servicoAreaAtualizado) throws SQLException {
        
        String qry = "UPDATE ServicoArea "
                + "SET codServicoArea = ?, nomServicoArea = ? "
                + "WHERE codServicoArea = ?";
        PreparedStatement pStmt = con.prepareStatement(qry);
        
        pStmt.setString(1, servicoAreaAtualizado.getCodServicoArea());
        pStmt.setString(2, servicoAreaAtualizado.getNomServicoArea());
        pStmt.setString(3, pK.toString());

        return pStmt.executeUpdate() > 0;
    }

    @Override
    public boolean deleta(String pK) throws SQLException {
        String qry = "DELETE FROM ServicoArea "
                + "WHERE codServicoArea = ?";
        PreparedStatement pStmt = con.prepareStatement(qry);
        if (pK instanceof String) {
            pStmt.setString(1, pK.toString());
        } else {
            pStmt.setInt(1, Integer.parseInt(pK.toString()));
        }

        return pStmt.executeUpdate() > 0;
    }
}
