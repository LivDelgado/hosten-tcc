package br.cefetmg.inf.hosten.controller.servicoarea;

import br.cefetmg.inf.hosten.dist.proxy.ManterServicoAreaProxy;
import br.cefetmg.inf.hosten.model.domain.ServicoArea;
import br.cefetmg.inf.hosten.model.service.IManterServicoArea;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("servicoAreaConverter")
public class ServicoAreaConverter implements Converter  {
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        IManterServicoArea manterServicoArea = new ManterServicoAreaProxy();
        ServicoArea servicoArea = null;
        try {
            servicoArea = manterServicoArea.listar(value, "nomServicoArea").get(0);
        } catch (NegocioException | SQLException ex) {
            //
        }
        return servicoArea;
    }
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        ServicoArea servicoArea = ((ServicoArea) value);

        String areaAsString = 
                //categoria.getCodCategoria() + " - " + 
                servicoArea.getNomServicoArea();
                //+ " | " + 
                //categoria.getVlrDiaria();
        
        return areaAsString;
    }
}
