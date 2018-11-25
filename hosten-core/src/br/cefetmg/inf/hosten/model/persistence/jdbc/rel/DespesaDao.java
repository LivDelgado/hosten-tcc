package br.cefetmg.inf.hosten.model.persistence.jdbc.rel;

import br.cefetmg.inf.hosten.model.domain.rel.Despesa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.cefetmg.inf.util.bd.ConnectionFactory;
import java.sql.Connection;
import br.cefetmg.inf.hosten.model.persistence.interfaces.rel.IDespesaDao;

public class DespesaDao implements IDespesaDao {

    private final Connection con;
    private static DespesaDao instancia;

    private DespesaDao() {
        con = new ConnectionFactory().getConnection();
    }

    public static synchronized DespesaDao getInstance() {
        if (instancia == null) {
            instancia = new DespesaDao();
        }
        return instancia;
    }

    @Override
    public List<Despesa> busca(int seqHospedagem, short nroQuarto) throws SQLException {

        String qry
                = "SELECT * FROM Despesa "
                + "WHERE seqHospedagem = ? AND nroQuarto = ?";

        PreparedStatement pStmt = con.prepareStatement(qry);

        pStmt.setInt(1, seqHospedagem);
        pStmt.setShort(2, nroQuarto);

        ResultSet rs = pStmt.executeQuery();

        List<Despesa> despesaEncontradas = new ArrayList<>();

        /*
        long id, 
        Integer seqHospedagem, 
        Short nroQuarto, 
        Short nroAdultos, 
        Short nroCriancas, 
        BigDecimal vlrDiaria, 
        Timestamp datCheckin, 
        Timestamp datCheckout, 
        BigDecimal vlrPago, 
        String nomHospede, 
        Short seqServico, 
        Short qtdConsumo, 
        Timestamp datConsumo, 
        String desServico, 
        BigDecimal vlrUnit
         */
        while (rs.next()) {
            despesaEncontradas.add(new Despesa(
                    rs.getLong(1),
                    rs.getInt(2),
                    rs.getShort(3),
                    rs.getShort(4),
                    rs.getShort(5),
                    rs.getBigDecimal(6),
                    rs.getTimestamp(7),
                    rs.getTimestamp(8),
                    rs.getBigDecimal(9),
                    rs.getString(10),
                    rs.getShort(11),
                    rs.getShort(12),
                    rs.getTimestamp(13),
                    rs.getString(14),
                    rs.getBigDecimal(15)));
        }
        return despesaEncontradas;
    }
}
