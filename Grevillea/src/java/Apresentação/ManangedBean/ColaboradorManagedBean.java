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
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import negocio.entidade.Colaborador;
import negocio.entidade.Tipo;
import negocio.entidade.TipoColaborador;
import negocio.fachada.ColaboradorFachada;
import negocio.fachada.TipoColaboradorFachada;
import org.primefaces.model.DualListModel;

/**
 *
 * @author pozenato
 */
@ManagedBean(name = "colaboradorManagedBean")
@SessionScoped
public class ColaboradorManagedBean {

    private Colaborador colaborador = new Colaborador();
    private List<Colaborador> colaboradores;
    private List<Colaborador> colaboradorFiltro;
    private TipoColaborador tipo = new TipoColaborador();
    private int render;
    private int idTipoSelecionado;
    public ImpressaoManagedBean impressaoManagedBean = new ImpressaoManagedBean();

    @EJB
    private ColaboradorFachada colaboradorFachada;
    
    @EJB
    private TipoColaboradorFachada tipoColaboradorFachada;

    public ColaboradorManagedBean() {
    }

    public String montarPaginaParaInsercao() {
        this.colaborador = new Colaborador();
        this.idTipoSelecionado = 0;
        return "/Colaborador/InserirColaborador?faces-redirect=true";
    }

    public String montarPaginaParaAlterar () {
        return "/Colaborador/AlterarColaborador?faces-redirect=true";
    }
    
    public String Alterar () {
        this.getColaboradorFachada().Alterar(colaborador);
        this.recuperarColaboradores(); 
        return "/Colaborador/ListarColaboradores?faces-redirect=true";
    }
    
    public String Inserir() {
        this.colaborador.setStatus(true);
        this.setTipo(this.tipoColaboradorFachada.listarPorId(idTipoSelecionado));
        this.colaborador.setTipo(tipo);
        colaboradorFachada.Inserir(colaborador);
        this.recuperarColaboradores();        
        return "/Colaborador/ListarColaboradores?faces-redirect=true";
    }    

    private void recuperarColaboradores() {
        colaboradorFiltro = colaboradorFachada.Listar();
        this.colaboradores = colaboradorFachada.Listar();
    }
    
     public List<SelectItem> getTiposColaborador() {
        List<TipoColaborador> tiposCadastrados = tipoColaboradorFachada.Listar();

        List<SelectItem> tipos = new ArrayList<>(tiposCadastrados.size());

        for (TipoColaborador tipo : tiposCadastrados) {
            tipos.add(new SelectItem(tipo.getIdtipo(), tipo.getDescricao()));
        }

        return tipos;
    }

    public String listar() {
        this.recuperarColaboradores();
        return "/Colaborador/ListarColaboradores?faces-redirect=true";
    }

    public Colaborador getColaborador() {
        return this.colaborador;
    }

    /**
     * @param colaborador the produto to set
     */
    
    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    /**
     * @return the colaboradorFachada
     */
    public ColaboradorFachada getColaboradorFachada() {
        return colaboradorFachada;
    }

    /**
     * @param colaboradorFachada the colaboradorFachada to set
     */
    public void setColaboradorFachada(ColaboradorFachada colaboradorFachada) {
        this.colaboradorFachada = colaboradorFachada;
    }

    /**
     * @return the colaboradoress
     */
    public List<Colaborador> getColaboradores() {
        return colaboradores;
    }

    /**
     * @param colaboradores the colaboradores to set
     */
    public void setColaboradores(List<Colaborador> colaboradores) {
        this.colaboradores = colaboradores;
    }

    /**
     * @return the colaboradorFiltro
     */
    public List<Colaborador> getColaboradorFiltro() {
        return colaboradorFiltro;
    }

    /**
     * @param colaboradorFiltro the colaboradorFiltro to set
     */
    public void setColaboradorFiltro(List<Colaborador> colaboradorFiltro) {
        this.colaboradorFiltro = colaboradorFiltro;
    }
    
    public void imprimePDF(Object document) throws IOException, BadElementException, DocumentException {
        impressaoManagedBean.preProcessPDF(document, "Lista de Colaboradores");
    }
    
    public void imprimeXLS(Object document){
        impressaoManagedBean.gerarXLS(document);
    }


    /**
     * @return the tipoColaborador
     */
    public TipoColaborador getTipo() {
        return tipo;
    }

    /**
     * @param tipoColaborador the tipoColaborador to set
     */
    public void setTipo(TipoColaborador tipo) {
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

    /**
     * @return the idTipoSelecionado
     */
    public int getIdTipoSelecionado() {
        return idTipoSelecionado;
    }

    /**
     * @param idTipoSelecionado the idTipoSelecionado to set
     */
    public void setIdTipoSelecionado(int idTipoSelecionado) {
        this.idTipoSelecionado = idTipoSelecionado;
    }
}
