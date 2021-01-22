package MVC.Modelos.ModelosBase;

import MVC.Modelos.Catalogos.CatalogoClientes;
import MVC.Modelos.Catalogos.CatalogoProdutos;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.lang.NumberFormatException;
import java.util.List;

/**
 * Classe que implementa uma Venda.
 * 
 * @author (Grupo 26) 
 * @version (8/6/2019)
 */
public class Venda implements IVenda, Serializable {
    
    //Variveis de Instância
    
    /**
     * Código de produto
     */
    private String codProd;
    
    /**
     * Código de cliente
     */
    private String codCli;
    
    /**
     * Quantidade de unidades vendidas
     */
    private int quantidade;
    
    /**
     * Preco de cada unidade
     */
    private double preco;
    
    /**
     * Tipo da venda (N -> não promoção / P -> promoção)
     */
    private String tipo;
    
    /**
     * Mês da venda
     */
    private int mes; 
    
    /**
     * Filial da venda
     */
    private int filial;

    /**
     * Construtores da classe Cliente
     * Declaração dos construtores por omissao (vazio), parametrizado e de cópia
     */
   
    /**
     * Construtor por omissao de Venda.
     */
    public Venda() {
        this.codProd = "n/a";
        this.preco = 0;
        this.quantidade = 0;
        this.tipo = "n/a";
        this.codCli = "n/a";
        this.mes = 0;
        this.filial = 0;
    }

    /**
     * Construtor parametrizado de Venda.
     * @param código de produto, preço, quantidade, tipo, código de cliente, mês, filial
     */
    public Venda(String codProd,double preco, int quantidade, String tipo, String codCli, int mes, int filial) {
        this.codProd = codProd;
        this.preco = preco;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.codCli = codCli;
        this.mes = mes;
        this.filial = filial;
    }

    /**
     * Construtor de cópia de Cliente.
     * @param Cliente original
     */
    public Venda (Venda venda) {
        this.codProd = venda.getCodProd();
        this.preco = venda.getPreco();
        this.quantidade = venda.getQuantidade();
        this.tipo = venda.getTipo();
        this.codCli = venda.getCodCli();
        this.mes = venda.getMes();
        this.filial = venda.getFilial();
    }

    // Gets

    /**
      * Devolve o código do produto vendido
      * @return string
      */
    public String getCodProd() {
        return this.codProd;
    }

    /**
      * Devolve o código do cliente 
      * @return string
      */
    public String getCodCli() {
        return this.codCli;
    }

    /**
      * Devolve a quantidade de unidades vendidas
      * @return int
      */
    public int getQuantidade() {
        return this.quantidade;
    }

    /**
      * Devolve o preço de cada unidade
      * @return double
      */
    public double getPreco() {
        return this.preco;
    }

    /**
      * Devolve o tipo de venda
      * @return string
      */
    public String getTipo() {
        return this.tipo;
    }

    /**
      * Devolve o mês da realização da venda
      * @return integer
      */
    public int getMes() {
        return this.mes;
    }

    /**
      * Devolve a filial onde foi realizada a compra
      * @return integer
      */
    public int getFilial() {
        return this.filial;
    }
    
    /**
     * Devolve o total faturado de uma venda
     * @return double
     */
    public double faturado(){
        return this.quantidade * this.preco;
    }

    /**
     * Metodo que devolve a representação em String da Venda
     * @return string
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("-----------VENDA-----------\n");
        sb.append("Codigo:\n");
        sb.append("\tProduto: " + this.codProd + ";\n");
        sb.append("\tCliente: " + this.codCli + ";\n");
        sb.append("Quantidade: " + this.quantidade + ".\n");
        sb.append("Preco Unidade: " + this.preco + ".\n");
        sb.append("Tipo: " + this.tipo + ".\n");
        sb.append("Mes: " + this.mes + ".\n");
        sb.append("Filial: " + this.filial + ".\n");
        return sb.toString();
    }
    
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
        Venda venda = (Venda)o;
        return this.codProd.equals(venda.getCodProd()) && this.codCli.equals(venda.getCodCli()) && this.quantidade == venda.getQuantidade() &&
                this.preco == venda.getPreco() && this.tipo.equals(venda.getTipo()) && this.mes == venda.getMes() && this.filial == venda.getFilial();
    }
    
    /**
     * Valida uma venda em formato string e, com isto, cria uma Venda caso seja válida
     * @param String
     * @return Venda
     */
    public static Venda validaVenda(String linhaVenda){
        Venda v = null;
        int quantidade = 0, mes = 0, filial = 0;
        double preco = 0.0;
        String codProd, codCli, tipo;
        String[] campos;
        campos = linhaVenda.split(" ");
        
        codProd = campos[0];
        
        try{
            preco = Double.parseDouble(campos[1]);
        }
        catch(InputMismatchException e){return null;}
        catch(NumberFormatException e){return null;}
        
        try{
            quantidade = Integer.parseInt(campos[2]);
        }
        catch(InputMismatchException e){return null;}
        catch(NumberFormatException e){return null;}
        
        tipo = campos[3];
        if(!(tipo.equals("N") || (tipo.equals("P")))){
            return null;
        }
        
        codCli = campos[4];
        
        try{
            mes = Integer.parseInt(campos[5]);
        }
        catch(InputMismatchException e){return null;}
        catch(NumberFormatException e){return null;}
        
        try{
            filial = Integer.parseInt(campos[6]);
        }
        catch(InputMismatchException e){return null;}
        catch(NumberFormatException e){return null;}
        
        if(codCli != null && codProd != null){
            return new Venda(codProd,preco,quantidade,tipo,codCli,mes,filial);
        }
        return null;
    }
    
    /**
     * Cria uma cópia de Venda
     * @return Venda
     */
    public Venda clone(){
        return new Venda(this);
    }
}