package br.cefetmg.inf.hosten.model.service;

import br.cefetmg.inf.hosten.model.domain.Quarto;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.List;

public interface IManterQuarto {

    public boolean inserir(Quarto quarto) throws NegocioException, SQLException;

    public List<Quarto> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException;

    public List<Quarto> listarTodos() throws NegocioException, SQLException;

    public boolean alterar(short codRegistro, Quarto quarto) throws NegocioException, SQLException;

    public boolean excluir(short codRegistro) throws NegocioException, SQLException;

    public int buscaUltimoRegistroRelacionadoAoQuarto(short nroQuarto) throws NegocioException, SQLException;
}
