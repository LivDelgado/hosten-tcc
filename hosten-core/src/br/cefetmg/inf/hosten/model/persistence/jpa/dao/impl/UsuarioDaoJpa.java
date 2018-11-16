package br.cefetmg.inf.hosten.model.persistence.jpa.dao.impl;

import br.cefetmg.inf.hosten.model.persistence.jpa.domain.CargoJpa;
import br.cefetmg.inf.hosten.model.persistence.jpa.domain.UsuarioJpa;
import br.cefetmg.inf.util.SenhaUtils;
import br.cefetmg.inf.util.bd.BdUtils;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import br.cefetmg.inf.hosten.model.persistence.jpa.dao.IUsuarioDaoJpa;

public class UsuarioDaoJpa implements IUsuarioDaoJpa {

    private static final String NAMED_QUERY_BASE = "Usuario.findBy";

    private static UsuarioDaoJpa instancia;

    private final EntityManager em;

    private UsuarioDaoJpa() {
        em = BdUtils.getEntityManager();
    }

    public static synchronized UsuarioDaoJpa getInstance() {
        if (instancia == null) {
            instancia = new UsuarioDaoJpa();
        }
        return instancia;
    }

    @Override
    public boolean adiciona(UsuarioJpa usuario) throws SQLException {
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();

        return true;
    }

    @Override
    public UsuarioJpa buscaPorPk(String id) throws SQLException {
        em.getTransaction().begin();
        UsuarioJpa usuario = em.find(UsuarioJpa.class, id);
        em.getTransaction().commit();

        return usuario;
    }

    @Override
    public List<UsuarioJpa> buscaPorColuna(Object dadoBusca, String coluna)
            throws SQLException {
        String parametro = "";
        String qryBusca = NAMED_QUERY_BASE;

        switch (coluna.toLowerCase()) {
            case "codusuario":
                qryBusca += "CodUsuario";
                parametro = "codUsuario";
                break;
            case "nomusuario":
                qryBusca += "NomUsuario";
                parametro = "nomUsuario";
                break;
            case "dessenha":
                qryBusca += "DesSenha";
                parametro = "desSenha";
                break;
            case "desemail":
                qryBusca += "DesEmail";
                parametro = "desEmail";
                break;
        }

        em.getTransaction().begin();

        TypedQuery<UsuarioJpa> tq = em
                .createNamedQuery(qryBusca, UsuarioJpa.class)
                .setParameter(parametro, dadoBusca);
        List<UsuarioJpa> usuarios = tq.getResultList();

        em.getTransaction().commit();

        return usuarios;
    }

    @Override
    public List<UsuarioJpa> buscaTodos() throws SQLException {
        em.getTransaction().begin();

        TypedQuery<UsuarioJpa> tq = em.createNamedQuery("Usuario.findAll", UsuarioJpa.class);
        List<UsuarioJpa> usuarios = tq.getResultList();

        em.getTransaction().commit();

        return usuarios;
    }

    @Override
    public boolean atualiza(String id, UsuarioJpa usuarioNov)
            throws SQLException {
        em.getTransaction().begin();
        UsuarioJpa usuarioAnt = em.find(UsuarioJpa.class, id);

        usuarioAnt.setNomUsuario(usuarioNov.getNomUsuario());
        usuarioAnt.setDesEmail(usuarioNov.getDesEmail());
        usuarioAnt.setDesSenha(usuarioNov.getDesSenha());
        
        CargoJpa crgAnt = usuarioAnt.getCodCargo();
        CargoJpa crgNov = usuarioNov.getCodCargo();
        if (!crgAnt.equals(crgNov)) {
           crgAnt.removeUsuario(usuarioAnt, crgNov);
        }

        em.getTransaction().commit();

        return true;
    }

    @Override
    public boolean deleta(UsuarioJpa usuario) throws SQLException {
        em.getTransaction().begin();
        em.remove(usuario);
        em.getTransaction().commit();

        return true;
    }

    @Override
    public UsuarioJpa usuarioLogin(String email, String senha) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        UsuarioJpa usuario = em
                .createNamedQuery("Usuario.findByDesEmail", UsuarioJpa.class)
                .setParameter("desEmail", email)
                .getResultList()
                .get(0);

        if (SenhaUtils.verificaSenha(senha, usuario.getDesSenha())) {
            return usuario;
        }

        // Retorna null caso o email não bata com a senha
        return null;
    }
}
