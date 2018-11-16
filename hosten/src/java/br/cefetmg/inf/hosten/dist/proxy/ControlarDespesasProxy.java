package br.cefetmg.inf.hosten.dist.proxy;

import br.cefetmg.inf.hosten.model.domain.rel.Despesa;
import br.cefetmg.inf.hosten.model.domain.rel.QuartoConsumo;
import br.cefetmg.inf.hosten.model.service.IControlarDespesas;
import br.cefetmg.inf.hosten.model.service.impl.ControlarDespesas;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ControlarDespesasProxy implements IControlarDespesas {

    private final IControlarDespesas controlarDespesas;
    
    public ControlarDespesasProxy() {
        controlarDespesas = new ControlarDespesas();
    }
    
    @Override
    public boolean inserir(QuartoConsumo quartoConsumo) throws NegocioException, SQLException {
        return controlarDespesas.inserir(quartoConsumo);
    }

    @Override
    public List<Despesa> listar(int seqHospedagem, int nroQuarto) throws NegocioException, SQLException {
        return controlarDespesas.listar(seqHospedagem, nroQuarto);
    }

    @Override
    public boolean excluir(QuartoConsumo quartoConsumo) throws NegocioException, SQLException {
        return controlarDespesas.excluir(quartoConsumo);
    }

    @Override
    public Map<String, Object> retornaDespesa(int seqHospedagem, int nroQuarto) 
            throws NegocioException, SQLException {
        return controlarDespesas.retornaDespesa(seqHospedagem, nroQuarto);
    }
}
