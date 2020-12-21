package MVC.Modelos.Catalogos;

import java.util.*;
import MVC.Modelos.ModelosBase.*;
import MVC.Exceptions.ProdutoJaExisteException;

public interface IFaturacao{

    public Map<String,Matriz> getFaturas();

    public void insereProduto(String produto) throws ProdutoJaExisteException;

    public void adicionaVendas(Collection<Venda> vendas);

    public String toString();
}
