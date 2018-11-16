package br.cefetmg.inf.hosten.dist.proxy;

import br.cefetmg.inf.hosten.model.domain.Servico;
import br.cefetmg.inf.hosten.model.service.IManterServico;
import br.cefetmg.inf.hosten.model.service.impl.ManterServico;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.List;

public class ManterServicoProxy implements IManterServico {
    private final IManterServico manterServico;

    public ManterServicoProxy() {
        this.manterServico = new ManterServico();
    }
    
    @Override
    public boolean inserir(Servico servico) throws NegocioException, SQLException {
        return manterServico.inserir(servico);
    }

    @Override
    public List<Servico> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException {
        return manterServico.listar(dadoBusca, coluna);
    }

    @Override
    public List<Servico> listarTodos() throws NegocioException, SQLException {
        return manterServico.listarTodos();
    }

    @Override
    public boolean alterar(String codRegistro, Servico servico) throws NegocioException, SQLException {
        return manterServico.alterar(codRegistro, servico);
    }

    @Override
    public boolean excluir(String codRegistro) throws NegocioException, SQLException {
        return manterServico.excluir(codRegistro);
    }
}
