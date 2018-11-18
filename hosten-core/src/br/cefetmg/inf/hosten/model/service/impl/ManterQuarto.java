package br.cefetmg.inf.hosten.model.service.impl;

import br.cefetmg.inf.hosten.model.domain.Quarto;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoHospedagem;
import br.cefetmg.inf.hosten.model.persistence.adapters.QuartoDaoAdapter;
import br.cefetmg.inf.hosten.model.persistence.adapters.QuartoHospedagemDaoAdapter;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.service.IManterQuarto;
import br.cefetmg.inf.hosten.model.persistence.interfaces.rel.IQuartoHospedagemDao;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IQuartoDao;

public class ManterQuarto implements IManterQuarto {

    IQuartoDao objetoDAO;

    public ManterQuarto() {
        objetoDAO = QuartoDaoAdapter.getInstance();
    }

    @Override
    public boolean inserir(Quarto quarto)
            throws NegocioException, SQLException {
        // testa tamanho dos campos
        if (quarto.getNroQuarto() <= 0 || quarto.getNroQuarto() > 32767) {
            throw new NegocioException("Número do quarto inválido.");
        }

        // confere se já existe algum quarto com aquele número
        List<Quarto> quartosPesquisados
                = listar(quarto.getNroQuarto(), "nroQuarto");

        if (quartosPesquisados.isEmpty()) {
            // pode inserir
            boolean testeRegistro = objetoDAO.adiciona(quarto);
            return testeRegistro;
        } else {
            // tem quarto com o mesmo número
            throw new NegocioException("Número do quarto repetido!");
        }
    }

    @Override
    public boolean alterar(short codRegistro, Quarto quarto)
            throws NegocioException, SQLException {
        // testa tamanho dos campos
        if (quarto.getNroQuarto() <= 0 || quarto.getNroQuarto() > 32767) {
            throw new NegocioException("Número do quarto inválido.");
        }

        boolean testeRegistro = objetoDAO.atualiza(codRegistro, quarto);

        return testeRegistro;
    }

    @Override
    public boolean excluir(short codRegistro) throws NegocioException, SQLException {
        //
        // confere se o quarto está ocupado
        List<Quarto> quartosPesquisados = listar(codRegistro, "nroQuarto");
        if (quartosPesquisados.isEmpty()) {
            throw new NegocioException("Quarto não encontrado!");
        } else {
            if (quartosPesquisados.get(0).getIdtOcupado()) {
                throw new NegocioException("Não é possível excluir o quarto"
                        + codRegistro + ". Ele está ocupado!");
            } else {
                //
                // testa se o nroQuarto está sendo usado em QuartoHospedagem
                //
                IQuartoHospedagemDao relDAO = QuartoHospedagemDaoAdapter.getInstance();
                List<QuartoHospedagem> listaREL = relDAO.buscaPorColuna(codRegistro, "nroQuarto");
                if (!listaREL.isEmpty()) {
                    throw new NegocioException(
                            "Não é possível excluir o quarto"
                            + codRegistro
                            + ". Há registros de hospedagem "
                            + "relacionados a ele!");
                } else {
                    return objetoDAO.deleta(codRegistro);
                }
            }
        }
    }

    @Override
    public List<Quarto> listar(Object dadoBusca, String coluna) throws NegocioException, SQLException {
        //
        // confere se foi digitado um dado busca e se a coluna é válida
        //
        if (dadoBusca != null) {
            return objetoDAO.buscaPorColuna(dadoBusca, coluna);
        } else {
            throw new NegocioException("Nenhum quarto buscado!");
        }
    }

    @Override
    public List<Quarto> listarTodos()
            throws NegocioException, SQLException {
        return objetoDAO.buscaTodos();
    }

    @Override
    public int buscaUltimoRegistroRelacionadoAoQuarto(short nroQuarto) throws NegocioException, SQLException {
        if (nroQuarto > 0) {
            IQuartoHospedagemDao qhDao = QuartoHospedagemDaoAdapter.getInstance();
            return qhDao.buscaUltimoRegistro(nroQuarto);
        } else {
            throw new NegocioException("Número do quarto inválido!");
        }
    }
}
