package negocio.excecao;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MensagemUtility {

    public static void adicionarMensagemDeErro(String idDoComponente, String mensagem) {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(idDoComponente, facesMessage);
    }

    public static void adicionarMensagemDeSucesso(String idDoComponente, String mensagem) {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(idDoComponente, facesMessage);
    }
}