package MVC.Modelos.ModelosBase;

import MVC.Modelos.Catalogos.CatalogoClientes;
import MVC.Modelos.Catalogos.CatalogoProdutos;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.lang.NumberFormatException;
import java.util.List;

public class Venda implements IVenda, Serializable {
    
    // Variveis de Instância

    private String codProd;

    private String codCli;

    private int quantidade;

    private double preco;

    private String tipo;

    private int mes; 

    private int filial;

    // Construtores

    public Venda() {
        this.codProd = "n/a";
        this.preco = 0;
        this.quantidade = 0;
        this.tipo = "n/a";
        this.codCli = "n/a";
        this.mes = 0;
        this.filial = 0;
    }

    public Venda(String codProd,double preco, int quantidade, String tipo, String codCli, int mes, int filial) {
        this.codProd = codProd;
        this.preco = preco;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.codCli = codCli;
        this.mes = mes;
        this.filial = filial;
    }

    public Venda (Venda venda) {
        this.codProd = venda.getCodProd();
        this.preco = venda.getPreco();
        this.quantidade = venda.getQuantidade();
        this.tipo = venda.getTipo();
        this.codCli = venda.getCodCli();
        this.mes = venda.getMes();
        this.filial = venda.getFilial();
    }

    // Devolve o código do produto vendido

    public String getCodProd() {
        return this.codProd;
    }

    // Devolve o código do cliente

    public String getCodCli() {
        return this.codCli;
    }

    // Devolve a quantidade de unidades vendidas

    public int getQuantidade() {
        return this.quantidade;
    }

    // Devolve o preço de cada unidade

    public double getPreco() {
        return this.preco;
    }

    // Devolve o tipo de venda

    public String getTipo() {
        return this.tipo;
    }

    // Devolve o mês da realização da venda

    public int getMes() {
        return this.mes;
    }

    // Devolve a filial onde foi realizada a compra

    public int getFilial() {
        return this.filial;
    }
    
    // Devolve o total faturado de uma venda

    public double faturado(){
        return this.quantidade * this.preco;
    }

    // toString

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
    
    // Equals

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
    
    // Valida uma venda em formato string e, com isto, cria uma Venda caso seja válida

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
    
    // Clone

    public Venda clone(){
        return new Venda(this);
    }
}