package MVC.Modelos.ModelosBase;

import java.io.Serializable;

public class Produto implements IProduto, Serializable{  
    
    // Variáveis de Instância

    private String codigoProduto;
    
    // Construtores

    public Produto(){
        this.codigoProduto = "n/a";
    }

    public Produto(Produto produto){
        this.codigoProduto = produto.getCodigoProduto();
    }

    public Produto(String codigoProduto){
        this.codigoProduto = codigoProduto;
    }
    
    // Devolve o código do produto

    public String getCodigoProduto(){
        return this.codigoProduto;
    }
    
    // Equals

    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if((o == null) || (o.getClass() != this.getClass())){
            return false;
        }
        Produto p = (Produto)o;
        return this.codigoProduto.equals(p.getCodigoProduto());
    }
    
    // toString

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Codigo: " + this.codigoProduto + "\n");
        return sb.toString();
    }
    
    // Clone

    public Produto clone(){
        return new Produto(this);
    }
    
    // Verifica se um código de produto é válido

    public static boolean validaCodigoProduto(String codProduto){
        return codProduto.length()== 6 && codProduto.matches("[A-Z]{2}[1-9]{1}[0-9]{3}");
    }
    
}
