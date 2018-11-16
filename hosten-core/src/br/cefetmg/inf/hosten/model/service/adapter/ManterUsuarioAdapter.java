package br.cefetmg.inf.hosten.model.service.adapter;

import br.cefetmg.inf.hosten.model.domain.Usuario;
import br.cefetmg.inf.hosten.model.service.IManterUsuario;
import br.cefetmg.inf.hosten.model.service.impl.ManterUsuario;
import br.cefetmg.inf.hosten.model.service.remote.IManterUsuarioRemote;
import br.cefetmg.inf.util.exception.NegocioException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public class ManterUsuarioAdapter implements IManterUsuarioRemote {
    private final IManterUsuario manterUsuario;

    public ManterUsuarioAdapter() {
        this.manterUsuario = new ManterUsuario();
    }

    @Override
    public boolean inserir(Usuario usuario) throws NegocioException, SQLException, RemoteException {
        return manterUsuario.inserir(usuario);
    }

    @Override
    public List<Usuario> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException, RemoteException {
        return manterUsuario.listar(dadoBusca, coluna);
    }

    @Override
    public List<Usuario> listarTodos() throws NegocioException, SQLException, RemoteException {
        return manterUsuario.listarTodos();
    }

    @Override
    public boolean alterar(String codRegistro, Usuario usuario) throws NegocioException, SQLException, RemoteException {
        return manterUsuario.alterar(codRegistro, usuario);
    }

    @Override
    public boolean excluir(String codRegistro) throws NegocioException, SQLException, RemoteException {
        return manterUsuario.excluir(codRegistro);
    }

    @Override
    public Usuario usuarioLogin(String email, String senha) throws NegocioException, SQLException, RemoteException {
        return manterUsuario.usuarioLogin(email, senha);
    }
}
