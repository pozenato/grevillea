/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.fachada;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import negocio.entidade.Cliente;
import negocio.persistecia.ClienteDAO;

/**
 *
 * @author pozenato
 */
@Stateless
public class ClienteFachada {

    public Cliente cliente = new Cliente();
    private String nomeCorrigido = new String();
    @EJB
    private ClienteDAO clienteDAO;

    public static String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    public void Inserir(Cliente cliente) {
        nomeCorrigido = removerAcentos(cliente.getNome().toUpperCase());
        cliente.setNome(nomeCorrigido);
        clienteDAO.Inserir(cliente);
    }

    public List<Cliente> Listar() {
        return clienteDAO.RecuperarTodos();
    }

    public List<Cliente> recuperarCliente(Cliente cliente) {
        return clienteDAO.buscaClientePorNome(cliente);         
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the nomeCorrigido
     */
    public String getNomeCorrigido() {
        return nomeCorrigido;
    }

    /**
     * @param nomeCorrigido the nomeCorrigido to set
     */
    public void setNomeCorrigido(String nomeCorrigido) {
        this.nomeCorrigido = nomeCorrigido;
    }

    public void Alterar(Cliente cliente) {
        clienteDAO.Alterar(cliente);
    }
}
