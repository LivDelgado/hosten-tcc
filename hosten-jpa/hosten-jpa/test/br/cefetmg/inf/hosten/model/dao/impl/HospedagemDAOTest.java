package br.cefetmg.inf.hosten.model.dao.impl;

import br.cefetmg.inf.hosten.model.dao.IHospedagemDAO;
import br.cefetmg.inf.hosten.model.dao.rel.impl.QuartoConsumoDAO;
import br.cefetmg.inf.hosten.model.dao.rel.impl.QuartoHospedagemDAO;
import br.cefetmg.inf.hosten.model.domain.Hospedagem;
import br.cefetmg.inf.hosten.model.domain.Hospede;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoConsumo;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoHospedagem;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Nícolas
 */
public class HospedagemDAOTest extends BaseTest<Hospedagem, IHospedagemDAO> {

    private final HospedeDAO hDAO = HospedeDAO.getInstance();

    public HospedagemDAOTest() throws SQLException {
        dao = HospedagemDAO.getInstance();
        limpaTabelas();
    }
    
    @Override
    final void limpaTabelas() throws SQLException {
        QuartoHospedagemDAO qhDAO = QuartoHospedagemDAO.getInstance();
        QuartoConsumoDAO qcDAO = QuartoConsumoDAO.getInstance();

        for (QuartoConsumo qc : qcDAO.buscaTodos()) {
            qcDAO.deleta(qc);
        }
        for (QuartoHospedagem qh : qhDAO.buscaTodos()) {
            qhDAO.deleta(qh);
        }
        for (Hospedagem h : dao.buscaTodos()) {
            dao.deleta(h);
        }
        for (Hospede h : hDAO.buscaTodos()) {
            hDAO.deleta(h);
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
    Hospedagem getNewInstanciaDomain(int i) {
        Hospedagem hospedagem = new Hospedagem(
                Date.from(Instant.now()),
                Date.from(Instant.now()),
                BigDecimal.valueOf(Double.parseDouble("" + (i * 10))));

        return hospedagem;
    }

    Hospede getNewHospede(int i) {
        Hospede hospede = new Hospede(
                i + "" + i + "" + i + "" + i + "" + i + "" + i + "" + i + "" + i + "" + i + "" + i,
                "Hóspede " + i,
                "9999999999",
                "hospede" + i + "@email.com");

        return hospede;
    }

    /**
     * Test of adiciona method, of class HospedagemDAO.
     */
    @Test
    public void testAdiciona() {
        imprimeTituloTeste("adiciona");
        try {
            Hospedagem h = getNewInstanciaDomain(1);
            dao.adiciona(h);

            imprimeConteudoObjeto(h, "");

            concluiComSucesso();
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of buscaPorPk method, of class HospedagemDAO.
     */
    @Test
    public void testBuscaPorPk() throws SQLException {
        imprimeTituloTeste("buscaPorPk");

        Hospedagem expResult = getNewInstanciaDomain(2);

        imprimeConteudoObjeto(expResult, "expResult = ");
        try {
            dao.adiciona(expResult);

            Hospedagem result = dao.buscaPorPk(expResult.getSeqHospedagem());

            imprimeConteudoObjeto(result, "result = ");

            if (expResult.equals(result)) {
                concluiComSucesso();
            } else {
                concluiComFalha(null);
            }
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of buscaPorColuna method, of class HospedagemDAO.
     */
    @Test
    public void testBuscaPorColuna() {
        imprimeTituloTeste("buscaPorColuna");

        try {
            Hospedagem h = getNewInstanciaDomain(3);
            imprimeConteudoObjeto(h, "");

            dao.adiciona(h);

            List<Hospedagem> result = dao.buscaPorColuna(h.getSeqHospedagem(), "seqHospedagem");

            boolean sucesso = false;
            for (Hospedagem c : result) {
                imprimeConteudoObjeto(c, "");

                if (h.equals(c)) {
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
     * Test of buscaTodos method, of class HospedagemDAO.
     */
    @Test
    public void testBuscaTodos() {
        imprimeTituloTeste("buscaTodos");

        Hospedagem c1 = getNewInstanciaDomain(4);
        imprimeConteudoObjeto(c1, "c1 = ");
        Hospedagem c2 = getNewInstanciaDomain(5);
        imprimeConteudoObjeto(c2, "c2 = ");

        try {
            dao.adiciona(c1);
            dao.adiciona(c2);

            int j = 0;
            List<Hospedagem> result = dao.buscaTodos();
            for (Hospedagem c : result) {
                imprimeConteudoObjeto(c, "");
                if (c1.equals(c) || c2.equals(c)) {
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
     * Test of atualiza method, of class HospedagemDAO.
     */
    @Test
    public void testAtualiza() {
        imprimeTituloTeste("atualiza");

        Hospedagem hosp = getNewInstanciaDomain(6);

        try {
            Hospede h = getNewHospede(9);
            h.addHospedagem(hosp);
            
            hDAO.adiciona(h);
            dao.adiciona(hosp);
            
            imprimeConteudoObjeto(hosp, "Hospedagem antes do Update = ");
            
            Hospede h2 = getNewHospede(7);
            hDAO.adiciona(h2);

            Hospedagem hosp2 = getNewInstanciaDomain(7);
            h2.addHospedagem(hosp2);

            dao.atualiza(hosp.getSeqHospedagem(), hosp2);
            
            hosp = dao.buscaPorPk(hosp.getSeqHospedagem());
            imprimeConteudoObjeto(hosp, "Hospedagem depois do Update =");

            concluiComSucesso();
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of deleta method, of class HospedagemDAO.
     */
    @Test
    public void testDeleta() {
        imprimeTituloTeste("deleta");

        Hospedagem c = getNewInstanciaDomain(8);

        try {
            dao.adiciona(c);

            dao.deleta(c);

            Hospedagem c2 = dao.buscaPorPk(c.getSeqHospedagem());
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
