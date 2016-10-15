package negocio.entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import negocio.entidade.Fluxocaixa;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-14T10:39:18")
@StaticMetamodel(Fornecedor.class)
public class Fornecedor_ { 

    public static volatile SingularAttribute<Fornecedor, String> cidade;
    public static volatile SingularAttribute<Fornecedor, String> email;
    public static volatile SingularAttribute<Fornecedor, String> telefone;
    public static volatile SingularAttribute<Fornecedor, Date> datacadastro;
    public static volatile SingularAttribute<Fornecedor, Fluxocaixa> fluxo;
    public static volatile SingularAttribute<Fornecedor, Integer> idfornecedor;
    public static volatile SingularAttribute<Fornecedor, String> endereco;
    public static volatile SingularAttribute<Fornecedor, String> nomefornecedor;

}