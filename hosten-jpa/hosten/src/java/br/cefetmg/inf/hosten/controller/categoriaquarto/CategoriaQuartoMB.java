package br.cefetmg.inf.hosten.controller.categoriaquarto;

import br.cefetmg.inf.hosten.controller.context.ContextUtils;
import br.cefetmg.inf.hosten.model.domain.Categoria;
import br.cefetmg.inf.hosten.model.domain.ItemConforto;
import br.cefetmg.inf.hosten.model.service.IManterCategoriaQuarto;
import br.cefetmg.inf.hosten.proxy.ManterCategoriaQuartoProxy;
import br.cefetmg.inf.util.exception.NegocioException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.RowEditEvent;

@ViewScoped
@Named("categoriaMB")
public class CategoriaQuartoMB implements Serializable {

    private List<Categoria> listaCategorias;
    private Categoria categoria;

    private ItemConforto[] itensSelecionados;

    private List<ItemConforto> itensRelacionados;
    private String codCategoriaAlterar;

    public CategoriaQuartoMB() {
        categoria = new Categoria(null, null, null);
        IManterCategoriaQuarto manterCategoria = new ManterCategoriaQuartoProxy();
        try {
            listaCategorias = manterCategoria.listarTodos();
        } catch (NegocioException | SQLException e) {
            //
        }
    }

    public List<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void onRowInit(RowEditEvent event) {
        codCategoriaAlterar = (String) event.getComponent().getAttributes().get("categoriaEditar");           
    }
    
    public void onRowEdit(RowEditEvent event) throws IOException {
        categoria = (Categoria) event.getObject();

        List<ItemConforto> listaItens = new ArrayList();
        listaItens.addAll(Arrays.asList(itensSelecionados));

        IManterCategoriaQuarto manterCategoria = new ManterCategoriaQuartoProxy();
        try {
            boolean testeExclusao = manterCategoria.alterar(codCategoriaAlterar, categoria, listaItens);
            if (testeExclusao) {
                ContextUtils.mostrarMensagem("Alteração efetuada", "Registro alterado com sucesso!", true);
            } else {
                ContextUtils.mostrarMensagem("Falha na alteração", "Falha ao alterar o registro!", true);
            }
        } catch (NegocioException | SQLException ex) {
            ContextUtils.mostrarMensagem("Falha na alteração", ex.getMessage(), true);
        }
        ContextUtils.redireciona(null);
    }

    public void onRowCancel(RowEditEvent event) {
        ContextUtils.mostrarMensagem("Edição Cancelada", ((Categoria) event.getObject()).getCodCategoria(), false);
    }

    public String excluir(Categoria categoria) {
        this.categoria = categoria;

        IManterCategoriaQuarto manterCategoria = new ManterCategoriaQuartoProxy();
        try {
            boolean testeExclusao = manterCategoria.excluir(categoria.getCodCategoria());
            if (testeExclusao) {
                ContextUtils.mostrarMensagem("Exclusão efetuada", "Registro excluído com sucesso!", true);
                return "sucesso";
            } else {
                ContextUtils.mostrarMensagem("Falha na exclusão", "Falha ao excluir o registro!", true);
                return "falha";
            }
        } catch (NegocioException | SQLException ex) {
            ContextUtils.mostrarMensagem("Falha na exclusão", ex.getMessage(), true);
            return "falha";
        }

    }

    public String inserir() {
        IManterCategoriaQuarto manterCategoria = new ManterCategoriaQuartoProxy();

        List<ItemConforto> listaItens = new ArrayList();
        listaItens.addAll(Arrays.asList(itensSelecionados));

        try {
            boolean testeInsercao = manterCategoria.inserir(categoria, listaItens);

            if (testeInsercao) {
                ContextUtils.mostrarMensagem("Inserção efetuada", "Registro inserido com sucesso!", true);
                return "sucesso";
            } else {
                ContextUtils.mostrarMensagem("Falha na inserção", "Falha ao inserir o registro!", true);
                return "falha";
            }
        } catch (NegocioException | SQLException ex) {
            ContextUtils.mostrarMensagem("Falha na inserção", ex.getMessage(), true);
            return "falha";
        }
    }

    public ItemConforto[] getItensSelecionadosArray() {
        return itensSelecionados;
    }

    public void setItensSelecionadosArray(ItemConforto[] itensSelecionadosArray) {
        this.itensSelecionados = itensSelecionadosArray;
    }

    public List<ItemConforto> getItensRelacionados(Categoria categoria) {
        IManterCategoriaQuarto manterCategoria = new ManterCategoriaQuartoProxy();

        try {
            itensRelacionados = manterCategoria.listarItensRelacionados(categoria.getCodCategoria());
        } catch (NegocioException | SQLException ex) {
            ex.printStackTrace();
            //
        }
        return itensRelacionados;
    }

    public List<ItemConforto> getItensRelacionados() {
        return itensRelacionados;
    }

    public void setItensRelacionados(List<ItemConforto> itensRelacionados) {
        this.itensRelacionados = itensRelacionados;
    }

}