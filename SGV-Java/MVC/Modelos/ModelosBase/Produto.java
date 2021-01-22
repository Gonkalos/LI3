package MVC.Modelos.ModelosBase;

import java.io.Serializable;

/**
 * Classe que implementa um Produto.
 * 
 * @author (Grupo 26) 
 * @version (8/6/2019)
 */
public class Produto implements IProduto, Serializable{  
    
    // Variáveis de Instância
    
    /** 
     * Código do produto 
     */
    private String codigoProduto;
    
    // Construtores
    
    /**
     * Construtores da classe Produto
     * Declaração dos construtores por omissão(vazio), parametrizado e de cópia.
     */
    
    /** 
     * Construtor por omissão 
     */
    public Produto(){
        this.codigoProduto = "n/a";
    }
    
    /** 
     * Construtor por cópia
     * @param Produto original
     */
    public Produto(Produto produto){
        this.codigoProduto = produto.getCodigoProduto();
    }
    
    /**
     * Construtor parametrizado
     * @param código do produto
     */
    public Produto(String codigoProduto){
        this.codigoProduto = codigoProduto;
    }
    
    // Gets
    
    /**
     * Devolve o código do produto
     * @return String
     */
    public String getCodigoProduto(){
        return this.codigoProduto;
    }
    
    // Equals
    
    /**
     * Verifica a igualdade de dois objectos
     * @param objecto
     * @return boolean
     */
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
    
    /**
     * Retorna uma representação textual do objecto
     * @return String
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Codigo: " + this.codigoProduto + "\n");
        return sb.toString();
    }
    
    // Clone

    /**
     * Cria uma cópia do objecto 
     * @return objecto
     */
    public Produto clone(){
        return new Produto(this);
    }
    
    // Valida um Produto
    
    /**
     * Verifica se um código de produto é válido
     * @param código de produto
     * @return boolean
     */
    public static boolean validaCodigoProduto(String codProduto){
        return codProduto.length()== 6 && codProduto.matches("[A-Z]{2}[1-9]{1}[0-9]{3}");
    }
    
}
