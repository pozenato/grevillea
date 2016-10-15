/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apresentacao.validacao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Bruno Pozenato
 */
@FacesValidator("validateEmail")
public class EmailValidator implements Validator {

    private static final String O_EMAIL_FORNECIDO_E_INVALIDO = "E-mail Inválido!";

    @Override
    public void validate(FacesContext facesContext, UIComponent uIComponent, Object object) throws ValidatorException {

        String enteredEmail = (String) object;
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(enteredEmail);

        boolean matchFound = m.matches();

        if (!matchFound) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, O_EMAIL_FORNECIDO_E_INVALIDO, O_EMAIL_FORNECIDO_E_INVALIDO));
        }
    }
}
