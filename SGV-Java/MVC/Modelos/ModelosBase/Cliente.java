package MVC.Modelos.ModelosBase;

import java.io.Serializable;

/**
 * Classe que implementa um Cliente.
 * O seu código de cliente é uma String.
 * 
 * @author (Grupo 26) 
 * @version (8/6/2019)
 */
public class Cliente implements ICliente, Serializable
{
    // Variáveis de Instância
    
    /** 
     * Código do cliente 
     */
    private String codigoCliente;
    
    // Construtores
    
    /**
     * Construtores da classe Cliente
     * Declaração dos construtores por omissao (vazio), parametrizado e de cópia
     */
    
    /**
     * Construtor por omissão de Cliente
     */
    public Cliente(){
        this.codigoCliente = "n/a";
    }
    
    /**
     * Construtor parametrizado de Cliente
     * Aceita como parâmetro um código de cliente
     * @param codigoCliente
     */
    public Cliente(String codigoCliente){
        this.codigoCliente = codigoCliente;
    }
    
    /**
     * Construtor de cópia de Cliente
     * Aceita como parâmetro outro Cliente e utiliza os métodos
     * de acesso ao valor da variável de instancia
     * @param Cliente original
     */
    public Cliente(Cliente cliente){
        this.codigoCliente = cliente.getCodigoCliente();
    }
    
    
    //métodos de instância
    
    //Gets
    
    /**
      * Devolve o código de Cliente
      * @return codigoCliente
      */
    public String getCodigoCliente(){
        return this.codigoCliente;
    }
    
    //Equals
    
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
        Cliente c = (Cliente)o;
        return this.codigoCliente.equals(c.getCodigoCliente());
    }
    
    // toString
    
    /**
     * Metodo que devolve a representação em String do Cliente
     * @return codigoCliente
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Codigo: " + this.codigoCliente + ";\n");
        return sb.toString();
    }
    
    // Clone

    /**
     * Cria uma cópia de Cliente
     * @return Cliente
     */
    public Cliente clone(){
        return new Cliente(this);
    }
    
    //Valida um cliente
    
    /**
     * Verifica se um Cliente é válido
     * @param codigoCliente
     * @return boolean
     */
    public static boolean validaCodigoCliente(String codCliente){
        return codCliente.length() == 5 
                   && (codCliente.matches("[A-Z]{1}[1-4]{1}[0-9]{3}") 
                   ||  codCliente.matches("[A-Z]{1}[5]{1}[0]{3}"));
    }
}
