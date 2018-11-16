package br.cefetmg.inf.hosten.model.service.impl;

import br.cefetmg.inf.hosten.model.persistence.jdbc.CargoDao;
import br.cefetmg.inf.hosten.model.persistence.jdbc.ProgramaDao;
import br.cefetmg.inf.hosten.model.persistence.jdbc.UsuarioDao;
import br.cefetmg.inf.hosten.model.persistence.jdbc.rel.CargoProgramaDao;
import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.hosten.model.domain.Usuario;
import br.cefetmg.inf.hosten.model.domain.rel.CargoPrograma;
import br.cefetmg.inf.util.exception.NegocioException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import br.cefetmg.inf.hosten.model.domain.Programa;
import br.cefetmg.inf.hosten.model.persistence.adapters.CargoDaoAdapter;
import br.cefetmg.inf.hosten.model.persistence.adapters.CargoProgramaDaoAdapter;
import br.cefetmg.inf.hosten.model.persistence.adapters.ProgramaDaoAdapter;
import br.cefetmg.inf.hosten.model.persistence.adapters.UsuarioDaoAdapter;
import br.cefetmg.inf.hosten.model.service.IManterCargo;
import br.cefetmg.inf.hosten.model.persistence.interfaces.rel.ICargoProgramaDao;
import br.cefetmg.inf.hosten.model.persistence.interfaces.ICargoDao;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IProgramaDao;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IUsuarioDao;

public class ManterCargo implements IManterCargo {

    ICargoDao objetoDAO;

    public ManterCargo() {
        objetoDAO = CargoDaoAdapter.getInstance();
    }

    @Override
    public boolean inserir(Cargo cargo, List<String> listaProgramas)
            throws NegocioException, SQLException {
        // testa tamanho dos campos
        if (cargo.getCodCargo().length() != 3) {
            throw new NegocioException("O código do cargo deve ter 3 caracteres.");
        }
        if (cargo.getNomCargo().length() > 40) {
            throw new NegocioException("O nome do cargo ultrapassou os 40 caracteres máximos permitidos.");
        }

        // pesquisa para saber se há algum cargo já 
        // inserido que possui o mesmo código
        List<Cargo> cargosPesquisados
                = listar(cargo.getCodCargo(), "codCargo");

        if (cargosPesquisados.isEmpty()) {
            // não tem cargo com o mesmo código

            // busca se tem cargo com o mesmo nome
            List<Cargo> cargosPesquisados1
                    = listar(cargo.getNomCargo(), "nomCargo");
            if (cargosPesquisados1.isEmpty()) {
                // não tem cargo com o mesmo nome
                // pode inserir
                if (listaProgramas.isEmpty()) {
                    throw new NegocioException("Não é possível adicionar um cargo que não tem acesso a nenhuma tela.");
                }

                // adiciona o cargo
                boolean testeRegistro = objetoDAO.adicionaCargo(cargo);
                // cria os relacionamentos
                ICargoProgramaDao relDAO = CargoProgramaDaoAdapter.getInstance();
                for (String codPrograma : listaProgramas) {
                    CargoPrograma rel = new CargoPrograma(codPrograma, cargo.getCodCargo());
                    relDAO.adiciona(rel);
                }

                return testeRegistro;
            } else {
                // tem cargo com o mesmo nome
                throw new NegocioException("Nome do cargo repetido!");
            }
        } else {
            // tem cargo com o mesmo código
            throw new NegocioException("Código do cargo repetido!");
        }
    }

