package br.cefetmg.inf.hosten.controller.categoriaquarto;

import br.cefetmg.inf.hosten.model.domain.Categoria;
import br.cefetmg.inf.hosten.model.service.IManterCategoriaQuarto;
import br.cefetmg.inf.hosten.proxy.ManterCategoriaQuartoProxy;
import br.cefetmg.inf.util.exception.NegocioException;
import java.sql.SQLException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

// PADRÃO DE STRING
// 001 - Categoria | Valor

@FacesConverter("categoriaConverter")
public class CategoriaConverter implements Converter  {
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        IManterCategoriaQuarto manterCategoria = new ManterCategoriaQuartoProxy();
        Categoria categoria = null;
        try {
            categoria = manterCategoria.listar(value, "nomCategoria").get(0);
        } catch (NegocioException | SQLException ex) {
            //
        }
        return categoria;
    }
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Categoria categoria = ((Categoria) value);

        String categoriaAsString = 
                //categoria.getCodCategoria() + " - " + 
                categoria.getNomCategoria();
                //+ " | " + 
                //categoria.getVlrDiaria();
        
        return categoriaAsString;
    }
}