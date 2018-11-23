package br.cefetmg.inf.hosten.controller.hospedagem;

import br.cefetmg.inf.hosten.controller.context.ContextUtils;
import br.cefetmg.inf.hosten.dist.proxy.ControlarDespesasProxy;
import br.cefetmg.inf.hosten.dist.proxy.ControlarHospedagemProxy;
import br.cefetmg.inf.hosten.dist.proxy.ManterHospedeProxy;
import br.cefetmg.inf.hosten.model.domain.Hospedagem;
import br.cefetmg.inf.hosten.model.domain.Hospede;
import br.cefetmg.inf.hosten.model.domain.rel.Despesa;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoHospedagem;
import br.cefetmg.inf.hosten.model.service.IControlarDespesas;
import br.cefetmg.inf.hosten.model.service.IControlarHospedagem;
import br.cefetmg.inf.hosten.model.service.IManterHospede;
import br.cefetmg.inf.util.exception.NegocioException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import static com.itextpdf.text.Font.FontFamily.HELVETICA;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.sql.Timestamp;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@SessionScoped
@Named(value = "checkMB")
public class CheckMB implements Serializable {

    private short nroQuarto;
    private Hospede hospedeSelecionado;

    private short diasDeEstadia;
    private short nroAdultos;
    private short nroCriancas;

    public void resetaVariaveis() {
        nroQuarto = 0;
        hospedeSelecionado = null;
        diasDeEstadia = 0;
        nroAdultos = 0;
        nroCriancas = 0;
    }

    public void iniciaCheck(short nroQuarto, String operacao) {
        setNroQuarto(nroQuarto);
        try {
            if (operacao.equals("in")) {
                ContextUtils.redireciona("./check-in.jsf");
            } else {
                ContextUtils.redireciona("./check-out.jsf");
            }
        } catch (IOException ex) {
            //
            //
        }
    }

    public short getNroQuarto() {
        return nroQuarto;
    }

    public void setNroQuarto(short nroQuarto) {
        this.nroQuarto = nroQuarto;
    }

    public short getDiasDeEstadia() {
        return diasDeEstadia;
    }

    public void setDiasDeEstadia(short diasDeEstadia) {
        this.diasDeEstadia = diasDeEstadia;
    }

    public short getNroAdultos() {
        return nroAdultos;
    }

    public void setNroAdultos(short nroAdultos) {
        this.nroAdultos = nroAdultos;
    }

    public short getNroCriancas() {
        return nroCriancas;
    }

    public void setNroCriancas(short nroCriancas) {
        this.nroCriancas = nroCriancas;
    }

    public String checkIn() {
        IControlarHospedagem controlarHosp = new ControlarHospedagemProxy();

        boolean testeRegistro = controlarHosp.efetuarCheckIn(
                nroQuarto,
                hospedeSelecionado.getCodCPF(),
                diasDeEstadia,
                nroAdultos,
                nroCriancas);
        if (testeRegistro) {
            ContextUtils.mostrarMensagem("Check-in efetuado", "Operação efetuada com sucesso!", true);
            resetaVariaveis();
            return "sucesso";
        } else {
            ContextUtils.mostrarMensagem("Falha no check-in", "Falha ao executar operação!", true);
            return "falha";
        }

    }

    public Hospede getHospedeSelecionado() {
        return hospedeSelecionado;
    }

    public void setHospedeSelecionado(Hospede hospedeSelecionado) {
        this.hospedeSelecionado = hospedeSelecionado;
    }

    private Document document;

    public void checkout() {
        IControlarHospedagem controlarHosp = new ControlarHospedagemProxy();

        int seqHospedagem = controlarHosp.efetuarCheckout(nroQuarto);

        String nomeArquivo = "fatura.pdf";

        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.responseReset();
        ec.setResponseHeader("Content-Type", "application/pdf");
        ec.setResponseHeader("Content-Disposition", "inline; filename=fatura.pdf");

        try {
            document = new Document(PageSize.A4, 90, 90, 90, 90);
            PdfWriter.getInstance(document, ec.getResponseOutputStream());

            document.open();

            try {
                montaArquivo(seqHospedagem);
            } catch (NegocioException | SQLException ex) {
                //
                //
                //
            }

            document.close();
            fc.responseComplete();

        } catch (DocumentException | IOException ex) {
            //
            //
            //
        }

    }

