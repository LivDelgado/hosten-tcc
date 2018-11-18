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
                = "SELECT \n"
                + "	A.seqHospedagem, \n"
                + "	B.nroQuarto,\n"
                + "	A.nroAdultos,\n"
                + "	A.nroCriancas,\n"
                + "	A.vlrDiaria,\n"
                + "	B.idtOcupado,\n"
                + "	A.datCheckOut\n"
                + "FROM \n"
                + "	Quarto B\n"
                + "	LEFT JOIN (\n"
                + "                SELECT D.seqhospedagem, nroquarto, nroadultos, nrocriancas, vlrdiaria, datcheckin, datcheckout, vlrpago, codcpf\n"
                + "                FROM QuartoHospedagem D\n"
                + "                JOIN Hospedagem E on D.seqhospedagem = E.seqhospedagem\n"
                + "                WHERE datcheckout = NULL OR datcheckout > (SELECT NOW())\n"
                + "        ) A ON A.nroQuarto = B.nroQuarto\n"
                + "ORDER BY nroquarto;";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(qry);

        List<br.cefetmg.inf.hosten.model.domain.rel.QuartoEstado> quartoEstadoEncontrados = new ArrayList<>();

        while (rs.next()) {
            quartoEstadoEncontrados
                    .add(new br.cefetmg.inf.hosten.model.domain.rel.QuartoEstado(
                            rs.getInt(1),
                            rs.getShort(2),
                            rs.getShort(3),
                            rs.getShort(4),
                            rs.getBigDecimal(5),
                            rs.getBoolean(6),
                            rs.getTimestamp(7)));
        }
        return quartoEstadoEncontrados;
    }

}
