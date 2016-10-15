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
import negocio.entidade.Produto;
import negocio.fachada.ProdutoFachada;
import org.primefaces.model.DualListModel;

/**
 *
 * @author pozenato
 */
@ManagedBean(name = "produtoManagedBean")
@RequestScoped
public class ProdutoManagedBean {

    private Produto produto = new Produto();
    private List<Produto> produtos;
    private List<Produto> produtoFiltro;
    public ImpressaoManagedBean impressaoManagedBean = new ImpressaoManagedBean();

    @EJB
    private ProdutoFachada produtoFachada;

    public ProdutoManagedBean() {
    }

    public String montarPaginaParaInsercao() {
        this.produto = new Produto();
        this.recuperarProdutos();
        return "/Produto/InserirProduto?faces-redirect=true";
    }

    public String Inserir() {
        produtoFachada.Inserir(produto);
        this.recuperarProdutos();
        return "/Produto/ListarProdutos?faces-redirect=true";
    }
    

    private void recuperarProdutos() {
        setProdutoFiltro(produtoFachada.Listar());
        this.setProdutos(produtoFachada.Listar());
    }

    public String listar() {
        this.recuperarProdutos();
        return "/Produto/ListarProdutos?faces-redirect=true";
    }

    public Produto getProduto() {
        return produto;
    }

    /**
     * @param produto the produto to set
     */
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    /**
     * @return the produtoFachada
     */
    public ProdutoFachada getProdutoFachada() {
        return produtoFachada;
    }

    /**
     * @param produtoFachada the produtoFachada to set
     */
    public void setProdutoFachada(ProdutoFachada produtoFachada) {
        this.produtoFachada = produtoFachada;
    }

    /**
     * @return the produtos
     */
    public List<Produto> getProdutos() {
        this.recuperarProdutos();
        return produtos;
    }

    /**
     * @param produtos the produtos to set
     */
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    /**
     * @return the produtoFiltro
     */
    public List<Produto> getProdutoFiltro() {
        return produtoFiltro;
    }

    /**
     * @param produtoFiltro the produtoFiltro to set
     */
    public void setProdutoFiltro(List<Produto> produtoFiltro) {
        this.produtoFiltro = produtoFiltro;
    }
    
    public void imprimePDF(Object document) throws IOException, BadElementException, DocumentException {
        impressaoManagedBean.preProcessPDF(document, "Lista de Produtos");
    }
    
    public void imprimeXLS(Object document){
        impressaoManagedBean.gerarXLS(document);
    }

}
