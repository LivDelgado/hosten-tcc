package br.cefetmg.inf.hosten.dist.proxy;

import br.cefetmg.inf.hosten.dist.ClienteRMI;
import br.cefetmg.inf.hosten.model.domain.Hospede;
import br.cefetmg.inf.hosten.model.service.IManterHospede;
import br.cefetmg.inf.hosten.model.service.remote.IManterHospedeRemote;
import br.cefetmg.inf.util.exception.NegocioException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManterHospedeProxy implements IManterHospede {
    private final IManterHospedeRemote manterHospede;

    public ManterHospedeProxy() {
        this.manterHospede = (IManterHospedeRemote) ClienteRMI.getInstance().receberObjeto("ManterHospede");
    }

    @Override
    public boolean inserir(Hospede hospede) throws NegocioException, SQLException {
        try {
            return manterHospede.inserir(hospede);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterHospedeProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Hospede> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException {
        try {
            return manterHospede.listar(dadoBusca, coluna);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterHospedeProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Hospede> listarTodos() throws NegocioException, SQLException {
        try {
            return manterHospede.listarTodos();
        } catch (RemoteException ex) {
            Logger.getLogger(ManterHospedeProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean alterar(String codRegistro, Hospede hospede) throws NegocioException, SQLException {
        try {
            return manterHospede.alterar(codRegistro, hospede);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterHospedeProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
