package br.cefetmg.inf.hosten.dist.proxy;

import br.cefetmg.inf.hosten.dist.ClienteRMI;
import br.cefetmg.inf.hosten.model.domain.ServicoArea;
import br.cefetmg.inf.hosten.model.service.IManterServicoArea;
import br.cefetmg.inf.hosten.model.service.remote.IManterServicoAreaRemote;
import br.cefetmg.inf.util.exception.NegocioException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManterServicoAreaProxy implements IManterServicoArea {
    private final IManterServicoAreaRemote manterServicoArea;

    public ManterServicoAreaProxy() {
        this.manterServicoArea = (IManterServicoAreaRemote) ClienteRMI.getInstance().receberObjeto("ManterServicoArea");
    }

    @Override
    public boolean inserir(ServicoArea servicoArea) throws NegocioException, SQLException {
        try {
            return manterServicoArea.inserir(servicoArea);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterServicoAreaProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<ServicoArea> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException {
        try {
            return manterServicoArea.listar(dadoBusca, coluna);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterServicoAreaProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<ServicoArea> listarTodos() throws NegocioException, SQLException {
        try {
            return manterServicoArea.listarTodos();
        } catch (RemoteException ex) {
            Logger.getLogger(ManterServicoAreaProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean alterar(String codRegistro, ServicoArea servicoArea) throws NegocioException, SQLException {
        try {
            return manterServicoArea.alterar(codRegistro, servicoArea);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterServicoAreaProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean excluir(String codRegistro) throws NegocioException, SQLException {
        try {
            return manterServicoArea.excluir(codRegistro);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterServicoAreaProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
