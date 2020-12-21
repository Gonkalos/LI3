package MVC.Modelos.Catalogos;

import java.util.Collection;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;
import MVC.Modelos.ModelosBase.Produto;
import java.util.stream.Collectors;

public class CatalogoProdutos implements ICatalogoProdutos,Serializable{
   
    // Variáveis de Instância

    private Collection<Produto> produtos;

    private int numProdutosLidos;

    // Comparador de produtos por ordem alfabética dos seus códigos

    Comparator<Produto> compProdutoCodigo = ((a,b) -> a.getCodigoProduto().compareTo(b.getCodigoProduto()));
    
    // Construtores

    public CatalogoProdutos(){
        this.produtos = new TreeSet<>(compProdutoCodigo);
        //this.produtos = new HashSet<>();
        //this.produtos = new ArrayList<>();
        this.numProdutosLidos = 0;
    }

    public CatalogoProdutos (CatalogoProdutos catProdutos){
        this.produtos = catProdutos.getProdutos();
        this.numProdutosLidos = getNumProdutosLidos();
    }

    public CatalogoProdutos (Collection<Produto> produtos, int numProdutosLidos){
        this.produtos = new TreeSet<>(compProdutoCodigo);
        //this.produtos = new HashSet<>();
        //this.produtos = new ArrayList();
        for(Produto p : produtos){
            this.produtos.add(p.clone());
        }
        this.numProdutosLidos = numProdutosLidos;
    }
    
    // Gets

    public Collection<Produto> getProdutos(){
        Collection<Produto> aux = new TreeSet<>(compProdutoCodigo);
        //Collection<Produto> aux = new HashSet<>();
        //Collection<Produto> aux = new ArrayList<>();
        for(Produto p : this.produtos){
            aux.add(p.clone());
        }
        return aux;
    }

    public int getNumProdutosLidos(){
        return this.numProdutosLidos;
    }

    // Cria uma cópia de CatalogoProdutos

    public CatalogoProdutos clone(){
        return new CatalogoProdutos(this);
    }

    // Verifica a igualdade de dois objectos

    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if((o == null) || (o.getClass() != this.getClass())){
            return false;
        }
        
        CatalogoProdutos catProdutos = (CatalogoProdutos)o;
        
        return this.produtos.equals(catProdutos.getProdutos()) && this.numProdutosLidos == catProdutos.getNumProdutosLidos();
    }
    
    // Metodo que devolve a representação em String do CatalogoProdutos

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("----------Catalogo de Produtos----------\n");
        for(Produto p : this.produtos){
            sb.append(p.toString());
        }
        sb.append("Número de produtos lidos: " + this.getNumProdutosLidos() + "\n");
        sb.append("Número de produtos válidos: " + this.produtos.size() + "\n");
        return sb.toString();
    }
    
    // Valida as string de produtos e, caso sejam válidos, cria para cada string um Produto e adiciona ao CatalogoProdutos

    public CatalogoProdutos validaProdutos (Collection<String> produtos){
        CatalogoProdutos aux = new CatalogoProdutos();
        for(String s : produtos){
            if (Produto.validaCodigoProduto(s)){
                Produto p = new Produto(s);
                aux.adicionaProduto(p);
            }
        }
        aux.numProdutosLidos = produtos.size();
        return aux;
    }

    // Determina se um produto existe no CatalogoProdutos

    public boolean existeProduto(String codigoProduto){
        Produto p = new Produto(codigoProduto);
        return this.produtos.contains(p);
    }
    
    // Adiciona um Produto ao CatalogoProdutos

    public void adicionaProduto(Produto produto){
        this.produtos.add(produto.clone());
    }   

    // Liberta o CatalogoProdutos da memória

    public void libertaCatalogoProdutos(){
        this.produtos.clear();
    }
    
    // Determina se um produto existe no CatalogoProdutos

    public boolean produtoExiste (String codProduto){
        return this.produtos.contains(new Produto(codProduto));
    }
    
}