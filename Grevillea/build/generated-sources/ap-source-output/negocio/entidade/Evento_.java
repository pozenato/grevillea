package negocio.entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import negocio.entidade.Cliente;
import negocio.entidade.Lancamento;
import negocio.entidade.Tipo;
import negocio.entidade.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-14T10:39:18")
@StaticMetamodel(Evento.class)
public class Evento_ { 

    public static volatile SingularAttribute<Evento, String> contato;
    public static volatile SingularAttribute<Evento, Usuario> usuario;
    public static volatile SingularAttribute<Evento, Integer> idevento;
    public static volatile SingularAttribute<Evento, String> instituicao;
    public static volatile SingularAttribute<Evento, String> escola;
    public static volatile SingularAttribute<Evento, String> observacao;
    public static volatile SingularAttribute<Evento, Date> datacriacao;
    public static volatile SingularAttribute<Evento, String> telefoneconjuge;
    public static volatile SingularAttribute<Evento, Cliente> cliente;
    public static volatile SingularAttribute<Evento, Date> iddata;
    public static volatile SingularAttribute<Evento, Float> valor;
    public static volatile SingularAttribute<Evento, Character> ativo;
    public static volatile SingularAttribute<Evento, String> emailconjuge;
    public static volatile SingularAttribute<Evento, Tipo> tipo;
    public static volatile SingularAttribute<Evento, String> empresa;
    public static volatile SingularAttribute<Evento, String> nomeaniversariante;
    public static volatile SingularAttribute<Evento, String> nomeconjuge;
    public static volatile SingularAttribute<Evento, Lancamento> lancamento;

}