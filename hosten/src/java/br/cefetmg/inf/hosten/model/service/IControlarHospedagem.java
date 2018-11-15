package br.cefetmg.inf.hosten.model.service;

import br.cefetmg.inf.hosten.model.domain.Hospedagem;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoEstado;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoHospedagem;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.List;

public interface IControlarHospedagem {
    public boolean efetuarCheckIn(String nroQuarto, String codCPF, int diasEstadia, int nroAdultos, int nroCriancas);
    public int efetuarCheckOut(String nroQuarto);
    public List<QuartoEstado> listarTodos() throws NegocioException;
    public Hospedagem buscaHospedagem(int seqHospedagem) throws SQLException;
    public QuartoHospedagem buscaQuartoHospedagem(int seqHospedagem) throws SQLException;
}
