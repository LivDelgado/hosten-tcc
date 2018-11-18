package br.cefetmg.inf.hosten.model.service.impl;

import br.cefetmg.inf.hosten.model.domain.Servico;
import br.cefetmg.inf.hosten.model.domain.ServicoArea;
import br.cefetmg.inf.hosten.model.persistence.adapters.ServicoAreaDaoAdapter;
import br.cefetmg.inf.hosten.model.persistence.adapters.ServicoDaoAdapter;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.service.IManterServicoArea;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IServicoAreaDao;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IServicoDao;

public class ManterServicoArea implements IManterServicoArea {

    IServicoAreaDao objetoDAO;

    public ManterServicoArea() {
        objetoDAO = ServicoAreaDaoAdapter.getInstance();
    }

    @Override
    public boolean inserir(ServicoArea servicoArea)
            throws NegocioException, SQLException {
        // testa tamanho dos campos
        if (servicoArea.getCodServicoArea().length() != 3) {
            throw new NegocioException("O código da área de serviço deve ter 3 caracteres.");
        }
        if (servicoArea.getNomServicoArea().length() > 40) {
            throw new NegocioException("O nome da área de serviço ultrapassou os 40 caracteres máximos permitidos.");
        }

        // pesquisa para saber se há alguma área já 
        // inserida que possui o mesmo código
        List<ServicoArea> servicoAreasPesquisadas
                = listar(servicoArea.getCodServicoArea(), "codServicoArea");

        if (servicoAreasPesquisadas.isEmpty()) {
            // não tem área com o mesmo código

            // busca se tem área com o mesmo nome
            List<ServicoArea> servicoAreasPesquisadas1
                    = listar(servicoArea.getNomServicoArea(), "nomServicoArea");
            if (servicoAreasPesquisadas1.isEmpty()) {
                // não tem área com o mesmo nome
                // pode inserir

                // adiciona a área
                boolean testeRegistro = objetoDAO.adiciona(servicoArea);
                return testeRegistro;
            } else {
                // tem área com o mesmo nome
                throw new NegocioException("Nome da área de serviço repetido!");
            }
        } else {
            // tem área com o mesmo código
            throw new NegocioException("Código da área de serviço repetido!");
        }
    }

    @Override
    public boolean alterar(String codRegistro, ServicoArea servicoArea)
            throws NegocioException, SQLException {
        // testa tamanho dos campos
        if (servicoArea.getCodServicoArea().length() != 3) {
            throw new NegocioException("O código da área de serviço deve ter 3 caracteres.");
        }
        if (servicoArea.getNomServicoArea().length() > 40) {
            throw new NegocioException("O nome da área de serviço ultrapassou os 40 caracteres máximos permitidos.");
        }

        List<ServicoArea> buscaRegistroAntigo = listar(codRegistro, "codServicoArea");
        ServicoArea registroAntigo = buscaRegistroAntigo.get(0);

        // pesquisa para saber se há alguma área já 
        // inserida que possui o mesmo código
        List<ServicoArea> servicoAreasPesquisadas
                = listar(servicoArea.getCodServicoArea(), "codServicoArea");

        if (servicoAreasPesquisadas.isEmpty() || (codRegistro.equals(servicoArea.getCodServicoArea()))) {
            // não tem área com o mesmo código

            // busca se tem área com o mesmo nome
            List<ServicoArea> servicoAreasPesquisadas1
                    = listar(servicoArea.getNomServicoArea(), "nomServicoArea");
            if (servicoAreasPesquisadas1.isEmpty()
                    || (registroAntigo.getNomServicoArea().equals(servicoArea.getNomServicoArea()))) {
                // não tem área com o mesmo nome
                // pode alterar
                boolean testeRegistro = objetoDAO.atualiza(codRegistro, servicoArea);
                return testeRegistro;
            } else {
                // tem área com o mesmo nome
                throw new NegocioException("Nome da área de serviço repetido!");
            }
        } else {
            // tem área com o mesmo código
            throw new NegocioException("Código da área de serviço repetido!");
        }
    }

    @Override
    public boolean excluir(String codRegistro)
            throws NegocioException, SQLException {
        List<ServicoArea> servicoAreasPesquisadas
                = listar(codRegistro, "codServicoArea");
        if (servicoAreasPesquisadas.isEmpty()) {
            throw new NegocioException("Essa área de serviço não existe!");
        }

        // confere se há algum serviço naquela área
        IServicoDao servicoDAO = ServicoDaoAdapter.getInstance();
        List<Servico> listaServicos = servicoDAO.buscaPorColuna(codRegistro, "codServicoArea");
        if (!listaServicos.isEmpty()) {
            throw new NegocioException(
                    "Não é possível excluir essa área de serviço. Há "
                    + listaServicos.size() + " serviços nela.");
        }

        // deleta a categoria
        return objetoDAO.deleta(codRegistro);
    }

    @Override
    public List<ServicoArea> listar(Object dadoBusca, String coluna)
            throws NegocioException, SQLException {
        //
        // confere se foi digitado um dado busca e se a coluna é válida
        //
        if (dadoBusca != null) {
            return objetoDAO.buscaPorColuna(dadoBusca, coluna);
        } else {
            throw new NegocioException("Nenhuma área de serviço buscada!");
        }
    }

    @Override
    public List<ServicoArea> listarTodos() throws NegocioException, SQLException {
        return objetoDAO.buscaTodos();
    }
}
