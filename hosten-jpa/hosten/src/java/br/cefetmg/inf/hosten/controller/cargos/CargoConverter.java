package br.cefetmg.inf.hosten.controller.cargos;

import br.cefetmg.inf.hosten.model.domain.Cargo;
import br.cefetmg.inf.hosten.model.service.IManterCargo;
import br.cefetmg.inf.hosten.proxy.ManterCargoProxy;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("cargoConverter")
public class CargoConverter implements Converter  {
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        IManterCargo manterCargo = new ManterCargoProxy();
        Cargo cargo = null;
        try {
            cargo = manterCargo.listar(value, "nomCargo").get(0);
        } catch (NegocioException | SQLException ex) {
            //
            //
        }
        
        return cargo;
    }
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Cargo cargo = ((Cargo) value);
//        String gerente;
//        if (cargo.isIdtMaster()) {
//            gerente = "Gerente";
//        } else {
//            gerente = "Não gerente";
//        }
//        
        String cargoAsString;
//        cargoAsString = cargo.getCodCargo() + " - " + cargo.getNomCargo() + " | " + gerente;
        cargoAsString = cargo.getNomCargo();
        
        return cargoAsString;
    }
}
