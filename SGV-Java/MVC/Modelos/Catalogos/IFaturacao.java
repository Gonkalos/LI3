package MVC.Modelos.Catalogos;

import java.util.*;
import MVC.Modelos.ModelosBase.*;
import MVC.Exceptions.ProdutoJaExisteException;

/**
 * Interface IFaturacao da classe Faturacao
 * 
 * @author (Grupo 26) 
 * @version (8/6/2019)
 */

public interface IFaturacao
{
    /**
     * Devolve as faturas
     * @return faturasAux
     */
    public Map<String,Matriz> getFaturas();
    /**
     * Método que insere um protudo
     * @param produto
     */
    public void insereProduto(String produto) throws ProdutoJaExisteException;
    /**
     * Métudo que adiciona uma nova venda
     * @param vendas
     */
    public void adicionaVendas(Collection<Venda> vendas);
    /**
     * Metodo que devolve a representação em String da Faturação
     * @return sb.toString().
     */
    public String toString();
}
