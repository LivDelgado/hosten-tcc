package br.cefetmg.inf.hosten.model.service.impl;

import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import br.cefetmg.inf.hosten.model.domain.Hospedagem;
import br.cefetmg.inf.hosten.model.domain.Quarto;
import br.cefetmg.inf.hosten.model.domain.rel.Despesa;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoEstado;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoHospedagem;
import br.cefetmg.inf.hosten.model.persistence.adapters.CategoriaQuartoDaoAdapter;
import br.cefetmg.inf.hosten.model.persistence.adapters.HospedagemDaoAdapter;
import br.cefetmg.inf.hosten.model.persistence.adapters.QuartoDaoAdapter;
import br.cefetmg.inf.hosten.model.persistence.adapters.QuartoHospedagemDaoAdapter;
import br.cefetmg.inf.hosten.model.service.IControlarDespesas;
import br.cefetmg.inf.hosten.model.service.IControlarHospedagem;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.rel.IQuartoHospedagemDao;
import br.cefetmg.inf.hosten.model.persistence.interfaces.ICategoriaQuartoDao;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IHospedagemDao;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IQuartoDao;

public class ControlarHospedagem implements IControlarHospedagem {

    @Override
    public boolean efetuarCheckIn(String nroQuarto, String codCPF, int diasEstadia, int nroAdultos, int nroCriancas) {
        // data check-in
        Date dataAtual = new Date();
        Timestamp dataCheckIn = new Timestamp(dataAtual.getTime());

        // data check-out
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.format(dataAtual);
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(dataAtual);
        calendario.add(Calendar.DATE, +diasEstadia);
        Timestamp dataCheckOut = new Timestamp(calendario.getTimeInMillis());

        try {
            // vlrDiaria
            IQuartoDao quartoDAO = QuartoDaoAdapter.getInstance();
            List<Quarto> listaQuarto;

            listaQuarto = quartoDAO.buscaQuarto(Integer.parseInt(nroQuarto), "nroQuarto");

            String codCategoria = listaQuarto.get(0).getCodCategoria();

            ICategoriaQuartoDao categoriaDAO = CategoriaQuartoDaoAdapter.getInstance();
            List<CategoriaQuarto> categorias = categoriaDAO.buscaCategoriaQuarto(codCategoria, "codCategoria");
            Double valorDiaria = categorias.get(0).getVlrDiaria();

            double valorTotal = valorDiaria * diasEstadia;

            // ----------------------------------------------------------------------------------------------------------------------------------------
            // realiza a operação de check-in
            Hospedagem hosp = new Hospedagem(dataCheckIn, dataCheckOut, valorTotal, codCPF);
            IHospedagemDao hospDAO = HospedagemDaoAdapter.getInstance();

            boolean testeAddHospedagem = hospDAO.adicionaHospedagem(hosp);

            List<Hospedagem> hospEncontrada = hospDAO.busca(hosp);

            IQuartoHospedagemDao quartoHosp = QuartoHospedagemDaoAdapter.getInstance();
            int seqHospedagem = hospEncontrada.get(0).getSeqHospedagem();
            QuartoHospedagem objAdicionar = new QuartoHospedagem(seqHospedagem, Integer.parseInt(nroQuarto), nroAdultos, nroCriancas, valorDiaria);
            boolean testeAddQuarto = quartoHosp.adiciona(objAdicionar);

            // atualiza o idtOcupado do quarto pra ocupado
            Quarto quartoAtualizado = listaQuarto.get(0);
            quartoAtualizado.setIdtOcupado(true);
            boolean testeAtualizaQuarto = quartoDAO.atualizaQuarto(Integer.parseInt(nroQuarto), quartoAtualizado);

            return (testeAddQuarto && testeAtualizaQuarto);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public int efetuarCheckOut(String nroQuarto) {
        int intNroQuarto = Integer.parseInt(nroQuarto);

        IQuartoDao quartoDAO = QuartoDaoAdapter.getInstance();
        int seqHospedagem = 0;
        try {
            seqHospedagem = quartoDAO.buscaUltimoRegistroRelacionadoAoQuarto(intNroQuarto);

            Date dataAtual = new Date();
            Timestamp dataCheckOut = new Timestamp(dataAtual.getTime());

            IHospedagemDao hospDAO = HospedagemDaoAdapter.getInstance();
            List<Hospedagem> hospBuscada
                    = hospDAO.buscaHospedagem(seqHospedagem, "seqHospedagem");

            Hospedagem hospedagemAtualizado = hospBuscada.get(0);
            hospedagemAtualizado.setDatCheckOut(dataCheckOut);

            // faz o cálculo das despesas
            IControlarDespesas controlarDespesas = new ControlarDespesas();
            List<Despesa> listaDespesas = controlarDespesas.listar(
                    seqHospedagem, Integer.parseInt(nroQuarto)
            );
            Double vlrTotal = 0.0;
            if (listaDespesas != null) {
                for (Despesa despesa : listaDespesas) {
                    // um parágrafo para cada item/servico consumido
                    int qtdServico = despesa.getQtdConsumo();
                    String desServico = despesa.getDesServico();
                    Double vlrServico = despesa.getVlrUnit();

                    vlrTotal += vlrServico * qtdServico;
                }
            }
            Timestamp datCheckIn = hospedagemAtualizado.getDatCheckIn();
            long msDiferenca = (dataCheckOut.getTime()) - (datCheckIn.getTime());
            long segundos = msDiferenca / 1000;
            long minutos = segundos / 60;
            long horas = minutos / 60;
            long dias = horas / 24;
            
            Double valorDiaria = 0.0;
            ICategoriaQuartoDao categoriaDAO = CategoriaQuartoDaoAdapter.getInstance();
            valorDiaria = categoriaDAO.buscaCategoriaQuarto(
                    quartoDAO.buscaQuarto(Integer.parseInt(nroQuarto), "nroQuarto").get(0).getCodCategoria(), 
                    "codCategoria"
            ).get(0).getVlrDiaria();
            
            if (dias < 1)
                dias = 1;
            Double valorDiarias = dias * valorDiaria;
            vlrTotal += valorDiarias;
            
            hospedagemAtualizado.setVlrPago(vlrTotal);

            // atualiza a data de check-out da hospedagem
            hospDAO.atualizaHospedagemPorPk(seqHospedagem, hospedagemAtualizado);

            List<Quarto> listaQuarto;
            listaQuarto = quartoDAO.buscaQuarto(Integer.parseInt(nroQuarto), "nroQuarto");
            Quarto quartoAtualizado = listaQuarto.get(0);
            quartoAtualizado.setIdtOcupado(false);
            quartoDAO.atualizaQuarto(Integer.parseInt(nroQuarto), quartoAtualizado);

            // retorna o seqHospedagem 
            return seqHospedagem;
        } catch (SQLException | NegocioException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<QuartoEstado> listarTodos() throws NegocioException {
        IQuartoHospedagemDao dao = QuartoHospedagemDaoAdapter.getInstance();
        try {
            List<QuartoEstado> lista = dao.buscaTodos();
            if (lista == null) {
                throw new NegocioException("Não existem registros de QuartoHospedagem");
            }
            return lista;
        } catch (SQLException ex) {
            throw new NegocioException("Houve um erro no processamento da requisição");
        }
    }

    @Override
    public Hospedagem buscaHospedagem(int seqHospedagem) throws SQLException {
        return (HospedagemDaoAdapter.getInstance().buscaHospedagem(seqHospedagem, "seqHospedagem").get(0));
    }

    @Override
    public QuartoHospedagem buscaQuartoHospedagem(int seqHospedagem) throws SQLException{
        return (QuartoHospedagemDaoAdapter.getInstance().busca(seqHospedagem, "seqHospedagem").get(0));
    }
}
