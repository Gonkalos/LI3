package MVC.Modelos.ModelosBase;

import java.io.Serializable;

public class Cliente implements ICliente, Serializable
{
    // Variáveis de Instância

    private String codigoCliente;
    
    // Construtores

    public Cliente(){
        this.codigoCliente = "n/a";
    }

    public Cliente(String codigoCliente){
        this.codigoCliente = codigoCliente;
    }

    public Cliente(Cliente cliente){
        this.codigoCliente = cliente.getCodigoCliente();
    }
    
    // Gets

    public String getCodigoCliente(){
        return this.codigoCliente;
    }
    
    // Equals

    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if((o == null) || (o.getClass() != this.getClass())){
            return false;
        }
        Cliente c = (Cliente)o;
        return this.codigoCliente.equals(c.getCodigoCliente());
    }
    
    // toString

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Codigo: " + this.codigoCliente + ";\n");
        return sb.toString();
    }
    
    // Clone

    public Cliente clone(){
        return new Cliente(this);
    }
    
    // Verifica se um Cliente é válido

    public static boolean validaCodigoCliente(String codCliente){
        return codCliente.length() == 5 
                   && (codCliente.matches("[A-Z]{1}[1-4]{1}[0-9]{3}") 
                   ||  codCliente.matches("[A-Z]{1}[5]{1}[0]{3}"));
    }
}
