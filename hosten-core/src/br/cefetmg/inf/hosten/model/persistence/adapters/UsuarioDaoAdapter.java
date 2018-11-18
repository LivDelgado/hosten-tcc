package br.cefetmg.inf.hosten.model.persistence.adapters;

import br.cefetmg.inf.hosten.model.domain.Usuario;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IUsuarioDao;
import br.cefetmg.inf.hosten.model.persistence.jdbc.UsuarioDao;

public class UsuarioDaoAdapter implements IUsuarioDao {

    private final IUsuarioDao dao;
    private static IUsuarioDao instancia;

    private UsuarioDaoAdapter() {
        dao = UsuarioDao.getInstance();
    }

    public static synchronized IUsuarioDao getInstance() {
        if (instancia == null) {
            instancia = new UsuarioDaoAdapter();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(Usuario usuario)
            throws SQLException,
            NoSuchAlgorithmException,
            UnsupportedEncodingException {
        return dao.adiciona(usuario);
    }
    
    @Override
    public Usuario buscaPorPk(String id) throws SQLException {
        return dao.buscaPorPk(id);
    }

    @Override
    public List<Usuario> buscaPorColuna(Object dadoBusca, String coluna) throws SQLException {
        return dao.buscaPorColuna(dadoBusca, coluna);
    }

    @Override
    public List<Usuario> buscaTodos() throws SQLException {
        return dao.buscaTodos();
    }

    @Override
    public boolean atualiza(String pK, Usuario usuarioAtualizado)
            throws SQLException,
            NoSuchAlgorithmException,
            UnsupportedEncodingException {
        return dao.atualiza(pK, usuarioAtualizado);
    }

    @Override
    public boolean deleta(String pK) throws SQLException {
        return dao.deleta(pK);
    }

    @Override
    public Usuario usuarioLogin(String email, String senha)
            throws SQLException,
            NoSuchAlgorithmException, UnsupportedEncodingException {
        return dao.usuarioLogin(email, senha);
    }
}