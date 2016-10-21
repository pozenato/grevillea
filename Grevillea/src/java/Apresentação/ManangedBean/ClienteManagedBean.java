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
import negocio.entidade.Cliente;
import negocio.fachada.ClienteFachada;
import org.primefaces.model.DualListModel;

/**
 *
 * @author pozenato
 */
@ManagedBean(name = "clienteManagedBean")
@SessionScoped
public class ClienteManagedBean {

    private Cliente cliente = new Cliente();
    private List<Cliente> Clientes;
    private List<Cliente> ClienteFiltro;
    public ImpressaoManagedBean impressaoManagedBean = new ImpressaoManagedBean();

    @EJB
    private ClienteFachada clienteFachada;

    public ClienteManagedBean() {
    }

    public String montarPaginaParaInsercao() {
        this.setCliente(new Cliente());
        this.recuperarClientes();
        return "/Cliente/InserirCliente?faces-redirect=true";
    }
    
     public String montarPaginaParaAlteracao() {
        return "/Cliente/AlterarCliente?faces-redirect=true";
    }
     
    public String Alterar() {
        clienteFachada.Alterar(cliente);
        this.recuperarClientes();
        return "/Cliente/ListarClientes?faces-redirect=true";
    }

    public String Inserir() {
        clienteFachada.Inserir(cliente);
        this.recuperarClientes();
        return "/Cliente/ListarClientes?faces-redirect=true";
    }

    private void recuperarClientes() {
        setClienteFiltro(getClienteFachada().Listar());
        this.setClientes(getClienteFachada().Listar());
    }

    public String listar() {
        this.recuperarClientes();
        return "/Cliente/ListarClientes?faces-redirect=true";
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the item to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the Clientes
     */
    public List<Cliente> getClientes() {
        this.recuperarClientes();
        return Clientes;
    }

    /**
     * @param Clientes the Clientes to set
     */
    public void setClientes(List<Cliente> Clientes) {
        this.Clientes = Clientes;
    }

    /**
     * @return the ClienteFiltro
     */
    public List<Cliente> getClienteFiltro() {
        return ClienteFiltro;
    }

    /**
     * @param ClienteFiltro the ClienteFiltro to set
     */
    public void setClienteFiltro(List<Cliente> ClienteFiltro) {
        this.ClienteFiltro = ClienteFiltro;
    }

    /**
     * @return the clienteFachada
     */
    public ClienteFachada getClienteFachada() {
        return clienteFachada;
    }

    /**
     * @param clienteFachada the clienteFachada to set
     */
    public void setClienteFachada(ClienteFachada clienteFachada) {
        this.clienteFachada = clienteFachada;
    }

    public void imprimePDF(Object document) throws IOException, BadElementException, DocumentException {
        impressaoManagedBean.preProcessPDF(document, "Lista de Clientes");
    }

    public void imprimeXLS(Object document) {
        impressaoManagedBean.gerarXLS(document);
    }

}
