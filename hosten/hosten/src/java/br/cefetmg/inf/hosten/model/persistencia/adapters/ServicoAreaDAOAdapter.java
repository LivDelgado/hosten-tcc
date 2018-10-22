package br.cefetmg.inf.hosten.model.persistencia.adapters;

import br.cefetmg.inf.hosten.model.persistencia.jdbc.*;
import br.cefetmg.inf.hosten.model.persistencia.interfaces.IServicoAreaDAO;
import br.cefetmg.inf.hosten.model.domain.ServicoArea;
import java.sql.SQLException;
import java.util.List;

public class ServicoAreaDAOAdapter implements IServicoAreaDAO{

    private static IServicoAreaDAO instancia;

    public static synchronized IServicoAreaDAO getInstance() {
        if (instancia == null) {
            instancia = ServicoAreaDAO.getInstance();
        }
        return instancia;
    }

    @Override
    public boolean adicionaServicoArea(ServicoArea servicoArea) 
            throws SQLException {
        return instancia.adicionaServicoArea(servicoArea);
    }

    @Override
    public List<ServicoArea> buscaServicoArea(Object dadoBusca, String coluna) 
            throws SQLException {
        return instancia.buscaServicoArea(dadoBusca, coluna);
    }

    @Override
    public List<ServicoArea> buscaTodosServicoAreas() throws SQLException {
        return instancia.buscaTodosServicoAreas();
    }

    @Override
    public boolean atualizaServicoArea(
            Object pK, 
            ServicoArea servicoAreaAtualizado) throws SQLException {
        return instancia.atualizaServicoArea(pK, servicoAreaAtualizado);
    }

    @Override
    public boolean deletaServicoArea(Object pK) throws SQLException {
        return instancia.deletaServicoArea(pK);
    }
}
