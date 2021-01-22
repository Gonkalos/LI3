package MVC.Modelos.ModelosBase;

import MVC.Modelos.ModelosBase.*;

/**
 * Interface IVenda da classe Venda.
 * 
 * @author (Grupo 26) 
 * @version (8/6/2019)
 */
public interface IVenda{
    
    /**
      * Devolve o código do produto vendido
      * @return string
      */
    public String getCodProd();
    
    /**
      * Devolve o código do cliente 
      * @return string
      */
    public String getCodCli();
    
    /**
      * Devolve a quantidade de unidades vendidas
      * @return int
      */
    public int getQuantidade();
    
    /**
      * Devolve o preço de cada unidade
      * @return double
      */
    public double getPreco();

    /**
      * Devolve o tipo de venda
      * @return string
      */
    public String getTipo();
    
    /**
      * Devolve o mês da realização da venda
      * @return integer
      */
    public int getMes();
    
    /**
      * Devolve a filial onde foi realizada a compra
      * @return integer
      */
    public int getFilial();
    
    /**
     * Devolve o total faturado de uma venda
     * @return double
     */
    public double faturado();
    
}

