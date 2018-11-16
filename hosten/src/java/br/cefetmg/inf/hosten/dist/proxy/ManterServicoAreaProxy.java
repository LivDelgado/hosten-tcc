package br.cefetmg.inf.hosten.dist.proxy;

import br.cefetmg.inf.hosten.model.domain.ServicoArea;
import br.cefetmg.inf.hosten.model.service.IManterServicoArea;
import br.cefetmg.inf.hosten.model.service.impl.ManterServicoArea;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.List;

public class ManterServicoAreaProxy implements IManterServicoArea {
    private final IManterServicoArea manterServicoArea;

    public ManterServicoAreaProxy() {
        this.manterServicoArea = new ManterServicoArea();
    }

    @Override
    public boolean inserir(ServicoArea servicoArea) throws NegocioException, SQLException {
        return manterServicoArea.inserir(servicoArea);
    }

    @Override
    public List<ServicoArea> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException {
        return manterServicoArea.listar(dadoBusca, coluna);
    }

    @Override
    public List<ServicoArea> listarTodos() throws NegocioException, SQLException {
        return manterServicoArea.listarTodos();
    }

    @Override
    public boolean alterar(String codRegistro, ServicoArea servicoArea) throws NegocioException, SQLException {
        return manterServicoArea.alterar(codRegistro, servicoArea);
    }

    @Override
    public boolean excluir(String codRegistro) throws NegocioException, SQLException {
        return manterServicoArea.excluir(codRegistro);
    }
}
