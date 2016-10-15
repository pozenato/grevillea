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
import negocio.entidade.Terceiro;
import negocio.entidade.TerceiroTipo;
import negocio.entidade.Tipo;
import negocio.entidade.TipoColaborador;
import negocio.fachada.ProdutoFachada;
import negocio.fachada.TerceiroFachada;
import org.primefaces.model.DualListModel;

/**
 *
 * @author pozenato
 */
@ManagedBean(name = "terceiroManagedBean")
@RequestScoped
public class TerceiroManagedBean {

    private Terceiro terceiro = new Terceiro();
    private List<Terceiro> terceiros;
    private List<Terceiro> terceiroFiltro;
    private TerceiroTipo tipo = new TerceiroTipo();
    private int render;
    public ImpressaoManagedBean impressaoManagedBean = new ImpressaoManagedBean();

    @EJB
    private TerceiroFachada terceiroFachada;

    public TerceiroManagedBean() {
    }

    public String montarPaginaParaInsercao() {
        this.terceiro = new Terceiro();
        this.recuperarTerceiros();
        return "/Terceiro/InserirTerceiro?faces-redirect=true";
    }
    
    public String montarPaginaParaAlteracao() {
        return "/Terceiro/AlterarTerceiro?faces-redirect=true";
    }
    
    public String Alterar() {
        getTerceiroFachada().Alterar(terceiro);
        this.recuperarTerceiros();        
        return "/Terceiro/ListarTerceiros?faces-redirect=true";  
    }

    public String Inserir() {
        this.terceiro.setStatus(true);
        this.tipo.setIdtipo(render);
        this.terceiro.setTipo(tipo);
        terceiroFachada.Inserir(terceiro);
        this.recuperarTerceiros();        
        return "/Terceiro/ListarTerceiros?faces-redirect=true";
    }
    

    private void recuperarTerceiros() {
        setTerceiroFiltro(terceiroFachada.Listar());
        this.setTerceiros(terceiroFachada.Listar());
    }

    public String listar() {
        this.recuperarTerceiros();
        return "/Terceiro/ListarTerceiros?faces-redirect=true";
    }

    public Terceiro getTerceiro() {
        return terceiro;
    }

    /**
     * @param terceiro the produto to set
     */
    
    public void setTerceiro(Terceiro terceiro) {
        this.terceiro = terceiro;
    }

    /**
     * @return the terceiroFachada
     */
    public TerceiroFachada getTerceiroFachada() {
        return terceiroFachada;
    }

    /**
     * @param terceiroFachada the terceiroFachada to set
     */
    public void setTerceiroFachada(TerceiroFachada terceiroFachada) {
        this.terceiroFachada = terceiroFachada;
    }

    /**
     * @return the terceiros
     */
    public List<Terceiro> getTerceiros() {
        this.recuperarTerceiros();
        return terceiros;
    }

    /**
     * @param produtos the produtos to set
     */
    public void setTerceiros(List<Terceiro> terceiros) {
        this.terceiros = terceiros;
    }

    /**
     * @return the produtoFiltro
     */
    public List<Terceiro> getTerceiroFiltro() {
        return terceiroFiltro;
    }

    /**
     * @param terceiroFiltro the produtoFiltro to set
     */
    public void setTerceiroFiltro(List<Terceiro> terceiroFiltro) {
        this.terceiroFiltro = terceiroFiltro;
    }
    
    public void imprimePDF(Object document) throws IOException, BadElementException, DocumentException {
        impressaoManagedBean.preProcessPDF(document, "Lista de Terceiros");
    }
    
    public void imprimeXLS(Object document){
        impressaoManagedBean.gerarXLS(document);
    }
   
    /**
     * @return the tipo
     */
    public TerceiroTipo getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TerceiroTipo tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the render
     */
    public int getRender() {
        return render;
    }

    /**
     * @param render the render to set
     */
    public void setRender(int render) {
        this.render = render;
    }

}
