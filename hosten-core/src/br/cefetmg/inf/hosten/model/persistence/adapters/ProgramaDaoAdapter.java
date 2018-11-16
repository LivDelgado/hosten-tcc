package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.domain.Programa;
import br.cefetmg.inf.hosten.model.persistence.jdbc.ProgramaDao;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IProgramaDao;

public final class ProgramaDaoAdapter implements IProgramaDao {

    private static IProgramaDao instancia;

    public static synchronized IProgramaDao getInstance() {
        if (instancia == null) {
            instancia = ProgramaDao.getInstance();
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