    @Override
    public boolean alterar(String codRegistro, Cargo cargo, List<String> listaProgramas)
            throws NegocioException, SQLException {

        // testa tamanho dos campos
        if (cargo.getCodCargo().length() != 3) {
            throw new NegocioException("O código do cargo deve ter 3 caracteres.");
        }
        if (cargo.getNomCargo().length() > 40) {
            throw new NegocioException("O nome do cargo ultrapassou os 40 caracteres máximos permitidos.");
        }

        List<Cargo> buscaRegistroAntigo = listar(codRegistro, "codCargo");
        Cargo registroAntigo = buscaRegistroAntigo.get(0);
        
        // pesquisa para saber se há algum cargo já 
        // inserido que possui o mesmo código
        List<Cargo> cargosPesquisados
                = listar(cargo.getCodCargo(), "codCargo");

        if (cargosPesquisados.isEmpty() || (codRegistro.equals(cargo.getCodCargo()))) {
            // não tem cargo com o mesmo código

            // busca se tem cargo com o mesmo nome
            List<Cargo> cargosPesquisados1
                    = listar(cargo.getNomCargo(), "nomCargo");
            if (cargosPesquisados1.isEmpty() 
                    || (registroAntigo.getNomCargo().equals(cargo.getNomCargo())) ) {
                // não tem cargo com o mesmo nome
                // pode alterar

                if (listaProgramas.isEmpty()) {
                    throw new NegocioException("Não é possível deixar um cargo "
                            + "sem acesso a nenhuma tela.");
                }

                // atualiza o cargo
                boolean testeRegistro = objetoDAO.atualizaCargo(codRegistro, cargo);
                if (testeRegistro) {
                    // atualiza os relacionamentos
                    ICargoProgramaDao relDAO = CargoProgramaDaoAdapter.getInstance();
                    // deleta todos os relacionamentos com aquele cargo
                    List<CargoPrograma> listaREL = relDAO.busca(
                            cargo.getCodCargo(),
                            "codCargo");
                    if (!listaREL.isEmpty()) {
                        relDAO.deletaPorColuna(cargo.getCodCargo(), "codCargo");
                    }
                    // cria os relacionamentos
                    for (String codPrograma : listaProgramas) {
                        CargoPrograma rel = new CargoPrograma(
                                codPrograma,
                                cargo.getCodCargo());
                        relDAO.adiciona(rel);
                    }
                    return true;
                } else {
                    return false;
                }
            } else {
                // tem item com a mesma descrição
                throw new NegocioException("Nome do cargo repetido!");
            }
        } else {
            // tem categoria com o mesmo código
            throw new NegocioException("Código do cargo repetido!");
        }
    }

    @Override
    public boolean excluir(String codRegistro)
            throws NegocioException, SQLException {
        // testar se tem usuario com esse cargo
        IUsuarioDao dao = UsuarioDaoAdapter.getInstance();
        List<Usuario> listaUsuarios = null;
        try {
            listaUsuarios = dao.buscaUsuario(codRegistro, "codCargo");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            throw new NegocioException("Erro! Não foi possível excluir o cargo");
        }
        if (listaUsuarios.isEmpty()) {
            ICargoProgramaDao relDAO = CargoProgramaDaoAdapter.getInstance();
            // deleta todos os relacionamentos com aquele cargo
            List<CargoPrograma> listaREL = relDAO.busca(codRegistro, "codCargo");
            if (!listaREL.isEmpty()) {
                relDAO.deletaPorColuna(codRegistro, "codCargo");
            }
            return objetoDAO.deletaCargo(codRegistro);
        } else {
            throw new NegocioException(
                    "Não é possível excluir o cargo"
                            + codRegistro + ". Há usuários com esse cargo!");
        }
    }

    @Override
    public List<Cargo> listar(Object dadoBusca, String coluna)
            throws NegocioException, SQLException {
        //
        // confere se foi digitado um dado busca e se a coluna é válida
        //
        if (dadoBusca != null) {
            return objetoDAO.buscaCargo(dadoBusca, coluna);
        } else {
            throw new NegocioException("Nenhum cargo buscado!");
        }
    }

    @Override
    public List<Cargo> listarTodos()
            throws NegocioException, SQLException {
        return objetoDAO.buscaTodosCargos();
    }

    @Override
    public List<Programa> listarProgramasRelacionados(String codCargo) 
            throws NegocioException, SQLException {
        if(codCargo != null) {
            ICargoProgramaDao cargoProgramaDAO = CargoProgramaDaoAdapter.getInstance();
            List<Programa> lista = cargoProgramaDAO.buscaProgramasRelacionados(codCargo); 
            
            return lista;
        } else {
            throw new NegocioException("O codCargo passado é inválido");
        }
    }

    @Override
    public List<Programa> listarTodosProgramas() 
            throws NegocioException, SQLException {
        IProgramaDao programaDAO = ProgramaDaoAdapter.getInstance();
        return programaDAO.buscaTodosProgramas();
    }
}