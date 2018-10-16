package br.cefetmg.inf.hosten.model.dao.impl;

import br.cefetmg.inf.hosten.model.dao.ICategoriaQuartoDAO;
import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import br.cefetmg.inf.hosten.model.domain.Programa;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nícolas
 */
public class CategoriaQuartoDAOTest extends BaseTest<CategoriaQuarto, ICategoriaQuartoDAO> {
    
    public CategoriaQuartoDAOTest() throws SQLException {
        DAO = CategoriaQuartoDAO.getInstance();
        
        for (CategoriaQuarto cq : DAO.buscaTodos()) {
            Iterator<ItemConforto> itIc = cq.getItemConfortos().iterator();
            while (itIc.hasNext()) {
                ItemConforto ic = itIc.next();
                cq.removeItemConforto(ic);
            }
        }
        for (CategoriaQuarto cq : DAO.buscaTodos()) {
            DAO.deleta(cq);
        }
        ItemConfortoDAO IcDAO = ItemConfortoDAO.getInstance();
        for(ItemConforto ic : IcDAO.buscaTodos()) {
            IcDAO.deleta(ic);
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
            DAO.adiciona(c);
            
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
            DAO.adiciona(expResult);

            CategoriaQuarto result = DAO.buscaPorPk(expResult.getCodCategoria());

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

            DAO.adiciona(categoriaQuarto);

            List<CategoriaQuarto> result = DAO.buscaPorColuna(categoriaQuarto.getCodCategoria(), "codCategoria");

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
            DAO.adiciona(c1);
            DAO.adiciona(c2);

            int j = 0;
            List<CategoriaQuarto> result = DAO.buscaTodos();
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
            DAO.adiciona(c);
            imprimeConteudoObjeto(c, "CategoriaQuarto antes do Update = ");

            CategoriaQuarto c2 = getNewInstanciaDomain(7);
            imprimeConteudoObjeto(c2, "O outro categoriaQuarto = ");

            DAO.atualiza(c.getCodCategoria(), c2);

            c = DAO.buscaPorPk(c.getCodCategoria());

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
            DAO.adiciona(c);

            DAO.deleta(c);

            CategoriaQuarto c2 = DAO.buscaPorPk(c.getCodCategoria());
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
