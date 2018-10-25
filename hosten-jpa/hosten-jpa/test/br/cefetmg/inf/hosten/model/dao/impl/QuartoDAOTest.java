package br.cefetmg.inf.hosten.model.dao.impl;

import br.cefetmg.inf.hosten.model.dao.ICategoriaQuartoDAO;
import br.cefetmg.inf.hosten.model.dao.IQuartoDAO;
import static br.cefetmg.inf.hosten.model.dao.impl.BaseTest.imprimeTituloTeste;
import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import br.cefetmg.inf.hosten.model.domain.Quarto;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Nícolas
 */
public class QuartoDAOTest extends BaseTest<Quarto, IQuartoDAO> {

    private final ICategoriaQuartoDAO cqDao;

    public QuartoDAOTest() throws SQLException {
        dao = QuartoDAO.getInstance();
        cqDao = CategoriaQuartoDAO.getInstance();
        limpaTabelas();
    }

    @Override
    final void limpaTabelas() throws SQLException {
        for (Quarto q : dao.buscaTodos()) {
            dao.deleta(q);
        }

        for (CategoriaQuarto cq : cqDao.buscaTodos()) {
            cqDao.deleta(cq);
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
    Quarto getNewInstanciaDomain(int i) {
        Quarto q = new Quarto((short) i, false);
        System.out.println("------------------------");
        imprimeConteudoObjeto(q, "Quarto(" + i +")=");
        System.out.println("------------------------");
        
        return q;
    }

    CategoriaQuarto getNewCategoria(int i) {
        return new CategoriaQuarto(
                String.valueOf(i),
                "Categoria n° " + i,
                BigDecimal.valueOf(i * 10.0));
    }
    
        /**
     * Test of adiciona method, of class QuartoDAO.
     */
    @Test
    public void testAdiciona() {
        imprimeTituloTeste("adiciona");
        
        CategoriaQuarto cq = getNewCategoria(1);
        Quarto q = getNewInstanciaDomain(1);
        cq.addQuarto(q);
        try {
            cqDao.adiciona(cq);
            dao.adiciona(q);

            concluiComSucesso();
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of buscaPorPk method, of class QuartoDAO.
     */
    @Test
    public void testBuscaPorPk() {
        imprimeTituloTeste("buscaPorPk");

        CategoriaQuarto cq = getNewCategoria(4);
        Quarto q = getNewInstanciaDomain(2);
        cq.addQuarto(q);
        try {
            cqDao.adiciona(cq);
            dao.adiciona(q);

            Quarto result = dao.buscaPorPk(q.getNroQuarto());
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
     * Test of buscaPorColuna method, of class QuartoDAO.
     */
    @Test
    public void testBuscaPorColuna() {
        imprimeTituloTeste("buscaPorColuna");

        CategoriaQuarto cq = getNewCategoria(2);
        Quarto q = getNewInstanciaDomain(3);
        cq.addQuarto(q);
        try {
            cqDao.adiciona(cq);
            dao.adiciona(q);

            List<Quarto> result = dao.buscaPorColuna(q.getNroQuarto(), "nroQuarto");

            boolean sucesso = false;
            for (Quarto qBusca : result) {
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
     * Test of buscaTodos method, of class QuartoDAO.
     */
    @Test
    public void testBuscaTodos() {
        imprimeTituloTeste("buscaTodos");
        
        CategoriaQuarto cq = getNewCategoria(3);
        
        Quarto q1 = getNewInstanciaDomain(4);
        cq.addQuarto(q1);
        Quarto q2 = getNewInstanciaDomain(5);
        cq.addQuarto(q2);
        try {
            cqDao.adiciona(cq);
            dao.adiciona(q1);
            dao.adiciona(q2);

            int j = 0;
            List<Quarto> result = dao.buscaTodos();
            for (Quarto q : result) {
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
     * Test of atualiza method, of class QuartoDAO.
     */
    @Test
    public void testAtualiza() {
        imprimeTituloTeste("atualiza");

        CategoriaQuarto cq = getNewCategoria(5);
        CategoriaQuarto cq2 = getNewCategoria(6);
        
        Quarto q = getNewInstanciaDomain(6);
        Quarto q2 = getNewInstanciaDomain(7);
        
        cq.addQuarto(q);
        cq2.addQuarto(q2);
        try {
            cqDao.adiciona(cq);
            dao.adiciona(q);
    
            cqDao.adiciona(cq2);

            dao.atualiza(q.getNroQuarto(), q2);

            q = dao.buscaPorPk(q.getNroQuarto());
            imprimeConteudoObjeto(q, "Quarto depois do Update =");

            if (q.getCodCategoria().equals(q2.getCodCategoria())) {
                concluiComSucesso();
            } else {
                concluiComFalha(null);
            }
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of deleta method, of class QuartoDAO.
     */
    @Test
    public void testDeleta() {
        imprimeTituloTeste("deleta");

        Quarto q = getNewInstanciaDomain(8);
        CategoriaQuarto cq = getNewCategoria(1);
        cq.addQuarto(q);
        
        try {
            cqDao.adiciona(cq);
            dao.adiciona(q);

            dao.deleta(q);

            Quarto c2 = dao.buscaPorPk(q.getNroQuarto());
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
