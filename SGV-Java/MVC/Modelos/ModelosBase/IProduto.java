package MVC.Modelos.ModelosBase;

import MVC.Modelos.ModelosBase.*;

/**
 * Interface IProduto da classe Produto.
 * 
 * @author (Grupo 26) 
 * @version (8/6/2019)
 */
public interface IProduto{
    
    /**
     * Devolve o código do produto
     * @return String
     */
    public String getCodigoProduto();
    
    /**
     * Verifica a igualdade de dois objectos
     * @param objecto
     * @return boolean
     */
    public boolean equals(Object o);
    
    /**
     * Retorna uma representação textual do objecto
     * @return String
     */
    public String toString();
    
    /**
     * Cria uma cópia do objecto 
     * @return objecto
     */
    public Produto clone();
    
}
