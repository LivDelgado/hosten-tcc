package br.cefetmg.inf.hosten.dist.proxy;

import br.cefetmg.inf.hosten.dist.ClienteRMI;
import br.cefetmg.inf.hosten.model.domain.Servico;
import br.cefetmg.inf.hosten.model.service.IManterServico;
import br.cefetmg.inf.hosten.model.service.remote.IManterServicoRemote;
import br.cefetmg.inf.util.exception.NegocioException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManterServicoProxy implements IManterServico {
    private final IManterServicoRemote manterServico;

    public ManterServicoProxy() {
        this.manterServico = (IManterServicoRemote) ClienteRMI.getInstance().receberObjeto("ManterServico");
    }
    
    @Override
    public boolean inserir(Servico servico) throws NegocioException, SQLException {
        try {
            return manterServico.inserir(servico);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterServicoProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Servico> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException {
        try {
            return manterServico.listar(dadoBusca, coluna);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterServicoProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Servico> listarTodos() throws NegocioException, SQLException {
        try {
            return manterServico.listarTodos();
        } catch (RemoteException ex) {
            Logger.getLogger(ManterServicoProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean alterar(String codRegistro, Servico servico) throws NegocioException, SQLException {
        try {
            return manterServico.alterar(codRegistro, servico);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterServicoProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean excluir(String codRegistro) throws NegocioException, SQLException {
        try {
            return manterServico.excluir(codRegistro);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterServicoProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
