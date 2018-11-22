package br.cefetmg.inf.hosten.model.service.impl;

import br.cefetmg.inf.hosten.model.domain.CategoriaQuarto;
import br.cefetmg.inf.hosten.model.domain.Hospedagem;
import br.cefetmg.inf.hosten.model.domain.Hospede;
import br.cefetmg.inf.hosten.model.domain.Quarto;
import br.cefetmg.inf.hosten.model.domain.idcomposto.QuartoHospedagemId;
import br.cefetmg.inf.hosten.model.domain.rel.Despesa;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoEstado;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoHospedagem;
import br.cefetmg.inf.hosten.model.persistence.adapters.CategoriaQuartoDaoAdapter;
import br.cefetmg.inf.hosten.model.persistence.adapters.HospedagemDaoAdapter;
import br.cefetmg.inf.hosten.model.persistence.adapters.QuartoDaoAdapter;
import br.cefetmg.inf.hosten.model.persistence.adapters.QuartoEstadoDaoAdapter;
import br.cefetmg.inf.hosten.model.persistence.adapters.QuartoHospedagemDaoAdapter;
import br.cefetmg.inf.hosten.model.service.IControlarDespesas;
import br.cefetmg.inf.hosten.model.service.IControlarHospedagem;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import br.cefetmg.inf.hosten.model.persistence.interfaces.rel.IQuartoHospedagemDao;
import br.cefetmg.inf.hosten.model.persistence.interfaces.ICategoriaQuartoDao;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IHospedagemDao;
import br.cefetmg.inf.hosten.model.persistence.interfaces.IQuartoDao;
import br.cefetmg.inf.hosten.model.persistence.interfaces.rel.IQuartoEstadoDao;
import java.math.BigDecimal;
import java.util.Date;

public class ControlarHospedagem implements IControlarHospedagem {

