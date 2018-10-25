package br.cefetmg.inf.hosten.model.dao.impl;

import br.cefetmg.inf.hosten.model.dao.ICategoriaQuartoDAO;
import br.cefetmg.inf.hosten.model.dao.IItemConfortoDAO;
import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import br.cefetmg.inf.hosten.model.domain.ItemConforto;
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
public class ItemConfortoDAOTest extends BaseTest<ItemConforto, IItemConfortoDAO> {

    public ItemConfortoDAOTest() throws SQLException {
        dao = ItemConfortoDAO.getInstance();
        limpaTabelas();
    }

    @Override
    final void limpaTabelas() throws SQLException {
        ICategoriaQuartoDAO cqDAO = CategoriaQuartoDAO.getInstance();
        for (CategoriaQuarto cq : cqDAO.buscaTodos()) {
            Iterator<ItemConforto> itIc = cq.getItemConfortos().iterator();
            while (itIc.hasNext()) {
                cq.removeItemConforto(itIc.next());
            }
            cqDAO.deleta(cq);
        }
        for (ItemConforto h : dao.buscaTodos()) {
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
    ItemConforto getNewInstanciaDomain(int i) {
        ItemConforto h = new ItemConforto(
                // cod
                String.valueOf(i),
                // des
                "Item de Conforto n° " + i);
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
     * Test of getInstance method, of class ItemConfortoDAO.
     */
    @Test
    public void testGetInstance() {
        imprimeTituloTeste("getInstance");

        if (dao == ItemConfortoDAO.getInstance()) {
            concluiComSucesso();
        } else {
            concluiComFalha(null);
        }
    }

    /**
     * Test of adiciona method, of class ItemConfortoDAO.
     */
    @Test
    public void testAdiciona() {
        imprimeTituloTeste("adiciona");

        ItemConforto ic = getNewInstanciaDomain(1);
        imprimeConteudoObjeto(ic, "ic");
        try {
            dao.adiciona(ic);
            concluiComSucesso();
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of buscaPorPk method, of class ItemConfortoDAO.
     */
    @Test
    public void testBuscaPorPk() {
        imprimeTituloTeste("buscaPorPk");

        ItemConforto ic = getNewInstanciaDomain(2);
        imprimeConteudoObjeto(ic, "ic=");
        try {
            dao.adiciona(ic);

            ItemConforto ic2 = dao.buscaPorPk(ic.getCodItem());
            imprimeConteudoObjeto(ic2, "ic2=");

            if (ic.equals(ic2)) {
                concluiComSucesso();
            }
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of buscaPorColuna method, of class ItemConfortoDAO.
     */
    @Test
    public void testBuscaPorColuna() {
        imprimeTituloTeste("buscaPorColuna");

        ItemConforto ic = getNewInstanciaDomain(3);
        imprimeConteudoObjeto(ic, "ic=");
        try {
            dao.adiciona(ic);

            List<ItemConforto> ics = dao.buscaPorColuna(ic.getCodItem(), "codItem");
            if (ics.get(0).equals(ic)) {
                concluiComSucesso();
            }
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of buscaTodos method, of class ItemConfortoDAO.
     */
    @Test
    public void testBuscaTodos() {
        imprimeTituloTeste("buscaTodos");

        ItemConforto ic = getNewInstanciaDomain(4);
        imprimeConteudoObjeto(ic, "ic=");
        ItemConforto ic2 = getNewInstanciaDomain(5);
        imprimeConteudoObjeto(ic2, "ic2=");
        try {
            dao.adiciona(ic);
            dao.adiciona(ic2);

            List<ItemConforto> ics = dao.buscaTodos();
            int i = 0;
            for (ItemConforto icBusca : ics) {
                if (icBusca.equals(ic) || icBusca.equals(ic2)) {
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
     * Test of atualiza method, of class ItemConfortoDAO.
     */
    @Test
    public void testAtualiza() {
        imprimeTituloTeste("atualiza");

        ItemConforto ic = getNewInstanciaDomain(6);
        imprimeConteudoObjeto(ic, "ic=");
        ItemConforto ic2 = getNewInstanciaDomain(7);
        imprimeConteudoObjeto(ic2, "ic2=");
        try {
            dao.adiciona(ic);

            dao.atualiza(ic.getCodItem(), ic2);
            imprimeConteudoObjeto(ic, "ic depois do Update=");

            concluiComSucesso();
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }

    /**
     * Test of deleta method, of class ItemConfortoDAO.
     */
    @Test
    public void testDeleta() {
        imprimeTituloTeste("deleta");

        ItemConforto ic = getNewInstanciaDomain(8);
        imprimeConteudoObjeto(ic, "ic=");
        try {
            dao.adiciona(ic);

            ItemConforto hBuscado = dao.buscaPorPk(ic.getCodItem());
            imprimeConteudoObjeto(hBuscado, "Item encontrado=");

            dao.deleta(ic);
            ItemConforto hBuscado2 = dao.buscaPorPk(ic.getCodItem());
            if (hBuscado2 == null) {
                concluiComSucesso();
            }
        } catch (SQLException e) {
            concluiComFalha(e);
        }
    }
}
