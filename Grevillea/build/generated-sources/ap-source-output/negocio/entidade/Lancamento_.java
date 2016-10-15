package negocio.entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import negocio.entidade.Evento;
import negocio.entidade.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-14T10:39:18")
@StaticMetamodel(Lancamento.class)
public class Lancamento_ { 

    public static volatile SingularAttribute<Lancamento, Date> dataprevisao;
    public static volatile SingularAttribute<Lancamento, Float> valorrecebido;
    public static volatile SingularAttribute<Lancamento, Usuario> usuariorecebimento;
    public static volatile SingularAttribute<Lancamento, Boolean> status;
    public static volatile SingularAttribute<Lancamento, Date> datarecebimento;
    public static volatile SingularAttribute<Lancamento, Integer> idlancamento;
    public static volatile SingularAttribute<Lancamento, Date> datalancamento;
    public static volatile SingularAttribute<Lancamento, Float> valorprevisto;
    public static volatile SingularAttribute<Lancamento, Evento> evento;
    public static volatile SingularAttribute<Lancamento, Usuario> usuariolancamento;

}