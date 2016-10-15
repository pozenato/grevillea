package negocio.entidade;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import negocio.entidade.Colaborador;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-14T10:39:18")
@StaticMetamodel(TipoColaborador.class)
public class TipoColaborador_ { 

    public static volatile SingularAttribute<TipoColaborador, BigInteger> valor;
    public static volatile SingularAttribute<TipoColaborador, Integer> idtipo;
    public static volatile SingularAttribute<TipoColaborador, String> descricao;
    public static volatile SingularAttribute<TipoColaborador, Colaborador> colaborador;

}