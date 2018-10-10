package br.cefetmg.inf.hosten.controller.hospedagem;

import br.cefetmg.inf.hosten.controller.context.ContextUtils;
import br.cefetmg.inf.hosten.model.domain.Hospede;
import br.cefetmg.inf.hosten.model.service.IControlarHospedagem;
import br.cefetmg.inf.hosten.proxy.ControlarHospedagemProxy;
import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named(value = "checkInMB")
public class CheckInMB implements Serializable {
    private int nroQuarto;
    private Hospede hospedeSelecionado;
    
    private int diasDeEstadia;
    private int nroAdultos;
    private int nroCriancas;
    
    public void resetaVariaveis () {
        nroQuarto = 0;
        hospedeSelecionado = null;
        diasDeEstadia = 0;
        nroAdultos = 0;
        nroCriancas = 0;
    }
    

    public void iniciaCheckIn (int nroQuarto) {
        setNroQuarto(nroQuarto);
        try {
            ContextUtils.redireciona("./check-in.jsf");
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

}
