package br.cefetmg.inf.hosten.dist.proxy;

import br.cefetmg.inf.hosten.model.domain.Quarto;
import br.cefetmg.inf.hosten.model.service.IManterQuarto;
import br.cefetmg.inf.hosten.model.service.impl.ManterQuarto;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.List;


public class ManterQuartoProxy implements IManterQuarto {
    private final IManterQuarto manterQuarto;

    public ManterQuartoProxy() {
        this.manterQuarto = new ManterQuarto();
    }

    @Override
    public boolean inserir(Quarto quarto) throws NegocioException, SQLException {
        return manterQuarto.inserir(quarto);
    }

    @Override
    public List<Quarto> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException {
        return manterQuarto.listar(dadoBusca, coluna);
    }

    @Override
    public List<Quarto> listarTodos() throws NegocioException, SQLException {
        return manterQuarto.listarTodos();
    }

    @Override
    public boolean alterar(String codRegistro, Quarto quarto) throws NegocioException, SQLException {
        return manterQuarto.alterar(codRegistro, quarto);
    }

    @Override
    public boolean excluir(String codRegistro) throws NegocioException, SQLException {
        return manterQuarto.excluir(codRegistro);
    }

    @Override
    public int buscaUltimoRegistroRelacionadoAoQuarto(int nroQuarto) throws NegocioException, SQLException {
        return manterQuarto.buscaUltimoRegistroRelacionadoAoQuarto(nroQuarto);
    }
    
}
