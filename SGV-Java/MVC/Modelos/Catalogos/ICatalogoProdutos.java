package MVC.Modelos.Catalogos;

import MVC.Modelos.Catalogos.*;
import java.util.*;
import MVC.Modelos.ModelosBase.*;

/**
 * Interface ICatalogoProdutos da classe CatalogoProdutos.
 * 
 * @author (Grupo 26) 
 * @version (8/6/2019)
 */
public interface ICatalogoProdutos{
    
    /**
      * Devolve o TreeSet do CatalogoProdutos 
      * @return TreeSet de Produtos
      */
    public Collection<Produto> getProdutos();
    
    /**
     * Devolve o número de produtos lidos
     * @return int
     */
    public int getNumProdutosLidos();
    
    /**
     * Cria uma cópia de CatalogoProdutos
     * @return CatalogoProdutos
     */
    public CatalogoProdutos clone();
    
    /**
     * Verifica a igualdade de dois objectos
     * @param objecto
     * @return boolean
     */
    public boolean equals(Object o);
    
    /**
     * Metodo que devolve a representação em String do CatalogoProdutos
     * @return string
     */
    public String toString();
    
    /**
     * Valida as string de produtos e, caso sejam válidos, cria para cada string um Produto e adiciona ao CatalogoProdutos
     * @param coleção de strings
     * @return CatalogoProdutos
     */
    public CatalogoProdutos validaProdutos (Collection<String> produtos);
    
    /**
     * Determina se um produto existe no CatalogoProdutos
     * @param código do produto
     * @return boolean
     */
    public boolean existeProduto(String codigoProduto);
    
    /**
     * Adiciona um Produto ao CatalogoProdutos
     * @param Produto
     */
    public void adicionaProduto(Produto produto);
    
    /**
     * Liberta o CatalogoProdutos da memória
     */
    public void libertaCatalogoProdutos();
    
    /**
     * Determina se um produto existe no CatalogoProdutos
     * @param código do produto
     * @return boolean
     */
    public boolean produtoExiste (String codProduto);
}
