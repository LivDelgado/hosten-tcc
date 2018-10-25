package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.persistence.interfaces.IProgramaDAO;
import br.cefetmg.inf.hosten.model.domain.Programa;
import br.cefetmg.inf.hosten.model.persistence.jdbc.ProgramaDAO;
import java.sql.SQLException;
import java.util.List;

public final class ProgramaDAOAdapter implements IProgramaDAO {

    private static IProgramaDAO instancia;

    public static synchronized IProgramaDAO getInstance() {
        if (instancia == null) {
            instancia = ProgramaDAO.getInstance();
        }
        return instancia;
    }

    @Override
    public boolean adicionaPrograma(Programa programa) throws SQLException {
        return instancia.adicionaPrograma(programa);
    }

    @Override
    public List<Programa> buscaPrograma(
            Object dadoBusca, 
            String coluna) throws SQLException {
        return instancia.buscaPrograma(dadoBusca, coluna);
    }

    @Override
    public List<Programa> buscaTodosProgramas() throws SQLException {
        return instancia.buscaTodosProgramas();
    }

    @Override
    public boolean atualizaPrograma(
            Object pK, 
            Programa programaAtualizado) throws SQLException {
        return instancia.atualizaPrograma(pK, programaAtualizado);
    }

    @Override
    public boolean deletaPrograma(Object pK) throws SQLException {
        return instancia.deletaPrograma(pK);
    }
}
