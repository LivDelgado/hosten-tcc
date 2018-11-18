package br.cefetmg.inf.hosten.model.persistence.jdbc.rel;

import br.cefetmg.inf.hosten.model.domain.rel.QuartoEstado;
import br.cefetmg.inf.hosten.model.persistence.interfaces.rel.IQuartoEstadoDao;
import br.cefetmg.inf.util.bd.ConnectionFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuartoEstadoDao implements IQuartoEstadoDao {

    private static Connection con;
    private static QuartoEstadoDao instancia;

    private QuartoEstadoDao() {
        super();
        con = new ConnectionFactory().getConnection();
    }

    public static synchronized QuartoEstadoDao getInstance() {
        if (instancia == null) {
            instancia = new QuartoEstadoDao();
        }
        return instancia;
    }

    @Override
    public List<QuartoEstado> buscaTodos() throws SQLException {

        String qry
                = "SELECT * FROM QuartoEstado";

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(qry);

        List<QuartoEstado> quartoEstadoEncontrados = new ArrayList<>();

        while (rs.next()) {
            quartoEstadoEncontrados.add(new QuartoEstado(
                    rs.getLong(1),
                    rs.getInt(2),
                    rs.getShort(3),
                    rs.getShort(4),
                    rs.getShort(5),
                    rs.getBigDecimal(6),
                    rs.getBoolean(7),
                    rs.getDate(8)));
        }
        
        return quartoEstadoEncontrados;
    }
}