    @Override
    public boolean efetuarCheckIn(short nroQuarto, String codCPF, short diasEstadia, short nroAdultos, short nroCriancas) {
        // data check-in
        Date dataAtual = new Date();
        Date dataCheckIn = new Timestamp(dataAtual.getTime());

        // data check-out
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.format(dataAtual);
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(dataAtual);
        calendario.add(Calendar.DATE, +diasEstadia);
        Date dataCheckout = new Timestamp(calendario.getTimeInMillis());

        try {
            // vlrDiaria
            IQuartoDao quartoDAO = QuartoDaoAdapter.getInstance();
            List<Quarto> listaQuarto;

            listaQuarto = quartoDAO.buscaPorColuna(nroQuarto, "nroQuarto");

            String codCategoria = listaQuarto.get(0).getCategoria().getCodCategoria();

            ICategoriaQuartoDao categoriaDAO = CategoriaQuartoDaoAdapter.getInstance();
            List<CategoriaQuarto> categorias = categoriaDAO.buscaPorColuna(codCategoria, "codCategoria");
            double valorDiaria = categorias.get(0).getVlrDiaria().doubleValue();

            double valorTotal = valorDiaria * diasEstadia;

            // ----------------------------------------------------------------------------------------------------------------------------------------
            // realiza a operação de check-in
            Hospedagem hosp = new Hospedagem(
                    new java.sql.Date(dataCheckIn.getTime()),
                    new java.sql.Date(dataCheckout.getTime()), 
                    BigDecimal.valueOf(valorTotal));
            
            hosp.setHospede(new Hospede(codCPF));
            IHospedagemDao hospDAO = HospedagemDaoAdapter.getInstance();
            hospDAO.adiciona(hosp);

            Hospedagem hospEncontrada = hospDAO.buscaHospedagem(hosp).get(0);

            IQuartoHospedagemDao quartoHosp = QuartoHospedagemDaoAdapter.getInstance();
            int seqHospedagem = hospEncontrada.getSeqHospedagem();
            QuartoHospedagem objAdicionar = new QuartoHospedagem(
                    new QuartoHospedagemId(seqHospedagem, nroQuarto), nroAdultos, nroCriancas, BigDecimal.valueOf(valorDiaria));
            boolean testeAddQuarto = quartoHosp.adiciona(objAdicionar);

            // atualiza o idtOcupado do quarto pra ocupado
            Quarto quartoAtualizado = listaQuarto.get(0);
            quartoAtualizado.setIdtOcupado(true);
            boolean testeAtualizaQuarto = quartoDAO.atualiza(nroQuarto, quartoAtualizado);

            return (testeAddQuarto && testeAtualizaQuarto);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public int efetuarCheckout(short nroQuarto) {

        IQuartoDao quartoDAO = QuartoDaoAdapter.getInstance();
        int seqHospedagem = 0;
        try {
            IQuartoHospedagemDao qhDao = QuartoHospedagemDaoAdapter.getInstance();
            
            seqHospedagem = qhDao.buscaUltimoRegistro(nroQuarto);

            Date dataAtual = new Date();
            Timestamp dataCheckout = new Timestamp(dataAtual.getTime());

            IHospedagemDao hospDAO = HospedagemDaoAdapter.getInstance();
            List<Hospedagem> hospBuscada = hospDAO.buscaPorColuna(seqHospedagem, "seqHospedagem");

            Hospedagem hospedagemAtualizado = hospBuscada.get(0);
            hospedagemAtualizado.setDatCheckout(new java.sql.Date(dataCheckout.getTime()));

            // faz o cálculo das despesas
            IControlarDespesas controlarDespesas = new ControlarDespesas();
            List<Despesa> listaDespesas = 
                    controlarDespesas.listar(seqHospedagem, nroQuarto);
            Double vlrTotal = 0.0;
            if (listaDespesas != null) {
                for (Despesa despesa : listaDespesas) {
                    // um parágrafo para cada item/servico consumido
                    int qtdServico = despesa.getQtdConsumo();
                    Double vlrServico = despesa.getVlrUnit().doubleValue();

                    vlrTotal += vlrServico * qtdServico;
                }
            }
            Date datCheckIn = hospedagemAtualizado.getDatCheckin();
            long msDiferenca = (dataCheckout.getTime()) - (datCheckIn.getTime());
            long segundos = msDiferenca / 1000;
            long minutos = segundos / 60;
            long horas = minutos / 60;
            long dias = horas / 24;
            
            Double valorDiaria = 0.0;
            ICategoriaQuartoDao categoriaDAO = CategoriaQuartoDaoAdapter.getInstance();
            valorDiaria = categoriaDAO.buscaPorColuna(
                    quartoDAO.buscaPorColuna(
                            nroQuarto, 
                            "nroQuarto").get(0).getCategoria(), 
                    "codCategoria"
            ).get(0).getVlrDiaria().doubleValue();
            
            if (dias < 1)
                dias = 1;
            Double valorDiarias = dias * valorDiaria;
            vlrTotal += valorDiarias;
            
            hospedagemAtualizado.setVlrPago(BigDecimal.valueOf(vlrTotal));

            // atualiza a data de check-out da hospedagem
            hospDAO.atualiza(seqHospedagem, hospedagemAtualizado);

            List<Quarto> listaQuarto;
            listaQuarto = quartoDAO.buscaPorColuna(nroQuarto, "nroQuarto");
            Quarto quartoAtualizado = listaQuarto.get(0);
            quartoAtualizado.setIdtOcupado(false);
            quartoDAO.atualiza(nroQuarto, quartoAtualizado);

            // retorna o seqHospedagem 
            return seqHospedagem;
        } catch (SQLException | NegocioException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<QuartoEstado> listarTodos() throws NegocioException {
        IQuartoEstadoDao dao = QuartoEstadoDaoAdapter.getInstance();
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
        return (HospedagemDaoAdapter.getInstance().buscaPorColuna(seqHospedagem, "seqHospedagem").get(0));
    }

    @Override
    public QuartoHospedagem buscaQuartoHospedagem(int seqHospedagem) throws SQLException{
        return (QuartoHospedagemDaoAdapter.getInstance().buscaPorColuna(seqHospedagem, "seqHospedagem").get(0));
    }
}
