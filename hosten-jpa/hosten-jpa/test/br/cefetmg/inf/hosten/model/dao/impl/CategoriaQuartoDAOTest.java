package br.cefetmg.inf.hosten.model.dao.impl;

import br.cefetmg.inf.hosten.model.dao.ICategoriaQuartoDAO;
import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import java.math.BigDecimal;
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
public class CategoriaQuartoDAOTest extends BaseTest<CategoriaQuarto, ICategoriaQuartoDAO> {
    
    public CategoriaQuartoDAOTest() throws SQLException {
        dao = CategoriaQuartoDAO.getInstance();
        limpaTabelas();
    }
    
    @Override
    final void limpaTabelas() throws SQLException {
        for (CategoriaQuarto cq : dao.buscaTodos()) {
            Iterator<ItemConforto> itIc = cq.getItemConfortos().iterator();
            while (itIc.hasNext()) {
                ItemConforto ic = itIc.next();
                cq.removeItemConforto(ic);
            }
            dao.deleta(cq);
        }
        ItemConfortoDAO icDao = ItemConfortoDAO.getInstance();
        for (ItemConforto ic : icDao.buscaTodos()) {
            icDao.deleta(ic);
        }
    }

    @BeforeClass
    public static void setUpClass()  {
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
    CategoriaQuarto getNewInstanciaDomain(int i) {
        if (i < 10) {
            return new CategoriaQuarto("00" + i, "CategoriaQuarto " + i, BigDecimal.valueOf(Double.parseDouble((i * 10) + "")));
        } else if (i >= 10 && i < 100) {
            return new CategoriaQuarto("0" + i, "CategoriaQuarto " + i, BigDecimal.valueOf(Double.parseDouble((i * 100) + "")));
        } else {
            return new CategoriaQuarto(String.valueOf(i), "CategoriaQuarto " + i, BigDecimal.valueOf(Double.parseDouble((i * 1000) + "")));
        }
    }

    /**
     * Test of adiciona method, of class CategoriaQuartoDAO.
     */
    @Test
    public void testAdiciona() {
        imprimeTituloTeste("adiciona");
        try {
            CategoriaQuarto c = getNewInstanciaDomain(1);
            dao.adiciona(c);
            
            ItemConforto ic = new ItemConforto("001", "Frigobar");
            c.addItemConforto(ic);
            
            imprimeConteudoObjeto(c, "");
            
            concluiComSucesso();
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of buscaPorPk method, of class CategoriaQuartoDAO.
     */
    @Test
    public void testBuscaPorPk() {
        imprimeTituloTeste("buscaPorPk");

        CategoriaQuarto expResult = getNewInstanciaDomain(2);

        imprimeConteudoObjeto(expResult, "expResult = ");
        try {
            dao.adiciona(expResult);

            CategoriaQuarto result = dao.buscaPorPk(expResult.getCodCategoria());

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
     * Test of buscaPorColuna method, of class CategoriaQuartoDAO.
     */
    @Test
    public void testBuscaPorColuna() {
        imprimeTituloTeste("buscaPorColuna");

        try {
            CategoriaQuarto categoriaQuarto = getNewInstanciaDomain(3);
            imprimeConteudoObjeto(categoriaQuarto, "");

            dao.adiciona(categoriaQuarto);

            List<CategoriaQuarto> result = dao.buscaPorColuna(categoriaQuarto.getCodCategoria(), "codCategoria");

            boolean sucesso = false;
            for (CategoriaQuarto c : result) {
                imprimeConteudoObjeto(c, "");

                if (categoriaQuarto.equals(c)) {
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
     * Test of buscaTodos method, of class CategoriaQuartoDAO.
     */
    @Test
    public void testBuscaTodos() {
        imprimeTituloTeste("buscaTodos");

        CategoriaQuarto c1 = getNewInstanciaDomain(4);
        imprimeConteudoObjeto(c1, "c1 = ");
        CategoriaQuarto c2 = getNewInstanciaDomain(5);
        imprimeConteudoObjeto(c2, "c2 = ");

        try {
            dao.adiciona(c1);
            dao.adiciona(c2);

            int j = 0;
            List<CategoriaQuarto> result = dao.buscaTodos();
            for (CategoriaQuarto c : result) {
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
     * Test of atualiza method, of class CategoriaQuartoDAO.
     */
    @Test
    public void testAtualiza() {
        imprimeTituloTeste("atualiza");

        CategoriaQuarto c = getNewInstanciaDomain(6);

        try {
            dao.adiciona(c);
            imprimeConteudoObjeto(c, "CategoriaQuarto antes do Update = ");

            CategoriaQuarto c2 = getNewInstanciaDomain(7);
            imprimeConteudoObjeto(c2, "O outro categoriaQuarto = ");

            dao.atualiza(c.getCodCategoria(), c2);

            c = dao.buscaPorPk(c.getCodCategoria());

        imprimeConteudoObjeto(c, "CategoriaQuarto depois do Update =");

            if (c.getNomCategoria().equals(c2.getNomCategoria())) {
                concluiComSucesso();
            } else {
                concluiComFalha(null);
            }
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of deleta method, of class CategoriaQuartoDAO.
     */
    @Test
    public void testDeleta() {
        imprimeTituloTeste("deleta");

        CategoriaQuarto c = getNewInstanciaDomain(8);

        try {
            dao.adiciona(c);

            dao.deleta(c);

            CategoriaQuarto c2 = dao.buscaPorPk(c.getCodCategoria());
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
