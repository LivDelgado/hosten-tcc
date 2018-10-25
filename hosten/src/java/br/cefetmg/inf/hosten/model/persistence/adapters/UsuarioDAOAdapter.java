package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.persistence.interfaces.IUsuarioDAO;
import br.cefetmg.inf.hosten.model.domain.Usuario;
import br.cefetmg.inf.hosten.model.persistence.jdbc.UsuarioDAO;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public class UsuarioDAOAdapter implements IUsuarioDAO {

    private static IUsuarioDAO instancia;

    public static synchronized IUsuarioDAO getInstance() {
        if (instancia == null) {
            instancia = UsuarioDAO.getInstance();
        }
        return instancia;
    }

    @Override
    public boolean adicionaUsuario(Usuario usuario)
            throws SQLException,
            NoSuchAlgorithmException,
            UnsupportedEncodingException {
        return instancia.adicionaUsuario(usuario);
    }

    @Override
    public List<Usuario> buscaUsuario(Object dadoBusca, String coluna)
            throws SQLException,
            NoSuchAlgorithmException,
            UnsupportedEncodingException {
        return instancia.buscaUsuario(dadoBusca, coluna);
    }

    @Override
    public List<Usuario> buscaTodosUsuarios()
            throws SQLException,
            NoSuchAlgorithmException,
            UnsupportedEncodingException {
        return instancia.buscaTodosUsuarios();
    }

    @Override
    public boolean atualizaUsuario(Object pK, Usuario usuarioAtualizado)
            throws SQLException,
            NoSuchAlgorithmException,
            UnsupportedEncodingException {
        return instancia.atualizaUsuario(pK, usuarioAtualizado);
    }

    @Override
    public boolean deletaUsuario(Object pK) throws SQLException {
        return instancia.deletaUsuario(pK);
    }

    @Override
    public Usuario usuarioLogin(String email, String senha)
            throws SQLException,
            NoSuchAlgorithmException, UnsupportedEncodingException {
        return instancia.usuarioLogin(email, senha);
    }
}
