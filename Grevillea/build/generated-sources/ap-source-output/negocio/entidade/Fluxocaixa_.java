package negocio.entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import negocio.entidade.Fornecedor;
import negocio.entidade.Produto;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-14T10:39:18")
@StaticMetamodel(Fluxocaixa.class)
public class Fluxocaixa_ { 

    public static volatile SingularAttribute<Fluxocaixa, Fornecedor> fornecedor;
    public static volatile SingularAttribute<Fluxocaixa, Date> datadespesa;
    public static volatile SingularAttribute<Fluxocaixa, Produto> produto;
    public static volatile SingularAttribute<Fluxocaixa, Float> valor;
    public static volatile SingularAttribute<Fluxocaixa, Boolean> status;
    public static volatile SingularAttribute<Fluxocaixa, Character> tipo;
    public static volatile SingularAttribute<Fluxocaixa, Date> datainsercao;
    public static volatile SingularAttribute<Fluxocaixa, Integer> idfluxo;

}