package MVC.Modelos.Catalogos;

import MVC.Modelos.Catalogos.*;
import java.util.*;
import MVC.Modelos.ModelosBase.*;

public interface ICatalogoProdutos{

    public Collection<Produto> getProdutos(); 

    public int getNumProdutosLidos(); 

    public CatalogoProdutos clone();  

    public boolean equals(Object o);  

    public String toString();  

    public CatalogoProdutos validaProdutos(Collection<String> produtos);

    public boolean existeProduto(String codigoProduto);  

    public void adicionaProduto(Produto produto); 

    public void libertaCatalogoProdutos();  

    public boolean produtoExiste(String codProduto);
}
