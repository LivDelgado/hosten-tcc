package br.cefetmg.inf.hosten.model.dao.impl;

import br.cefetmg.inf.hosten.model.dao.ICargoDAO;
import br.cefetmg.inf.hosten.model.dao.IProgramaDAO;
import br.cefetmg.inf.hosten.model.dao.IUsuarioDAO;
import static br.cefetmg.inf.hosten.model.dao.impl.BaseTest.imprimeTituloTeste;
import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.hosten.model.domain.Programa;
import br.cefetmg.inf.hosten.model.domain.Usuario;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Nícolas
 */
public class UsuarioDAOTest extends BaseTest<Usuario, IUsuarioDAO> {

    private final ICargoDAO crgDao;

    public UsuarioDAOTest() throws SQLException {
        dao = UsuarioDAO.getInstance();
        crgDao = CargoDAO.getInstance();
        // limpaTabelas();
    }

    @Override
    final void limpaTabelas() throws SQLException {
        for (Usuario user : dao.buscaTodos()) {
            dao.deleta(user);
        }
        IProgramaDAO progDao = ProgramaDAO.getInstance();
        for (Programa p : progDao.buscaTodos()) {
            Iterator<Cargo> itC = p.getCargos().iterator();
            while (itC.hasNext()) {
                Cargo c = itC.next();
                p.removeCargo(c);
            }
            progDao.deleta(p);
        }
        for (Cargo crg : crgDao.buscaTodos()) {
            crgDao.deleta(crg);
        }
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /*
    @Before
    public void setUp() {
        System.out.println("\n\n\n");
    }

    @After
    public void tearDown() {
    }
     */
    @Override
    Usuario getNewInstanciaDomain(int i) {
        String cod = "";
        if (i < 10) {
            cod = "000" + i;
        } else if (i >= 10 && i < 100) {
            cod = "00" + i;
        } else if (i >= 100 && i < 1000) {
            cod = "0" + i;
        } else if (i >= 1000 && i < 10000) {
            cod = String.valueOf(i);
        }
        
        Usuario q = new Usuario(
                cod,
                "Usuário n° " + i,
                "senha",
                "usuario" + i + "@email.com");

        System.out.println("------------------------");
        imprimeConteudoObjeto(q, "Usuario(" + i + ")=");
        System.out.println("------------------------");

        return q;
    }

    Cargo getNewCargo(int i) {
        return new Cargo(
                String.valueOf(i),
                "Categoria n° " + i,
                false);
    }

    /**
     * Test of adiciona method, of class UsuarioDAO.
     */
    @Test
    public void testAdiciona() {
        imprimeTituloTeste("adiciona");

        Cargo cq = getNewCargo(1);
        Usuario q = getNewInstanciaDomain(1);
        cq.addUsuario(q);
        try {
            crgDao.adiciona(cq);
            dao.adiciona(q);

            concluiComSucesso();
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of buscaPorPk method, of class UsuarioDAO.
     */
    @Test
    public void testBuscaPorPk() {
        imprimeTituloTeste("buscaPorPk");

        Cargo cq = getNewCargo(4);
        Usuario q = getNewInstanciaDomain(2);
        cq.addUsuario(q);
        try {
            crgDao.adiciona(cq);
            dao.adiciona(q);

            Usuario result = dao.buscaPorPk(q.getCodUsuario());
            imprimeConteudoObjeto(result, "result = ");

            if (q.equals(result)) {
                concluiComSucesso();
            } else {
                concluiComFalha(null);
            }
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of buscaPorColuna method, of class UsuarioDAO.
     */
    @Test
    public void testBuscaPorColuna() {
        imprimeTituloTeste("buscaPorColuna");

        Cargo cq = getNewCargo(2);
        Usuario q = getNewInstanciaDomain(3);
        cq.addUsuario(q);
        try {
            crgDao.adiciona(cq);
            dao.adiciona(q);

            List<Usuario> result = dao.buscaPorColuna(q.getCodUsuario(), "codUsuario");

            boolean sucesso = false;
            for (Usuario qBusca : result) {
                imprimeConteudoObjeto(qBusca, "");

                if (q.equals(qBusca)) {
                    sucesso = true;
                }
            }
            if (sucesso) {
                concluiComSucesso();
            } else {
                concluiComFalha(null);
            }
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of buscaTodos method, of class UsuarioDAO.
     */
    @Test
    public void testBuscaTodos() {
        imprimeTituloTeste("buscaTodos");

        Cargo cq = getNewCargo(3);

        Usuario q1 = getNewInstanciaDomain(4);
        cq.addUsuario(q1);
        Usuario q2 = getNewInstanciaDomain(5);
        cq.addUsuario(q2);
        try {
            crgDao.adiciona(cq);
            dao.adiciona(q1);
            dao.adiciona(q2);

            int j = 0;
            List<Usuario> result = dao.buscaTodos();
            for (Usuario q : result) {
                imprimeConteudoObjeto(q, "");
                if (q1.equals(q) || q2.equals(q)) {
                    j++;
                }
            }

            if (j >= 2) {
                concluiComSucesso();
            } else {
                concluiComFalha(null);
            }
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of atualiza method, of class UsuarioDAO.
     */
    @Test
    public void testAtualiza() {
        imprimeTituloTeste("atualiza");

        Cargo cq = getNewCargo(5);
        Cargo cq2 = getNewCargo(6);

        Usuario q = getNewInstanciaDomain(6);
        Usuario q2 = getNewInstanciaDomain(7);

        cq.addUsuario(q);
        cq2.addUsuario(q2);
        try {
            crgDao.adiciona(cq);
            dao.adiciona(q);

            crgDao.adiciona(cq2);

            dao.atualiza(q.getCodUsuario(), q2);
            dao.adiciona(q2);

            q = dao.buscaPorPk(q.getCodUsuario());
            imprimeConteudoObjeto(q, "Usuario depois do Update =");

            if (q.getCodCargo().equals(q2.getCodCargo())) {
                concluiComSucesso();
            } else {
                concluiComFalha(null);
            }
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of deleta method, of class UsuarioDAO.
     */
    @Test
    public void testDeleta() {
        imprimeTituloTeste("deleta");

        Usuario q = getNewInstanciaDomain(8);
        Cargo cq = getNewCargo(8);
        cq.addUsuario(q);

        try {
            crgDao.adiciona(cq);
            dao.adiciona(q);

            dao.deleta(q);

            Usuario c2 = dao.buscaPorPk(q.getCodUsuario());
            if (c2 == null) {
                concluiComSucesso();
            } else {
                concluiComFalha(null);
            }
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }
}
