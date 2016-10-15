package negocio.entidade;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import negocio.entidade.TipoColaborador;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-14T10:39:18")
@StaticMetamodel(Colaborador.class)
public class Colaborador_ { 

    public static volatile SingularAttribute<Colaborador, Boolean> status;
    public static volatile SingularAttribute<Colaborador, TipoColaborador> tipo;
    public static volatile SingularAttribute<Colaborador, Integer> idcolaborador;
    public static volatile SingularAttribute<Colaborador, String> nome;

}