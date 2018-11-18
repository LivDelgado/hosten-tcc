package br.cefetmg.inf.hosten.model.persistence.jdbc;

import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import br.cefetmg.inf.hosten.model.domain.Quarto;
import br.cefetmg.inf.hosten.model.domain.ServicoArea;
import br.cefetmg.inf.util.bd.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IQuartoDao;

public class QuartoDao implements IQuartoDao {

    private static Connection con;
    private static QuartoDao instancia;

    private QuartoDao() {
        super();
        con = new ConnectionFactory().getConnection();
    }

    public static synchronized QuartoDao getInstance() {
        if (instancia == null) {
            instancia = new QuartoDao();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(Quarto quarto) throws SQLException {

        String qry
                = "INSERT INTO Quarto"
                + "(nroQuarto, codCategoria, idtOcupado) "
                + "VALUES (?,?,?)";

        PreparedStatement pStmt = con.prepareStatement(qry);

        pStmt.setShort(1, quarto.getNroQuarto());
        pStmt.setString(2, quarto.getCategoria().getCodCategoria());
        pStmt.setBoolean(3, quarto.getIdtOcupado());

        return pStmt.executeUpdate() > 0;
    }

    @Override
    public Quarto buscaPorPk(short id) throws SQLException {

        String qry
                = "SELECT * FROM Quarto "
                + "WHERE nroQuarto = ?";

        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setShort(1, id);
        ResultSet rs = pStmt.executeQuery();

        Quarto qrt = new Quarto(rs.getShort(1), rs.getBoolean(3));
        qrt.setCategoria(new CategoriaQuarto(rs.getString(2)));

        return qrt;
    }

    @Override
    public List<Quarto> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException {

        String qry
                = "SELECT * FROM Quarto "
                + "WHERE " + coluna + " = ?";

        PreparedStatement pStmt = con.prepareStatement(qry);

        switch (coluna.toLowerCase()) {
            
            case "nroquarto":
                if (dadoBusca instanceof Quarto) {
                    pStmt.setShort(1, ((Quarto) dadoBusca).getNroQuarto());
                } else {
                    pStmt.setShort(1, (short) dadoBusca);
                }
                break;
                
            case "codcategoria":
                if (dadoBusca instanceof CategoriaQuarto) {
                    pStmt.setString(1, ((CategoriaQuarto) dadoBusca).getCodCategoria());
                } else {
                    pStmt.setString(1, dadoBusca.toString());
                }
                break;
                
            case "idtocupado":
                pStmt.setBoolean(1, (boolean) dadoBusca);
                break;
        }
        ResultSet rs = pStmt.executeQuery();

        List<Quarto> quartoEncontrados = new ArrayList<>();

        while (rs.next()) {
            Quarto qrt = new Quarto(rs.getShort(1), rs.getBoolean(3));
            qrt.setCategoria(new CategoriaQuarto(rs.getString(2)));

            quartoEncontrados.add(qrt);
        }

        return quartoEncontrados;
    }

    @Override
    public List<Quarto> buscaTodos() throws SQLException {

        String qry = "SELECT * FROM Quarto";

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(qry);

        List<Quarto> quartosEncontrados = new ArrayList<>();

        while (rs.next()) {
            Quarto qrt = new Quarto(rs.getShort(1), rs.getBoolean(3));
            qrt.setCategoria(new CategoriaQuarto(rs.getString(2)));

            quartosEncontrados.add(qrt);
        }

        return quartosEncontrados;
    }

    @Override
    public boolean atualiza(short pK, Quarto quartoAtualizado) throws SQLException {

        String qry
                = "UPDATE Quarto "
                + "SET nroQuarto = ?, codCategoria = ?, idtOcupado = ? "
                + "WHERE nroQuarto = ?";

        PreparedStatement pStmt = con.prepareStatement(qry);

        pStmt.setShort(1, quartoAtualizado.getNroQuarto());
        pStmt.setString(2, quartoAtualizado.getCategoria().getCodCategoria());
        pStmt.setBoolean(3, quartoAtualizado.getIdtOcupado());
        pStmt.setShort(4, pK);

        return pStmt.executeUpdate() > 0;
    }

    @Override
    public boolean deleta(short pK) throws SQLException {

        String qry
                = "DELETE FROM Quarto WHERE nroQuarto = ?";
        
        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setShort(1, pK);

        return pStmt.executeUpdate() > 0;
    }
}
