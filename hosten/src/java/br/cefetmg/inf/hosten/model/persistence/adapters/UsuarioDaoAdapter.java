package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.domain.Usuario;
import br.cefetmg.inf.hosten.model.persistence.jdbc.UsuarioDao;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IUsuarioDao;

public class UsuarioDaoAdapter implements IUsuarioDao {

    private static IUsuarioDao instancia;

    public static synchronized IUsuarioDao getInstance() {
        if (instancia == null) {
            instancia = UsuarioDao.getInstance();
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
