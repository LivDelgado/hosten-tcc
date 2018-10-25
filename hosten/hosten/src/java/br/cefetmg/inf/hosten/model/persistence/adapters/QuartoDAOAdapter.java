package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.persistence.interfaces.IQuartoDAO;
import br.cefetmg.inf.hosten.model.domain.Quarto;
import br.cefetmg.inf.hosten.model.persistence.jdbc.QuartoDAO;
import java.sql.SQLException;
import java.util.List;

public class QuartoDAOAdapter implements IQuartoDAO {

    private static IQuartoDAO instancia;

    public static synchronized IQuartoDAO getInstance() {
        if (instancia == null) {
            instancia = QuartoDAO.getInstance();
        }
        return instancia;
    }

    @Override
    public boolean adicionaQuarto(Quarto quarto) throws SQLException {
        return instancia.adicionaQuarto(quarto);
    }

    @Override
    public List<Quarto> buscaQuarto(
            Object dadoBusca,
            String coluna) throws SQLException {
        return instancia.buscaQuarto(dadoBusca, coluna);
    }

    @Override
    public List<Quarto> buscaTodosQuartos() throws SQLException {
        return instancia.buscaTodosQuartos();
    }

    @Override
    public boolean atualizaQuarto(Object pK, Quarto quartoAtualizado) 
            throws SQLException {
        return instancia.atualizaQuarto(pK, quartoAtualizado);
    }

    @Override
    public boolean deletaQuarto(Object pK) throws SQLException {
        return instancia.deletaQuarto(pK);
    }
    
    @Override
    public int buscaUltimoRegistroRelacionadoAoQuarto(int nroQuarto)
            throws SQLException {
        return instancia.buscaUltimoRegistroRelacionadoAoQuarto(nroQuarto);
    }
}
