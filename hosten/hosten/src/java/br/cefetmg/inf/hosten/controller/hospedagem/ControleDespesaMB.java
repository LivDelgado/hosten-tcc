package br.cefetmg.inf.hosten.controller.hospedagem;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

@Named(value = "controleDespesaMB")
@Dependent
public class ControleDespesaMB {
    
    /*
        atributos
            servicoSelecionado
            qtdConsumo
            codUsuarioRegistro --> pega da sessão também
        
        métodos
            inserir (Despesa) --> chama ControlarDespesas.inserir(QuartoConsumo) 
            excluir (Despesa) --> chama ControlarDespesas.excluir(QuartoConsumo) 
            
        // o seqHospedagem e o NroQuarto vao estar no bean despesaMB (na sessão)
        
    */

    public ControleDespesaMB() {
    }
    
}
