package br.cefetmg.inf.hosten.model.dao.impl;

import br.cefetmg.inf.hosten.model.dao.ICargoDAO;
import br.cefetmg.inf.hosten.model.dao.IHospedagemDAO;
import br.cefetmg.inf.hosten.model.dao.IProgramaDAO;
import static br.cefetmg.inf.hosten.model.dao.impl.BaseTest.imprimeTituloTeste;
import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.hosten.model.domain.Hospedagem;
import br.cefetmg.inf.hosten.model.domain.Programa;
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
public class ProgramaDAOTest extends BaseTest<Programa, IProgramaDAO> {

    public ProgramaDAOTest() throws SQLException {
        dao = ProgramaDAO.getInstance();
        limpaTabelas();
    }

    @Override
    final void limpaTabelas() throws SQLException {
        for (Programa prog : dao.buscaTodos()) {
            Iterator<Cargo> itCrg = prog.getCargos().iterator();
            while (itCrg.hasNext()) {
                prog.removeCargo(itCrg.next());
            }
            dao.deleta(prog);
        }
        ICargoDAO crgDao = CargoDAO.getInstance();
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

    @Override
    Programa getNewInstanciaDomain(int i) {
        Programa h = new Programa(
                // CPF
                String.valueOf(i),
                // nome
                "Programa n° " + i);
        return h;
    }

    /*
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
     */
    /**
     * Test of getInstance method, of class ProgramaDAO.
     */
    @Test
    public void testGetInstance() {
        imprimeTituloTeste("getInstance");

        if (dao == ProgramaDAO.getInstance()) {
            concluiComSucesso();
        } else {
            concluiComFalha(null);
        }
    }

    /**
     * Test of adiciona method, of class ProgramaDAO.
     */
    @Test
    public void testAdiciona() {
        imprimeTituloTeste("adiciona");

        Programa h = getNewInstanciaDomain(1);
        imprimeConteudoObjeto(h, "");
        try {
            dao.adiciona(h);
            concluiComSucesso();
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of buscaPorPk method, of class ProgramaDAO.
     */
    @Test
    public void testBuscaPorPk() {
        imprimeTituloTeste("buscaPorPk");

        Programa h = getNewInstanciaDomain(2);
        imprimeConteudoObjeto(h, "h=");
        try {
            dao.adiciona(h);

            Programa h2 = dao.buscaPorPk(h.getCodPrograma());
            imprimeConteudoObjeto(h2, "h2=");

            if (h.equals(h2)) {
                concluiComSucesso();
            }
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of buscaPorColuna method, of class ProgramaDAO.
     */
    @Test
    public void testBuscaPorColuna() {
        imprimeTituloTeste("buscaPorColuna");

        Programa h = getNewInstanciaDomain(3);
        imprimeConteudoObjeto(h, "h=");
        try {
            dao.adiciona(h);

            List<Programa> hs = dao.buscaPorColuna(h.getCodPrograma(), "codPrograma");
            if (hs.get(0).equals(h)) {
                concluiComSucesso();
            }
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of buscaTodos method, of class ProgramaDAO.
     */
    @Test
    public void testBuscaTodos() {
        imprimeTituloTeste("buscaTodos");

        Programa h = getNewInstanciaDomain(4);
        imprimeConteudoObjeto(h, "h=");
        Programa h2 = getNewInstanciaDomain(5);
        imprimeConteudoObjeto(h2, "h2=");
        try {
            dao.adiciona(h);
            dao.adiciona(h2);

            List<Programa> hs = dao.buscaTodos();
            int i = 0;
            for (Programa hBusca : hs) {
                if (hBusca.equals(h) || hBusca.equals(h2)) {
                    i++;
                }
            }
            if (i >= 2) {
                concluiComSucesso();
            }
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of atualiza method, of class ProgramaDAO.
     */
    @Test
    public void testAtualiza() {
        imprimeTituloTeste("atualiza");

        Programa h = getNewInstanciaDomain(6);
        imprimeConteudoObjeto(h, "h=");
        Programa h2 = getNewInstanciaDomain(7);
        imprimeConteudoObjeto(h2, "h2=");
        try {
            dao.adiciona(h);

            dao.atualiza(h.getCodPrograma(), h2);
            imprimeConteudoObjeto(h, "h depois do Update=");

            concluiComSucesso();
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of deleta method, of class ProgramaDAO.
     */
    @Test
    public void testDeleta() {
        imprimeTituloTeste("deleta");

        Programa h = getNewInstanciaDomain(8);
        imprimeConteudoObjeto(h, "h=");
        try {
            dao.adiciona(h);

            Programa hBuscado = dao.buscaPorPk(h.getCodPrograma());
            imprimeConteudoObjeto(hBuscado, "Programa encontrado=");

            dao.deleta(h);
            Programa hBuscado2 = dao.buscaPorPk(h.getCodPrograma());
            if (hBuscado2 == null) {
                concluiComSucesso();
            }
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }
}
