package br.cefetmg.inf.hosten.dist.proxy;

import br.cefetmg.inf.hosten.dist.ClienteRMI;
import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.hosten.model.domain.Programa;
import br.cefetmg.inf.hosten.model.service.IManterCargo;
import br.cefetmg.inf.hosten.model.service.remote.IManterCargoRemote;
import br.cefetmg.inf.util.exception.NegocioException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManterCargoProxy implements IManterCargo {
    private final IManterCargoRemote manterCargo;

    public ManterCargoProxy() {
        this.manterCargo = (IManterCargoRemote) ClienteRMI.getInstance().receberObjeto("ManterCargo");
    }

    @Override
    public boolean inserir(Cargo cargo, List<String> listaProgramas) throws NegocioException, SQLException {
        try {
            return manterCargo.inserir(cargo, listaProgramas);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterCargoProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Cargo> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException {
        try {
            return manterCargo.listar(dadoBusca, coluna);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterCargoProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Cargo> listarTodos() throws NegocioException, SQLException {
        try {
            return manterCargo.listarTodos();
        } catch (RemoteException ex) {
            Logger.getLogger(ManterCargoProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Programa> listarProgramasRelacionados(String codCargo) throws NegocioException, SQLException {
        try {
            return manterCargo.listarProgramasRelacionados(codCargo);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterCargoProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Programa> listarTodosProgramas() throws NegocioException, SQLException {
        try {
            return manterCargo.listarTodosProgramas();
        } catch (RemoteException ex) {
            Logger.getLogger(ManterCargoProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean alterar(String codRegistro, Cargo cargo, List<String> listaProgramas) throws NegocioException, SQLException {
        try {
            return manterCargo.alterar(codRegistro, cargo, listaProgramas);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterCargoProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean excluir(String codRegistro) throws NegocioException, SQLException {
        try {
            return manterCargo.excluir(codRegistro);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterCargoProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
