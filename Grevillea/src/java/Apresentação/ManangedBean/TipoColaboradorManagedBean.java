/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apresentação.ManangedBean;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import negocio.entidade.TipoColaborador;
import negocio.fachada.TipoColaboradorFachada;

/**
 *
 * @author pozenato
 */
@ManagedBean(name = "tipoColaboradorManagedBean")
@SessionScoped
public class TipoColaboradorManagedBean {

    private TipoColaborador tipoColaborador = new TipoColaborador();
    private List<TipoColaborador> tipoColaboradores;
    private List<TipoColaborador> tipoColaboradorFiltro;
    public ImpressaoManagedBean impressaoManagedBean = new ImpressaoManagedBean();

    @EJB
    private TipoColaboradorFachada tipoColaboradorFachada;

    public TipoColaboradorManagedBean() {
    }

    public String montarPaginaParaInsercao() {
        this.setTipoColaborador(new TipoColaborador());
        this.recuperarTipos();
        return "/TipoColaborador/InserirTipoColaborador?faces-redirect=true";
    }
    
    public String montarPaginaParaAlteracao() {
        return "/TipoColaborador/AlterarTipoColaborador?faces-redirect=true";
    }

    public String Inserir() {
        tipoColaboradorFachada.Inserir(tipoColaborador);
        this.recuperarTipos();        
        return "/TipoColaborador/ListarTipos?faces-redirect=true";
    }
    
    public String Alterar() {
        tipoColaboradorFachada.Alterar(tipoColaborador);
        this.recuperarTipos();        
        return "/TipoColaborador/ListarTipos?faces-redirect=true";
    }

    private void recuperarTipos() {
        setTipoColaboradorFiltro(tipoColaboradorFachada.Listar());
        this.setTipoColaboradores(tipoColaboradorFachada.Listar());
    }

    public String listar() {
        this.recuperarTipos();
        return "/TipoColaborador/ListarTipos?faces-redirect=true";
    }
   
    public void imprimePDF(Object document) throws IOException, BadElementException, DocumentException {
        impressaoManagedBean.preProcessPDF(document, "Lista de Terceiros");
    }
    
    public void imprimeXLS(Object document){
        impressaoManagedBean.gerarXLS(document);
    }

    /**
     * @return the tipoColaborador
     */
    public TipoColaborador getTipoColaborador() {
        return tipoColaborador;
    }

    /**
     * @param tipoColaborador the tipoColaborador to set
     */
    public void setTipoColaborador(TipoColaborador tipoColaborador) {
        this.tipoColaborador = tipoColaborador;
    }

    /**
     * @return the tipoColaboradores
     */
    public List<TipoColaborador> getTipoColaboradores() {
        return tipoColaboradores;
    }

    /**
     * @param tipoColaboradores the tipoColaboradores to set
     */
    public void setTipoColaboradores(List<TipoColaborador> tipoColaboradores) {
        this.tipoColaboradores = tipoColaboradores;
    }

    /**
     * @return the tipoColaboradorFiltro
     */
    public List<TipoColaborador> getTipoColaboradorFiltro() {
        return tipoColaboradorFiltro;
    }

    /**
     * @param tipoColaboradorFiltro the tipoColaboradorFiltro to set
     */
    public void setTipoColaboradorFiltro(List<TipoColaborador> tipoColaboradorFiltro) {
        this.tipoColaboradorFiltro = tipoColaboradorFiltro;
    }

    /**
     * @return the tipoColaboradorFachada
     */
    public TipoColaboradorFachada getTipoColaboradorFachada() {
        return tipoColaboradorFachada;
    }

    /**
     * @param tipoColaboradorFachada the tipoColaboradorFachada to set
     */
    public void setTipoColaboradorFachada(TipoColaboradorFachada tipoColaboradorFachada) {
        this.tipoColaboradorFachada = tipoColaboradorFachada;
    }
   
    
}