    private void montaArquivo(int seqHospedagem) throws NegocioException, SQLException, DocumentException {
        IControlarDespesas controlarDespesas = new ControlarDespesasProxy();
        List<Despesa> listaDespesas = controlarDespesas.listar(
                seqHospedagem, nroQuarto
        );

        IControlarHospedagem controlarHospedagem = new ControlarHospedagemProxy();

        Hospedagem hosp = controlarHospedagem.buscaHospedagem(seqHospedagem);
        QuartoHospedagem quartoHosp = controlarHospedagem.buscaQuartoHospedagem(seqHospedagem);

        // monta o arquivo
        DottedLineSeparator separator = new DottedLineSeparator();
        Font fonteUltraViolet = new Font(HELVETICA, 20, 1, new BaseColor(95, 75, 139));
        Font fonteRedViolet = new Font(HELVETICA, 18, 1, new BaseColor(163, 87, 118));
        Font fonteSparklingGrape = new Font(HELVETICA, 14, 1, new BaseColor(125, 63, 124));
        Font fonteMulberry = new Font(HELVETICA, 14, 0, new BaseColor(167, 108, 151));
        Font fonteChateauRose = new Font(HELVETICA, 14, 0, new BaseColor(210, 115, 143));
        Font fonteChateauRoseNegrito = new Font(HELVETICA, 14, 1, new BaseColor(210, 115, 143));

        Chunk c = new Chunk(separator);
        Paragraph p = new Paragraph();

        // título
        p.setFont(fonteUltraViolet);
        p.add("Fatura da hospedagem");
        document.add(p);
        p.clear();

        // subtítulo com o nome do cliente
        String nomeHospede;
        //
        //
//              CODIGO ANTERIOR, COM LISTA DE DESPESAS        
//        	nomeHospede = listaDespesas.get(0).getNomeHospede();
//               
        // buscar o nome do hospede pelo codigo do cpf que ta em hospedagem
        IManterHospede manterHospede = new ManterHospedeProxy();
        Hospede hospede = manterHospede.listar(hosp.getHospede().getCodCPF(), "codCPF").get(0);
        nomeHospede = hospede.getNomHospede();

        //
        //
        p.setFont(fonteRedViolet);
        p.setSpacingBefore(20);
        p.setSpacingAfter(20);
        p.add(nomeHospede);
        document.add(p);
        p.clear();

        Double vlrTotal = 0.0;
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();

        if (listaDespesas != null) {
            for (Despesa despesa : listaDespesas) {
                // um parágrafo para cada item/servico consumido
                int qtdServico = despesa.getQtdConsumo();
                String desServico = despesa.getDesServico();
                Double vlrServico = despesa.getVlrUnit().doubleValue();

//                vlrTotal += vlrServico * qtdServico;
                String strVlrServico = currencyFormatter.format(vlrServico);

                p.setFont(fonteMulberry);
                p.setSpacingAfter(2);
                p.setSpacingBefore(0);
                p.add(String.valueOf(qtdServico));
                p.add(" ");
                p.add(desServico);
                p.add(c);
                p.setFont(fonteSparklingGrape);
                p.add(strVlrServico);
                document.add(p);
                p.clear();
            }
        }

        // parágrafo com as informações da diária
        short nroAdultos = quartoHosp.getNroAdultos();
        short nroCriancas = quartoHosp.getNroCriancas();
        Double vlrDiaria = quartoHosp.getVlrDiaria().doubleValue();

        Timestamp datCheckIn;
        datCheckIn = hosp.getDatCheckin();
        Timestamp datCheckout = hosp.getDatCheckout();
        long msDiferenca = (datCheckout.getTime()) - (datCheckIn.getTime());
        long segundos = msDiferenca / 1000;
        long minutos = segundos / 60;
        long horas = minutos / 60;
        long dias = horas / 24;

        if (dias < 1) {
            dias = 1;
        }

        Double valorDiarias = dias * vlrDiaria;

        // valor total pago pelo cliente
        vlrTotal = hosp.getVlrPago().doubleValue();

        p.setFont(fonteChateauRose);
        p.setSpacingAfter(2);
        p.setSpacingBefore(0);

        p.add("Número de adultos");
        p.add(c);
        p.add(String.valueOf(nroAdultos));
        document.add(p);
        p.clear();

        p.add("Número de crianças");
        p.add(c);
        p.add(String.valueOf(nroCriancas));
        document.add(p);
        p.clear();

        p.add("Dias de estadia");
        p.add(c);
        p.add(String.valueOf(dias));
        document.add(p);
        p.clear();

        p.add("Valor total das diárias");
        p.add(c);

        String strValorDiarias = currencyFormatter.format(valorDiarias);
        p.setFont(fonteChateauRoseNegrito);
        p.add(strValorDiarias);
        document.add(p);
        p.clear();

        // parágrafo com o valor total
        String strValorTotal = currencyFormatter.format(vlrTotal);
        p.setFont(fonteRedViolet);
        p.setSpacingBefore(20);
        p.add("Valor total");
        p.add(c);
        p.add(strValorTotal);
        document.add(p);
    }

}
