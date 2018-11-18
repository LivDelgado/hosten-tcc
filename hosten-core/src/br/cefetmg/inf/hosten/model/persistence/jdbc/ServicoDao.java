package br.cefetmg.inf.hosten.model.persistence.jdbc;

import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.hosten.model.domain.Servico;
import br.cefetmg.inf.hosten.model.domain.ServicoArea;
import br.cefetmg.inf.util.bd.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IServicoDao;
import java.math.BigDecimal;

public class ServicoDao implements IServicoDao {

    private final Connection con;
    private static ServicoDao instancia;

    private ServicoDao() {
        super();
        con = new ConnectionFactory().getConnection();
    }

    public static synchronized ServicoDao getInstance() {
        if (instancia == null) {
            instancia = new ServicoDao();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(Servico servico) throws SQLException {

        String qry
                = "INSERT INTO Servico"
                + "(desServico, vlrUnit, codServicoArea)"
                + " VALUES (?,?,?)";

        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setString(1, servico.getDesServico());
        pStmt.setDouble(2, servico.getVlrUnit().doubleValue());
        pStmt.setString(3, servico.getServicoArea().getCodServicoArea());

        return pStmt.executeUpdate() > 0;
    }

    @Override
    public Servico buscaPorPk(short id) throws SQLException {

        String qry
                = "SELECT * FROM Servico "
                + "WHERE seqServico = ?";

        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setShort(1, id);
        ResultSet rs = pStmt.executeQuery();

        Servico s = new Servico(
                rs.getShort(1),
                rs.getString(2),
                BigDecimal.valueOf(rs.getDouble(3)));
        s.setServicoArea(new ServicoArea(rs.getString(4)));

        return s;
    }

    @Override
    public List<Servico> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException {

        String qry
                = "SELECT * FROM Servico "
                + "WHERE " + coluna + " = ?";

        PreparedStatement pStmt = con.prepareStatement(qry);

        switch (coluna.toLowerCase()) {
            case "seqservico":
                if (dadoBusca instanceof Servico) {
                    pStmt.setShort(1, ((Servico) dadoBusca).getSeqServico());
                } else {
                    pStmt.setShort(1, (short) dadoBusca);
                }
                break;
            case "desservico":
                pStmt.setString(1, dadoBusca.toString());
                break;
            case "vlrunit":
                pStmt.setBigDecimal(
                        1,
                        BigDecimal.valueOf(
                                Double.parseDouble(
                                        dadoBusca.toString())));
                break;
            case "codservicoarea":
                if (dadoBusca instanceof ServicoArea) {
                    pStmt.setString(1, ((ServicoArea) dadoBusca).getCodServicoArea());
                } else {
                    pStmt.setString(1, dadoBusca.toString());
                }
                break;
        }
        ResultSet rs = pStmt.executeQuery();

        List<Servico> servicoEncontrados = new ArrayList<>();

        while (rs.next()) {
            Servico s = new Servico(
                    rs.getShort(1),
                    rs.getString(2),
                    BigDecimal.valueOf(rs.getDouble(3)));
            s.setServicoArea(new ServicoArea(rs.getString(4)));

            servicoEncontrados.add(s);
        }

        return servicoEncontrados;
    }

    @Override
    public List<Servico> buscaTodos() throws SQLException {
        Statement stmt = con.createStatement();

        String qry = "SELECT * FROM Servico";
        ResultSet rs = stmt.executeQuery(qry);

        List<Servico> servicosEncontrados = new ArrayList<>();

        while (rs.next()) {
            Servico s = new Servico(
                    rs.getShort(1),
                    rs.getString(2),
                    BigDecimal.valueOf(rs.getDouble(3)));
            s.setServicoArea(new ServicoArea(rs.getString(4)));

            servicosEncontrados.add(s);
        }

        return servicosEncontrados;
    }

    @Override
    public boolean atualiza(short pK, Servico servicoAtualizado)
            throws SQLException {
        String qry = "UPDATE Servico "
                + "SET desServico = ?, vlrUnit = ?, codServicoArea = ? "
                + "WHERE seqServico = ?";
        PreparedStatement pStmt = con.prepareStatement(qry);
        pStmt.setString(1, servicoAtualizado.getDesServico());
        pStmt.setBigDecimal(2, servicoAtualizado.getVlrUnit());
        pStmt.setString(3, servicoAtualizado.getServicoArea().getCodServicoArea());
        pStmt.setShort(4, pK);

        return pStmt.executeUpdate() > 0;
    }

    @Override
    public boolean deleta(short pK) throws SQLException {
        String qry = "DELETE FROM Servico "
                + "WHERE seqServico = ?";
        PreparedStatement pStmt = con.prepareStatement(qry);

        pStmt.setShort(1, pK);

        return pStmt.executeUpdate() > 0;
    }
}
