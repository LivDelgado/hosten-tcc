package br.cefetmg.inf.hosten.dist.proxy;

import br.cefetmg.inf.hosten.dist.ClienteRMI;
import br.cefetmg.inf.hosten.model.domain.Quarto;
import br.cefetmg.inf.hosten.model.service.IManterQuarto;
import br.cefetmg.inf.hosten.model.service.remote.IManterQuartoRemote;
import br.cefetmg.inf.util.exception.NegocioException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ManterQuartoProxy implements IManterQuarto {
    private final IManterQuartoRemote manterQuarto;

    public ManterQuartoProxy() {
        this.manterQuarto = (IManterQuartoRemote) ClienteRMI.getInstance().receberObjeto("ManterQuarto");
    }

    @Override
    public boolean inserir(Quarto quarto) throws NegocioException, SQLException {
        try {
            return manterQuarto.inserir(quarto);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterQuartoProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Quarto> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException {
        try {
            return manterQuarto.listar(dadoBusca, coluna);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterQuartoProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Quarto> listarTodos() throws NegocioException, SQLException {
        try {
            return manterQuarto.listarTodos();
        } catch (RemoteException ex) {
            Logger.getLogger(ManterQuartoProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean alterar(String codRegistro, Quarto quarto) throws NegocioException, SQLException {
        try {
            return manterQuarto.alterar(codRegistro, quarto);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterQuartoProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean excluir(String codRegistro) throws NegocioException, SQLException {
        try {
            return manterQuarto.excluir(codRegistro);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterQuartoProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public int buscaUltimoRegistroRelacionadoAoQuarto(int nroQuarto) throws NegocioException, SQLException {
        try {
            return manterQuarto.buscaUltimoRegistroRelacionadoAoQuarto(nroQuarto);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterQuartoProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
}
