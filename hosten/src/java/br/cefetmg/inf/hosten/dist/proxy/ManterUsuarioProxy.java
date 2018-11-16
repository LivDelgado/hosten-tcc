package br.cefetmg.inf.hosten.dist.proxy;

import br.cefetmg.inf.hosten.dist.ClienteRMI;
import br.cefetmg.inf.hosten.model.domain.Usuario;
import br.cefetmg.inf.hosten.model.service.IManterUsuario;
import br.cefetmg.inf.hosten.model.service.remote.IManterUsuarioRemote;
import br.cefetmg.inf.util.exception.NegocioException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ManterUsuarioProxy implements IManterUsuario {
    private final IManterUsuarioRemote manterUsuario;

    public ManterUsuarioProxy() {
        this.manterUsuario = (IManterUsuarioRemote) ClienteRMI.getInstance().receberObjeto("ManterUsuario");
    }

    @Override
    public boolean inserir(Usuario usuario) throws NegocioException, SQLException {
        try {
            return manterUsuario.inserir(usuario);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterUsuarioProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Usuario> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException {
        try {
            return manterUsuario.listar(dadoBusca, coluna);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterUsuarioProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Usuario> listarTodos() throws NegocioException, SQLException {
        try {
            return manterUsuario.listarTodos();
        } catch (RemoteException ex) {
            Logger.getLogger(ManterUsuarioProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean alterar(String codRegistro, Usuario usuario) throws NegocioException, SQLException {
        try {
            return manterUsuario.alterar(codRegistro, usuario);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterUsuarioProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean excluir(String codRegistro) throws NegocioException, SQLException {
        try {
            return manterUsuario.excluir(codRegistro);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterUsuarioProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Usuario usuarioLogin(String email, String senha) throws NegocioException, SQLException {
        try {
            return manterUsuario.usuarioLogin(email, senha);
        } catch (RemoteException ex) {
            Logger.getLogger(ManterUsuarioProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
