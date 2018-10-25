package br.cefetmg.inf.hosten.model.dao.impl;

import br.cefetmg.inf.hosten.model.dao.IHospedagemDAO;
import br.cefetmg.inf.hosten.model.dao.IHospedeDAO;
import br.cefetmg.inf.hosten.model.domain.Hospedagem;
import br.cefetmg.inf.hosten.model.domain.Hospede;
import java.sql.SQLException;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Nícolas
 */
public class HospedeDAOTest extends BaseTest<Hospede, IHospedeDAO> {

    public HospedeDAOTest() throws SQLException {
        dao = HospedeDAO.getInstance();
        limpaTabelas();
    }

    @Override
    final void limpaTabelas() throws SQLException {
        IHospedagemDAO hospDAO = HospedagemDAO.getInstance();
        for (Hospedagem hosp : hospDAO.buscaTodos()) {
            hospDAO.deleta(hosp);
        }
        for (Hospede h : dao.buscaTodos()) {
            dao.deleta(h);
        }
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Override
    Hospede getNewInstanciaDomain(int i) {
        Hospede h = new Hospede(
                // CPF
                String.valueOf(i * 1000),
                // nome
                "Hóspede n° " + i,
                // telefone
                "9." + i * 33 + "11",
                // e-mail
                "hospede" + i + "@email.com");
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
     * Test of getInstance method, of class HospedeDAO.
     */
    @Test
    public void testGetInstance() {
        imprimeTituloTeste("getInstance");

        if (dao == HospedeDAO.getInstance()) {
            concluiComSucesso();
        } else {
            concluiComFalha(null);
        }
    }

    /**
     * Test of adiciona method, of class HospedeDAO.
     */
    @Test
    public void testAdiciona() {
        imprimeTituloTeste("adiciona");

        Hospede h = getNewInstanciaDomain(1);
        imprimeConteudoObjeto(h, "");
        try {
            dao.adiciona(h);
            concluiComSucesso();
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of buscaPorPk method, of class HospedeDAO.
     */
    @Test
    public void testBuscaPorPk() {
        imprimeTituloTeste("buscaPorPk");

        Hospede h = getNewInstanciaDomain(2);
        imprimeConteudoObjeto(h, "h=");
        try {
            dao.adiciona(h);

            Hospede h2 = dao.buscaPorPk(h.getCodCpf());
            imprimeConteudoObjeto(h2, "h2=");

            if (h.equals(h2)) {
                concluiComSucesso();
            }
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of buscaPorColuna method, of class HospedeDAO.
     */
    @Test
    public void testBuscaPorColuna() {
        imprimeTituloTeste("buscaPorColuna");

        Hospede h = getNewInstanciaDomain(3);
        imprimeConteudoObjeto(h, "h=");
        try {
            dao.adiciona(h);

            List<Hospede> hs = dao.buscaPorColuna(h.getCodCpf(), "codCpf");
            if (hs.get(0).equals(h)) {
                concluiComSucesso();
            }
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of buscaTodos method, of class HospedeDAO.
     */
    @Test
    public void testBuscaTodos() {
        imprimeTituloTeste("buscaTodos");

        Hospede h = getNewInstanciaDomain(4);
        imprimeConteudoObjeto(h, "h=");
        Hospede h2 = getNewInstanciaDomain(5);
        imprimeConteudoObjeto(h2, "h2=");
        try {
            dao.adiciona(h);
            dao.adiciona(h2);

            List<Hospede> hs = dao.buscaTodos();
            int i = 0;
            for (Hospede hBusca : hs) {
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
     * Test of atualiza method, of class HospedeDAO.
     */
    @Test
    public void testAtualiza() {
        imprimeTituloTeste("atualiza");

        Hospede h = getNewInstanciaDomain(6);
        imprimeConteudoObjeto(h, "h=");
        Hospede h2 = getNewInstanciaDomain(7);
        imprimeConteudoObjeto(h2, "h2=");
        try {
            dao.adiciona(h);

            dao.atualiza(h.getCodCpf(), h2);
            imprimeConteudoObjeto(h, "h depois do Update=");

            concluiComSucesso();
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of deleta method, of class HospedeDAO.
     */
    @Test
    public void testDeleta() {
        imprimeTituloTeste("deleta");

        Hospede h = getNewInstanciaDomain(8);
        imprimeConteudoObjeto(h, "h=");
        try {
            dao.adiciona(h);

            Hospede hBuscado = dao.buscaPorPk(h.getCodCpf());
            imprimeConteudoObjeto(hBuscado, "Hóspede encontrado=");

            dao.deleta(h);
            Hospede hBuscado2 = dao.buscaPorPk(h.getCodCpf());
            if (hBuscado2 == null) {
                concluiComSucesso();
            }
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }
}
