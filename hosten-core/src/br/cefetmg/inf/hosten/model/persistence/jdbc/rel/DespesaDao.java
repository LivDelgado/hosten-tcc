package br.cefetmg.inf.hosten.model.persistence.jdbc.rel;

import br.cefetmg.inf.hosten.model.domain.rel.Despesa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.cefetmg.inf.util.bd.ConnectionFactory;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import br.cefetmg.inf.hosten.model.persistence.interfaces.rel.IDespesaDao;

public class DespesaDao implements IDespesaDao {
    
    private final Connection con;
    private static DespesaDao instancia;
    
    private DespesaDao() {
        con = new ConnectionFactory().getConnection();
    }
    
    public static synchronized DespesaDao getInstance() {
        if (instancia == null) {
            instancia  = new DespesaDao();
        }
        return instancia;
    }
    
    @Override
    public List<Despesa> busca(int seqHospedagem, int nroQuarto) throws SQLException {
        String qry = "SELECT * "
                + "FROM Despesa "
                + "WHERE "
                + "seqHospedagem = ? AND "
                + "nroQuarto = ?";

        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setInt(1, seqHospedagem);
        pStmt.setInt(2, nroQuarto);
        ResultSet rs = pStmt.executeQuery();

        List<Despesa> despesaEncontradas = new ArrayList<>();

        /*
        long id, 
        Integer seqHospedagem, 
        Short nroQuarto, 
        Short nroAdultos, 
        Short nroCriancas, 
        BigDecimal vlrDiaria, 
        Date datCheckin, 
        Date datCheckout, 
        BigDecimal vlrPago, 
        String nomHospede, 
        Short seqServico, 
        Short qtdConsumo, 
        String desServico, 
        BigDecimal vlrUnit
        */
        while (rs.next()) {
            despesaEncontradas
                    .add(new Despesa(
                            rs.getInt(1),
                            rs.getInt(1),
                            rs.getShort(2),
                            rs.getShort(3),
                            rs.getShort(4),
                            rs.getBigDecimal(5),
                            rs.getTimestamp(6),
                            rs.getTimestamp(7),
                            rs.getBigDecimal(8),
                            rs.getString(9),
                            rs.getShort(10),
                            rs.getShort(11),
                            rs.getString(12),
                            rs.getBigDecimal(13)));
        }
        return despesaEncontradas;
    }
    
    @Override
    public Map<String, Object> retornaDespesa(int seqHospedagem, int nroQuarto) 
            throws SQLException {
        String qry = "SELECT * "
                + "FROM  Despesa "
                + "WHERE seqHospedagem = ? "
                + "AND nroQuarto = ?";
        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setInt(1, seqHospedagem);
        pStmt.setInt(2, nroQuarto);
        ResultSet rs = pStmt.executeQuery();
        
        Map<String, Object> map = new HashMap<>();
        rs.next();
        map.put("seqHospedagem", rs.getInt("seqHospedagem"));
        map.put("nroQuarto", rs.getInt("nroQuarto"));
        map.put("nroAdultos", rs.getInt("nroAdultos"));
        map.put("nroCriancas", rs.getInt("nroCriancas"));
        map.put("vlrDiaria", rs.getDouble("vlrDiaria"));
        map.put("datCheckIn", rs.getTimestamp("datCheckIn"));
        map.put("datCheckOut", rs.getTimestamp("datCheckOut"));
        map.put("vlrPago", rs.getDouble("vlrPago"));
        map.put("nomHospede", rs.getString("nomHospede"));
        
        ArrayList despesas = new ArrayList();
        while(rs.next()) {            
            Map<String, Object> despesa = new HashMap<>();
            map.put("seqServico", rs.getInt("seqServico"));
            map.put("desServico", rs.getString("desServico"));
            map.put("qtdConsumo", rs.getInt("qtdConsumo"));
            map.put("vlrUnit", rs.getDouble("vlrUnit"));
            
            despesas.add(despesa);
        }
        map.put("arrayDespesas", despesas);
        
        return map;
    }
}
