/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentação.ManangedBean;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import negocio.entidade.Item;
import negocio.fachada.ItemFachada;
import org.primefaces.model.DualListModel;


/**
 *
 * @author pozenato
 */
@ManagedBean(name = "itemManagedBean")
@RequestScoped
public class ItemManagedBean {

    private Item item = new Item();
    private List<Item> Itens;
    private List<Item> ItemFiltro;
    public ImpressaoManagedBean impressaoManagedBean = new ImpressaoManagedBean();

    @EJB
    private ItemFachada itemFachada;

    public ItemManagedBean() {
    }

    public String montarPaginaParaInsercao() {
        this.item = new Item();
        this.recuperarItens();
        return "/Item/InserirItem?faces-redirect=true";
    }
    
    public String montarPaginaParaAlteracao() {
        return "/Item/AlterarItem?faces-redirect=true";
    }

    public String Inserir() {
        this.item.setStatus(true);
        getItemFachada().Inserir(getItem());
        this.recuperarItens();
        return "/Item/ListarItens?faces-redirect=true";
    }
    
    public String Alterar() {
        getItemFachada().Alterar(getItem());
        this.recuperarItens();
        return "/Item/ListarItens?faces-redirect=true";
    }

    private void recuperarItens() {
        setItemFiltro(getItemFachada().Listar());
        this.setItens(getItemFachada().Listar());
    }

    public String listar() {
        this.recuperarItens();
        return "/Item/ListarItens?faces-redirect=true";
    }

    /**
     * @return the item
     */
    public Item getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * @return the Itens
     */
    public List<Item> getItens() {
        this.recuperarItens();
        return Itens;
    }

    /**
     * @param Itens the Itens to set
     */
    public void setItens(List<Item> Itens) {
        this.Itens = Itens;
    }

    /**
     * @return the ItemFiltro
     */
    public List<Item> getItemFiltro() {
        return ItemFiltro;
    }

    /**
     * @param ItemFiltro the ItemFiltro to set
     */
    public void setItemFiltro(List<Item> ItemFiltro) {
        this.ItemFiltro = ItemFiltro;
    }

    /**
     * @return the itemFachada
     */
    public ItemFachada getItemFachada() {
        return itemFachada;
    }

    /**
     * @param itemFachada the itemFachada to set
     */
    public void setItemFachada(ItemFachada itemFachada) {
        this.itemFachada = itemFachada;
    }
    
     public void imprimePDF(Object document) throws IOException, BadElementException, DocumentException {
        impressaoManagedBean.preProcessPDF(document, "Lista de Itens");
    }
     
     public void imprimeXLS(Object document){
        impressaoManagedBean.gerarXLS(document);
    }

}

