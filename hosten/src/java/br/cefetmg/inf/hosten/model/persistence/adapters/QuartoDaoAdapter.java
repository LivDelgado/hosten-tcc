package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.domain.Quarto;
import br.cefetmg.inf.hosten.model.persistence.jdbc.QuartoDao;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IQuartoDao;

public class QuartoDaoAdapter implements IQuartoDao {

    private static IQuartoDao instancia;

    public static synchronized IQuartoDao getInstance() {
        if (instancia == null) {
            instancia = QuartoDao.getInstance();
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
