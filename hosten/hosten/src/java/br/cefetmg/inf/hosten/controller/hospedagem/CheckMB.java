package br.cefetmg.inf.hosten.controller.hospedagem;

import br.cefetmg.inf.hosten.controller.context.ContextUtils;
import br.cefetmg.inf.hosten.model.domain.Hospede;
import br.cefetmg.inf.hosten.model.domain.rel.Despesa;
import br.cefetmg.inf.hosten.model.service.IControlarDespesas;
import br.cefetmg.inf.hosten.model.service.IControlarHospedagem;
import br.cefetmg.inf.hosten.proxy.ControlarDespesasProxy;
import br.cefetmg.inf.hosten.proxy.ControlarHospedagemProxy;
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
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

@SessionScoped
@Named(value = "checkMB")
public class CheckMB implements Serializable {

    private int nroQuarto;
    private Hospede hospedeSelecionado;

    private int diasDeEstadia;
    private int nroAdultos;
    private int nroCriancas;

    public void resetaVariaveis() {
        nroQuarto = 0;
        hospedeSelecionado = null;
        diasDeEstadia = 0;
        nroAdultos = 0;
        nroCriancas = 0;
    }

    public void iniciaCheck(int nroQuarto, String operacao) {
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

    public int getNroQuarto() {
        return nroQuarto;
    }

    public void setNroQuarto(int nroQuarto) {
        this.nroQuarto = nroQuarto;
    }

    public int getDiasDeEstadia() {
        return diasDeEstadia;
    }

    public void setDiasDeEstadia(int diasDeEstadia) {
        this.diasDeEstadia = diasDeEstadia;
    }

    public int getNroAdultos() {
        return nroAdultos;
    }

    public void setNroAdultos(int nroAdultos) {
        this.nroAdultos = nroAdultos;
    }

    public int getNroCriancas() {
        return nroCriancas;
    }

    public void setNroCriancas(int nroCriancas) {
        this.nroCriancas = nroCriancas;
    }

    public String checkIn() {
        IControlarHospedagem controlarHosp = new ControlarHospedagemProxy();

        boolean testeRegistro = controlarHosp.efetuarCheckIn(String.valueOf(nroQuarto), hospedeSelecionado.getCodCPF(), diasDeEstadia, nroAdultos, nroCriancas);
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

    public void checkOut() {
        IControlarHospedagem controlarHosp = new ControlarHospedagemProxy();

        int seqHospedagem = controlarHosp.efetuarCheckOut(String.valueOf(nroQuarto));

        String nomeArquivo = "fatura.pdf";

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.setResponseHeader("Content-Type", "application/pdf");
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + "fatura" + ".pdf" + "\"");
        
        try {
            geraFatura(nroQuarto, seqHospedagem, nomeArquivo,ec.getResponseOutputStream());
            FacesContext.getCurrentInstance().responseComplete();

            ContextUtils.redireciona("./quartos-estados.jsf");

        } catch (DocumentException | IOException ex) {
            //
            //
            //
        }

    }

    private Document document;

    public void geraFatura(int nroQuarto, int seqHospedagem, String nomeArquivo, OutputStream outs)
            throws DocumentException, IOException {
        
        this.document = new Document();
        PdfWriter.getInstance(document, outs);
        document = new Document(PageSize.A4, 90, 90, 90, 90);

        document.open();

        try {
            montaArquivo(seqHospedagem);
        } catch (NegocioException | SQLException ex) {
            //
            //
            //
        }

        document.close();
    }

    private void montaArquivo(int seqHospedagem) throws NegocioException, SQLException, DocumentException {
        IControlarDespesas controlarDespesas = new ControlarDespesasProxy();
        List<Despesa> listaDespesas = controlarDespesas.listar(
                seqHospedagem, nroQuarto
        );

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
        String nomeHospede = listaDespesas.get(0).getNomeHospede();

        p.setFont(fonteRedViolet);
        p.setSpacingBefore(20);
        p.setSpacingAfter(20);
        p.add(nomeHospede);
        document.add(p);
        p.clear();

        Double vlrTotal = 0.0;
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();

        for (Despesa despesa : listaDespesas) {
            // um parágrafo para cada item/servico consumido
            int qtdServico = despesa.getQtdConsumo();
            String desServico = despesa.getDesServico();
            Double vlrServico = despesa.getVlrUnit();

            vlrTotal += vlrServico * qtdServico;

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

        // parágrafo com as informações da diária
        int nroAdultos = listaDespesas.get(0).getNroAdultos();
        int nroCriancas = listaDespesas.get(0).getNroCriancas();
        Double vlrDiaria = listaDespesas.get(0).getVlrDiaria();

        Timestamp datCheckIn = listaDespesas.get(0).getDatCheckIn();
        Timestamp datCheckOut = listaDespesas.get(0).getDatCheckOut();
        long msDiferenca = (datCheckOut.getTime()) - (datCheckIn.getTime());
        long segundos = msDiferenca / 1000;
        long minutos = segundos / 60;
        long horas = minutos / 60;
        long dias = horas / 24;

        Double valorDiarias = dias * vlrDiaria;
        vlrTotal += valorDiarias;

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
