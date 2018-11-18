package br.cefetmg.inf.hosten.model.persistence.jdbc;

import br.cefetmg.inf.hosten.model.domain.Programa;
import br.cefetmg.inf.hosten.model.domain.Quarto;
import br.cefetmg.inf.util.bd.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IProgramaDao;

public final class ProgramaDao implements IProgramaDao {

    private static Connection con;
    private static ProgramaDao instancia;

    private ProgramaDao() {
        super();
        con = new ConnectionFactory().getConnection();
    }

    public static synchronized ProgramaDao getInstance() {
        if (instancia == null) {
            instancia = new ProgramaDao();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(Programa programa) throws SQLException {

        String qry
                = "INSERT INTO Programa"
                + "(codPrograma, desPrograma) "
                + "VALUES (?,?)";

        PreparedStatement pStmt = con.prepareStatement(qry);

        pStmt.setString(1, programa.getCodPrograma());
        pStmt.setString(2, programa.getDesPrograma());

        return pStmt.executeUpdate() > 0;
    }

    @Override
    public Programa buscaPorPk(String id) throws SQLException {

        String qry
                = "SELECT * FROM Programa "
                + "WHERE codPrograma LIKE ?";

        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setString(1, id);
        ResultSet rs = pStmt.executeQuery();

        Programa prog = new Programa(rs.getString(1), rs.getString(2));

        return prog;
    }

    @Override
    public List<Programa> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException {

        String qry
                = "SELECT * FROM Programa "
                + "WHERE " + coluna + " "
                + "LIKE ?";

        PreparedStatement pStmt = con.prepareStatement(qry);

        switch (coluna.toLowerCase()) {
            
            case "codprograma":
                if (dadoBusca instanceof Programa) {
                    pStmt.setString(1, ((Programa) dadoBusca).getCodPrograma());
                } else {
                    pStmt.setString(1, dadoBusca.toString());
                }
                break;
                
            case "desprograma":
                pStmt.setString(1, dadoBusca.toString());
                break;
        }
        ResultSet rs = pStmt.executeQuery();

        List<Programa> programasEncontrados = new ArrayList<>();

        while (rs.next()) {
            programasEncontrados.add(new Programa(rs.getString(1), rs.getString(2)));
        }

        return programasEncontrados;
    }

    @Override
    public List<Programa> buscaTodos() throws SQLException {

        String qry
                = "SELECT * FROM Programa";

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(qry);

        List<Programa> programasEncontrados = new ArrayList<>();

        while (rs.next()) {
            programasEncontrados.add(new Programa(rs.getString(1), rs.getString(2)));
        }

        return programasEncontrados;
    }

    @Override
    public boolean atualiza(String id, Programa programaAtualizado) throws SQLException {
        
        String qry
                = "UPDATE Programa "
                + "SET codPrograma = ?, desPrograma = ? "
                + "WHERE codPrograma LIKE ?";
        
        PreparedStatement pStmt = con.prepareStatement(qry);

        pStmt.setString(1, programaAtualizado.getCodPrograma());
        pStmt.setString(2, programaAtualizado.getDesPrograma());
        pStmt.setString(3, id);

        return pStmt.executeUpdate() > 0;
    }

    @Override
    public boolean deleta(String id) throws SQLException {
        
        String qry
                = "DELETE FROM Programa "
                + "WHERE codPrograma LIKE ?";
        
        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setString(1, id);
        
        return pStmt.executeUpdate() > 0;
    }
}
