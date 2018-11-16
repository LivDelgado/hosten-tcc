package br.cefetmg.inf.hosten.model.service.adapter;

import br.cefetmg.inf.hosten.model.domain.ServicoArea;
import br.cefetmg.inf.hosten.model.service.IManterServicoArea;
import br.cefetmg.inf.hosten.model.service.impl.ManterServicoArea;
import br.cefetmg.inf.hosten.model.service.remote.IManterServicoAreaRemote;
import br.cefetmg.inf.util.exception.NegocioException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;


public class ManterServicoAreaAdapter implements IManterServicoAreaRemote {
    private final IManterServicoArea manterServicoArea;

    public ManterServicoAreaAdapter() {
        this.manterServicoArea = new ManterServicoArea();
    }
    
    @Override
    public boolean inserir(ServicoArea servicoArea) throws NegocioException, SQLException, RemoteException {
        return manterServicoArea.inserir(servicoArea);
    }

    @Override
    public List<ServicoArea> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException, RemoteException {
        return manterServicoArea.listar(dadoBusca, coluna);
    }

    @Override
    public List<ServicoArea> listarTodos() throws NegocioException, SQLException, RemoteException {
        return manterServicoArea.listarTodos();
    }

    @Override
    public boolean alterar(String codRegistro, ServicoArea servicoArea) throws NegocioException, SQLException, RemoteException {
        return manterServicoArea.alterar(codRegistro, servicoArea);
    }

    @Override
    public boolean excluir(String codRegistro) throws NegocioException, SQLException, RemoteException {
        return manterServicoArea.excluir(codRegistro);
    }
}